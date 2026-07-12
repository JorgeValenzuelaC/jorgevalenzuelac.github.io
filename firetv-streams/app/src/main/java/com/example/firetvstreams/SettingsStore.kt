package com.example.firetvstreams

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/** Persists the user-configurable playlist URL across launches. */
class SettingsStore(private val context: Context) {

    private val playlistKey = stringPreferencesKey("playlist_url")

    /** Emits the saved playlist URL, or the build default when nothing is saved yet. */
    val playlistUrl: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[playlistKey]?.takeIf { it.isNotBlank() } ?: BuildConfig.DEFAULT_PLAYLIST_URL
    }

    suspend fun setPlaylistUrl(url: String) {
        context.dataStore.edit { prefs ->
            prefs[playlistKey] = url.trim()
        }
    }
}
