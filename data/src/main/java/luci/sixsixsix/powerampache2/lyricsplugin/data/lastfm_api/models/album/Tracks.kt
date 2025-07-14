package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.album


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.Track

@Keep
data class Tracks(
    @SerializedName("track")
    val track: List<Track?>? = null
)
