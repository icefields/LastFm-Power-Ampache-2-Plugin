package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Image(
    @SerializedName("size")
    val size: String? = null,
    @SerializedName("#text")
    val text: String? = null
)