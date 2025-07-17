package luci.sixsixsix.powerampache2.infoplugin.domain.local

import kotlinx.coroutines.flow.StateFlow

interface SharedPreferencesManager {
    // most apis require an auth token
    var apiKey: String
    val apiKeyStateFlow: StateFlow<String>

    var secretKey: String
    val secretKeyStateFlow: StateFlow<String>
}
