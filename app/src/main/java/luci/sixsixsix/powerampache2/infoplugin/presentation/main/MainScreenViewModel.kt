package luci.sixsixsix.powerampache2.infoplugin.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import luci.sixsixsix.powerampache2.infoplugin.domain.local.SharedPreferencesManager
import luci.sixsixsix.powerampache2.infoplugin.domain.usecase.AuthenticateUseCase
import luci.sixsixsix.powerampache2.infoplugin.domain.usecase.ClearStoredLyricsUseCase
import luci.sixsixsix.powerampache2.infoplugin.domain.usecase.FetchSongInfoUseCase
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val fetchSongInfoUseCase: FetchSongInfoUseCase,
    private val clearStoredLyricsUseCase: ClearStoredLyricsUseCase,
    private val authenticateUseCase: AuthenticateUseCase,
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {
    val tokenStateFlow = sharedPreferencesManager.apiKeyStateFlow

    init {
        viewModelScope.launch {
            authenticateUseCase("username/email", "password")
        }
    }

    fun setToken(newToken: String) {
        sharedPreferencesManager.apiKey = newToken
    }

    fun clearStoredLyrics() = viewModelScope.launch {
        clearStoredLyricsUseCase()
    }

    fun fetchLyricsDebug() =
    fetchSongInfoUseCase(
        songId = "1",
        mbId = "",
        songTitle =  "dream on",
        albumTitle = "",
        artistName = "aerosmith") { songdata ->
        println("aaaa callback resp:$songdata")
    }
}
