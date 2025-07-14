package luci.sixsixsix.powerampache2.lyricsplugin.data.local

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import luci.sixsixsix.powerampache2.lyricsplugin.data.di.WeakContext
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.common.LASTFM_API_KEY
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferenceDelegate
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_BEARER_TOKEN = "luci.sixsixsix.powerampache2.plugin.lyrics.data.KEY_BEARER_TOKEN"

@Singleton
class SharedPreferencesManagerImpl @Inject constructor(
    private val weakContext: WeakContext,
): SharedPreferencesManager, SharedPreferenceDelegate by SharedPreferenceDelegateImpl(weakContext) {

    private val _tokenStateFlow = MutableStateFlow(token)
    override val tokenStateFlow: StateFlow<String> = _tokenStateFlow

    override var token: String
        get() = getString(KEY_BEARER_TOKEN, LASTFM_API_KEY).run {
            if (isNullOrBlank()) LASTFM_API_KEY else this
        }
        set(value) = setString(KEY_BEARER_TOKEN, value).also {
            _tokenStateFlow.value = token
        }
}
