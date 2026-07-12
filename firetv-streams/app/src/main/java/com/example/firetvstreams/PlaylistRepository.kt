package com.example.firetvstreams

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

/** Result of a playlist load: either channels, or an error message to surface in the UI. */
sealed interface LoadResult {
    data class Success(val channels: List<Channel>) : LoadResult
    data class Error(val message: String) : LoadResult
}

/**
 * Loads channels from a user-supplied M3U/M3U8 playlist URL.
 *
 * If the URL is empty, still the shipped placeholder, or fails to load, the
 * repository falls back to [sampleChannels] — a small set of openly published
 * test/demo streams so the app is usable out of the box without pointing it at
 * anyone's content.
 */
class PlaylistRepository {

    suspend fun load(url: String): LoadResult = withContext(Dispatchers.IO) {
        if (url.isBlank() || url == PLACEHOLDER_URL) {
            return@withContext LoadResult.Success(sampleChannels)
        }
        try {
            val body = fetch(url)
            val channels = M3uParser.parse(body)
            if (channels.isEmpty()) {
                LoadResult.Error("No channels found in the playlist. Check that the URL points to a valid M3U file.")
            } else {
                LoadResult.Success(channels)
            }
        } catch (e: Exception) {
            LoadResult.Error("Could not load playlist: ${e.message ?: "unknown error"}")
        }
    }

    private fun fetch(url: String): String {
        val connection = (URL(url).openConnection() as HttpURLConnection).apply {
            connectTimeout = 15_000
            readTimeout = 15_000
            requestMethod = "GET"
            instanceFollowRedirects = true
            setRequestProperty("User-Agent", "FireTVStreams/1.0")
        }
        try {
            val code = connection.responseCode
            if (code !in 200..299) {
                throw IllegalStateException("HTTP $code")
            }
            return connection.inputStream.bufferedReader().use { it.readText() }
        } finally {
            connection.disconnect()
        }
    }

    companion object {
        const val PLACEHOLDER_URL =
            "https://raw.githubusercontent.com/example/placeholder/main/sample.m3u"

        /**
         * Openly published demo/test streams used as a working default.
         * These are widely distributed reference assets, not third-party
         * broadcast content. Replace them by configuring your own playlist URL.
         */
        val sampleChannels: List<Channel> = listOf(
            Channel(
                name = "Apple HLS Basic (bipbop)",
                url = "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_ts/master.m3u8",
                group = "Reference streams",
            ),
            Channel(
                name = "Big Buck Bunny (HLS)",
                url = "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8",
                group = "Reference streams",
            ),
            Channel(
                name = "Tears of Steel (DASH)",
                url = "https://storage.googleapis.com/shaka-demo-assets/tears-of-steel/dash.mpd",
                group = "Reference streams",
            ),
        )
    }
}
