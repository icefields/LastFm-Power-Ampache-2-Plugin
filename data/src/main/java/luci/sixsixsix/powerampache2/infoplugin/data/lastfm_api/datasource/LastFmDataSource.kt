package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.datasource

import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.LASTFM_METHOD_GET_MOBILE_SESSION
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.MainNetwork
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.LASTFM_API_KEY
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.md5
import javax.inject.Inject

class LastFmDataSource @Inject constructor(
    private val api: MainNetwork
) {
    suspend fun authenticate(apiKey: String, secret: String, username: String, password: String) =
        api.getMobileSession(
            apiKey = apiKey,
            apiSig = apiSignature(
                apiKey = apiKey,
                secret = secret,
                username = username,
                password = password,
                method = LASTFM_METHOD_GET_MOBILE_SESSION
            ),
            username = username,
            password = password
        )


    /**
     * Construct your api method signatures by first ordering all the parameters sent in your call
     * alphabetically by parameter name and concatenating them into one string using a
     * <name><value> scheme. So for a call to auth.getMobileSession you may have:
     * **api_key**xxxxxxxx**method**auth.getMobileSession**password**xxxxxxx**username**xxxxxxxx
     *
     * Ensure your parameters are utf8
     * encoded. Now append your secret to this string. Finally, generate an md5
     *
     * hash of the resulting string. For example, for an account with a secret equal to 'mysecret',
     * your api signature will be:
     * api signature = md5("api_keyxxxxxxxxmethodauth.getMobileSession
     *                          passwordxxxxxxxusernamexxxxxxxxmysecret")
     * Where md5() is an md5 hashing operation and its argument is the string to be hashed.
     * The hashing operation should return a 32-character hexadecimal md5 hash.
     */
    fun apiSignature(
        apiKey: String = LASTFM_API_KEY,
        secret: String,
        username: String,
        password: String,
        method: String = LASTFM_METHOD_GET_MOBILE_SESSION
    ) = "api_key${apiKey}method${method}password${password}username${username}${secret}".md5()
}