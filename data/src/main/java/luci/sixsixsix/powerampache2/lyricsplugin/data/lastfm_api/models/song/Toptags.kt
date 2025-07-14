package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.song


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.Tag

@Keep
data class Toptags(
    @SerializedName("tag")
    val tag: List<Tag?>? = null
)