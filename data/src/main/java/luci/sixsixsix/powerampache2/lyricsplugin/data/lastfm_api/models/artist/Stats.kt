package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.artist


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Stats(
    @SerializedName("listeners")
    val listeners: Int? = null,
    @SerializedName("playcount")
    val playcount: Int? = null
)