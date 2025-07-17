package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.DURATION_NOT_VALID
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.song.Toptags
import luci.sixsixsix.powerampache2.infoplugin.domain.models.AlbumTrack

@Keep
data class Track(
    @SerializedName("album")
    val album: Album? = null,
    @SerializedName("artist")
    val artist: Artist? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("listeners")
    val listeners: Int? = null,
    @SerializedName("mbid")
    val mbid: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("playcount")
    val playcount: Int? = null,
    @SerializedName("streamable")
    val streamable: Streamable? = null,
    @SerializedName("toptags")
    val toptags: Toptags? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("wiki")
    val wiki: Wiki? = null,
)

fun Track.toAlbumTrack() = AlbumTrack(
    mbId = mbid ?: "",
    title = name ?: "",
    duration = duration ?: DURATION_NOT_VALID,
    url = url ?: "",
    songArtistName = artist?.name ?: "",
    songArtistMbId = artist?.mbid ?: ""
)
