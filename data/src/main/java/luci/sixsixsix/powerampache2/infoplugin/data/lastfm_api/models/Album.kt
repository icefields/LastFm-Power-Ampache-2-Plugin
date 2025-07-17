package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.album.Tracks

@Keep
data class Album(
    @SerializedName("artist")
    val artist: String? = null,
    @SerializedName("image")
    val image: List<Image?>? = null,
    @SerializedName("listeners")
    val listeners: Int? = null,
    @SerializedName("mbid")
    val mbid: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("playcount")
    val playcount: Int? = null,
    @SerializedName("tags")
    val tags: Tags? = null,
    @SerializedName("tracks")
    val tracks: Tracks? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("wiki")
    val wiki: Wiki? = null,

    @SerializedName("@attr")
    val attr: Attr? = null,
    @SerializedName("title")
    val title: String? = null,
)