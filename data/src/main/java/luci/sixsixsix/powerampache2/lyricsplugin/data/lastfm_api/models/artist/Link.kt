package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.artist


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Links(
    @SerializedName("link")
    val link: Link? = null
)

@Keep
data class Link(
    @SerializedName("href")
    val href: String? = null,
    @SerializedName("rel")
    val rel: String? = null,
    @SerializedName("#text")
    val text: String? = null
)