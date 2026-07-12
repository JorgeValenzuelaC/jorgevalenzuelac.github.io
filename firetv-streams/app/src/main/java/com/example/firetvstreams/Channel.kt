package com.example.firetvstreams

/**
 * A single playable entry parsed from an M3U playlist.
 *
 * @param name    Human-readable channel/title, shown in the grid.
 * @param url     Direct stream URL (HLS .m3u8, DASH .mpd, or a progressive file).
 * @param logoUrl Optional logo URL from the playlist's `tvg-logo` attribute.
 * @param group   Optional category from the playlist's `group-title` attribute.
 */
data class Channel(
    val name: String,
    val url: String,
    val logoUrl: String? = null,
    val group: String? = null,
)
