package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.artist


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.Artist

@Keep
data class Similar(
    @SerializedName("artist")
    val artist: List<Artist>? = listOf()
)