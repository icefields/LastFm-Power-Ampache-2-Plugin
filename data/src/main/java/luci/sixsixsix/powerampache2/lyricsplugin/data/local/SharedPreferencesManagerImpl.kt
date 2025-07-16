package luci.sixsixsix.powerampache2.lyricsplugin.data.local

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import luci.sixsixsix.powerampache2.lyricsplugin.data.di.WeakContext
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.common.LASTFM_API_KEY
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.common.LASTFM_SECRET_KEY
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferenceDelegate
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_API_KEY = "luci.sixsixsix.powerampache2.plugin.info.data.API_KEY"
private const val KEY_SECRET_KEY = "luci.sixsixsix.powerampache2.plugin.info.data.KEY_SECRET_KEY"

@Singleton
class SharedPreferencesManagerImpl @Inject constructor(
    private val weakContext: WeakContext,
): SharedPreferencesManager, SharedPreferenceDelegate by SharedPreferenceDelegateImpl(weakContext) {

    private val _apiKeyStateFlow = MutableStateFlow(apiKey)
    override val apiKeyStateFlow: StateFlow<String> = _apiKeyStateFlow

    private val _secretKeyStateFlow = MutableStateFlow(secretKey)
    override val secretKeyStateFlow: StateFlow<String> = _secretKeyStateFlow

    override var apiKey: String
        get() = getString(KEY_API_KEY, LASTFM_API_KEY).run {
            if (isNullOrBlank()) LASTFM_API_KEY else this
        }
        set(value) = setString(KEY_API_KEY, value).also {
            _apiKeyStateFlow.value = apiKey
        }

    override var secretKey: String
        get() = getString(KEY_SECRET_KEY, LASTFM_SECRET_KEY).run {
            if (isNullOrBlank()) LASTFM_SECRET_KEY else this
        }
        set(value) = setString(KEY_SECRET_KEY, value).also {
            _secretKeyStateFlow.value = secretKey
        }
}
