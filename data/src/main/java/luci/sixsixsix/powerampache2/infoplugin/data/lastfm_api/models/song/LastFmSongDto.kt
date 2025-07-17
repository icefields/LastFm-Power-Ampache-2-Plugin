package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.song


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.DESCRIPTION_NOT_VALID
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.DURATION_NOT_VALID
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.POSITION_NOT_VALID
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.parseImage
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.removeHtmlAnchor
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.Track
import luci.sixsixsix.powerampache2.infoplugin.domain.models.PluginSongData

@Keep
data class LastFmSongDto(
    @SerializedName("track")
    val track: Track? = null
)

fun LastFmSongDto.toPluginSongData(
    songId: String,
    mbId: String,
    songName: String,
    albumName: String,
    artistName: String
) = PluginSongData(
        id = songId,
        title = track?.name ?: songName,
        mbId = track?.mbid ?: mbId,
        description = track?.wiki?.content?.removeHtmlAnchor() ?: DESCRIPTION_NOT_VALID,
        shortDescription = track?.wiki?.summary?.removeHtmlAnchor() ?: DESCRIPTION_NOT_VALID,
        // When fetching a song, a title is received, not a name.
        albumName = track?.album?.title ?: track?.album?.name ?: albumName,
        artistName = track?.artist?.name ?: artistName,
        language = "", // not available in lastfm response
        lyrics = "", // not available in lastfm response
        albumMbId = track?.album?.mbid ?: "",
        artistMbId = track?.artist?.mbid ?: "",
        imageUrl = parseImage(track?.album?.image?.last()?.text),
        year = "",
        artistAlbum = track?.album?.artist ?: artistName,
        imageAlbum = parseImage(track?.album?.image?.last()?.text),
        urlAlbum = track?.album?.url ?: "",
        imageArtist = parseImage(track?.album?.image?.last()?.text), // not available in lastfm response
        urlArtist = track?.artist?.url ?: "",
        url = track?.url ?: "",
        duration = track?.duration ?: DURATION_NOT_VALID,
        listeners = track?.listeners ?: 0,
        playCount = track?.playcount ?: 0,
        topTags = track?.toptags?.tag?.mapNotNull { it?.name } ?: listOf(),
        position = track?.album?.attr?.position ?: POSITION_NOT_VALID
    )
