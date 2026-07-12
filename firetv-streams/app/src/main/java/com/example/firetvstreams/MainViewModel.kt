package com.example.firetvstreams

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/** UI state for the channel grid screen. */
sealed interface ChannelsUiState {
    data object Loading : ChannelsUiState
    data class Ready(val channels: List<Channel>) : ChannelsUiState
    data class Error(val message: String) : ChannelsUiState
}

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val settings = SettingsStore(app)
    private val repository = PlaylistRepository()

    private val _state = MutableStateFlow<ChannelsUiState>(ChannelsUiState.Loading)
    val state: StateFlow<ChannelsUiState> = _state.asStateFlow()

    private val _playlistUrl = MutableStateFlow("")
    val playlistUrl: StateFlow<String> = _playlistUrl.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value = ChannelsUiState.Loading
            val url = settings.playlistUrl.first()
            _playlistUrl.value = url
            _state.value = when (val result = repository.load(url)) {
                is LoadResult.Success -> ChannelsUiState.Ready(result.channels)
                is LoadResult.Error -> ChannelsUiState.Error(result.message)
            }
        }
    }

    fun savePlaylistUrl(url: String) {
        viewModelScope.launch {
            settings.setPlaylistUrl(url)
            refresh()
        }
    }
}
