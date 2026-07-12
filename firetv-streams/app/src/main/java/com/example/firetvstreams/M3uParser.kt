package com.example.firetvstreams

/**
 * Minimal parser for extended M3U (`#EXTM3U`) playlists.
 *
 * Extended M3U is the de-facto standard for IPTV and streaming playlists.
 * A typical entry looks like:
 *
 * ```
 * #EXTM3U
 * #EXTINF:-1 tvg-logo="https://.../logo.png" group-title="News",Example News
 * https://example.com/live/news.m3u8
 * ```
 *
 * The parser reads each `#EXTINF` line for the display name and optional
 * `tvg-logo` / `group-title` attributes, then pairs it with the following
 * non-comment line as the stream URL. Plain playlists (bare URLs, one per
 * line) are also supported.
 *
 * This does not scrape or reverse-engineer any website — it only reads a
 * playlist you supply. Point it at content you are licensed to access.
 */
object M3uParser {

    private val ATTR_REGEX = Regex("""([A-Za-z0-9-]+)="([^"]*)"""")

    fun parse(content: String): List<Channel> {
        val channels = mutableListOf<Channel>()
        var pendingName: String? = null
        var pendingLogo: String? = null
        var pendingGroup: String? = null

        content.lineSequence().forEach { rawLine ->
            val line = rawLine.trim()
            when {
                line.isEmpty() -> Unit

                line.startsWith("#EXTINF", ignoreCase = true) -> {
                    val attrs = ATTR_REGEX.findAll(line).associate { it.groupValues[1] to it.groupValues[2] }
                    pendingLogo = attrs["tvg-logo"]?.takeIf { it.isNotBlank() }
                    pendingGroup = attrs["group-title"]?.takeIf { it.isNotBlank() }
                    // The display name is the text after the last comma on the line.
                    pendingName = line.substringAfterLast(',', "").trim().takeIf { it.isNotEmpty() }
                }

                // Skip every other directive/comment line.
                line.startsWith("#") -> Unit

                else -> {
                    val name = pendingName ?: deriveName(line)
                    channels += Channel(
                        name = name,
                        url = line,
                        logoUrl = pendingLogo,
                        group = pendingGroup,
                    )
                    pendingName = null
                    pendingLogo = null
                    pendingGroup = null
                }
            }
        }
        return channels
    }

    /** Falls back to a readable name derived from the URL when no #EXTINF label exists. */
    private fun deriveName(url: String): String {
        val last = url.substringAfterLast('/').substringBefore('?')
        return last.substringBeforeLast('.').ifBlank { "Stream" }
    }
}
