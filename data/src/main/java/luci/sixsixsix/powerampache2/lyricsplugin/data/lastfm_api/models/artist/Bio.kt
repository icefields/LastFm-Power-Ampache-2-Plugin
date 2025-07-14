package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.artist


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Bio(
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("links")
    val links: Links? = null,
    @SerializedName("published")
    val published: String? = null,
    @SerializedName("summary")
    val summary: String? = null
)