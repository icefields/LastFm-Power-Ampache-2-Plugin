package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Tags(
    @SerializedName("tag")
    val tag: List<Tag?>? = null
)

@Keep
data class Tag(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)