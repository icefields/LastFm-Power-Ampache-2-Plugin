package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Attr(
    @SerializedName("rank")
    val rank: Int? = null,
    @SerializedName("position")
    val position: Int? = null
)
