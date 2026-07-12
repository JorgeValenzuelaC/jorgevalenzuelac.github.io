# FireTV Streams

A minimal **Android TV / Fire TV** app that shows a grid of channels and plays
them full-screen with ExoPlayer. It is **source-agnostic**: it does not scrape
or reverse-engineer any website. Instead it loads channels from a standard
**M3U / M3U8 playlist URL that you supply** — the same playlist format used by
Plex, Jellyfin, Pluto, and paid IPTV subscriptions.

Point it only at streams you are licensed to access (a free/legal service, your
own media server, or a subscription you pay for). See [Legal use](#legal-use).

## What it does

1. On launch it loads the configured M3U playlist (or the built-in sample
   streams if none is set).
2. It parses the playlist's `#EXTINF` entries for channel names, logos
   (`tvg-logo`), and categories (`group-title`).
3. Channels render in a D-pad–navigable grid (Compose for TV).
4. Selecting a channel opens a full-screen ExoPlayer that handles HLS
   (`.m3u8`), DASH (`.mpd`), and progressive files.

Out of the box it ships with a few openly published **reference/demo streams**
(Apple's BipBop HLS, the Mux Big Buck Bunny test stream, and Shaka's Tears of
Steel DASH) so it runs before you configure anything.

## Configure your playlist

- **In the app:** open **Settings** on the main screen, paste your playlist
  URL, and press **Save**. It is persisted across launches.
- **At build time:** change `DEFAULT_PLAYLIST_URL` in
  [`app/build.gradle.kts`](app/build.gradle.kts).

A valid extended M3U playlist looks like:

```
#EXTM3U
#EXTINF:-1 tvg-logo="https://example.com/news.png" group-title="News",Example News
https://example.com/live/news.m3u8
#EXTINF:-1,Example Sports
https://example.com/live/sports.m3u8
```

## Build

Requires the **Android SDK** (via Android Studio Hedgehog+ or a CI runner with
`sdkmanager`). The Gradle wrapper is checked in, so no local Gradle install is
needed.

```bash
cd firetv-streams
./gradlew assembleDebug          # builds app/build/outputs/apk/debug/app-debug.apk
```

Install and run on a connected Fire TV / Android TV device or emulator:

```bash
./gradlew installDebug
# or open the folder in Android Studio and press Run
```

### Tech stack

| Concern        | Library                                   |
|----------------|-------------------------------------------|
| UI             | Jetpack Compose + **Compose for TV**      |
| Playback       | **Media3 / ExoPlayer** (HLS + DASH)       |
| Persistence    | DataStore Preferences                     |
| Min / target   | API 21 (Android 5.0) / API 34             |

## Project layout

```
firetv-streams/
├── settings.gradle.kts
├── build.gradle.kts
├── gradle/wrapper/…              # checked-in Gradle wrapper
└── app/
    ├── build.gradle.kts
    └── src/main/
        ├── AndroidManifest.xml
        ├── java/com/example/firetvstreams/
        │   ├── Channel.kt            # data model
        │   ├── M3uParser.kt          # extended-M3U parser (no scraping)
        │   ├── PlaylistRepository.kt # fetch + sample fallback
        │   ├── SettingsStore.kt      # persisted playlist URL
        │   ├── MainViewModel.kt      # loading / ready / error state
        │   ├── MainActivity.kt       # TV channel grid + settings dialog
        │   ├── PlayerActivity.kt     # full-screen ExoPlayer
        │   └── Theme.kt
        └── res/…                     # icon, banner, strings, theme
```

## Notes

- **HTTP (cleartext) streams:** the manifest defaults to HTTPS-only for
  security. If you must play an `http://` stream, add
  `android:usesCleartextTraffic="true"` to `<application>` in
  `AndroidManifest.xml`, or use a network-security-config for specific hosts.
- **Referer / headers:** some streams require custom request headers. Add a
  `DefaultHttpDataSource.Factory` with `setDefaultRequestProperties(...)` and
  pass it to the ExoPlayer `MediaSource` in `PlayerActivity.kt`.

## Legal use

This app is a generic player. It contains no channel list and connects to no
service on its own. You are responsible for ensuring you have the right to
access whatever playlist and streams you configure it with. Do not use it to
access content you are not licensed to receive.
