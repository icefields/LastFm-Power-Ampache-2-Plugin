package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.album


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.DESCRIPTION_NOT_VALID
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.DURATION_NOT_VALID
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.RANK_NOT_VALID
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.parseImage
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.removeHtmlAnchor
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.Album
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.toAlbumTrack
import luci.sixsixsix.powerampache2.infoplugin.domain.models.PluginAlbumData

@Keep
data class LastFmAlbumDto(
    @SerializedName("album")
    val album: Album? = null
)

fun LastFmAlbumDto.toPluginAlbumData(
    albumId: String,
    mbId: String,
    albumName: String,
    artistName: String
) = PluginAlbumData(
    id = albumId,
    // When fetching an album, a name is received, not a title.
    albumName = album?.name ?: album?.title ?: albumName,
    artistName = album?.artist ?: artistName,
    description = sanitizeDescription(
        album?.wiki?.content?.removeHtmlAnchor() ?: DESCRIPTION_NOT_VALID
    ),
    shortDescription = sanitizeDescription(
        album?.wiki?.summary?.removeHtmlAnchor() ?: DESCRIPTION_NOT_VALID
    ),
    mbId = album?.mbid ?: mbId,
    language = "", // not available in lastFm
    lyrics = "", // not available in lastFm
    artistMbId = album?.tracks?.track?.firstOrNull()?.artist?.mbid ?: "",
    albumArtistMbId = "",
    imageUrl = parseImage(album?.image?.last()?.text),
    year = "",
    tags = album?.tags?.tag?.mapNotNull { it?.name } ?: listOf(),
    rank = album?.attr?.rank ?: RANK_NOT_VALID,
    url = album?.url ?: "",
    imageArtist = parseImage(album?.image?.last()?.text),
    urlArtist = album?.tracks?.track?.firstOrNull()?.artist?.url ?: "",
    duration = album?.tracks?.track?.sumOf { it?.duration ?: 0 } ?: DURATION_NOT_VALID,
    listeners = album?.listeners ?: 0,
    playCount = album?.playcount ?: 0,
    tracks = album?.tracks?.track?.mapNotNull { it?.toAlbumTrack() } ?: listOf()
)

/**
 * TODO : description itself might contain those words leading to unreadable sentences
 */
fun sanitizeDescription(descr: String): String = descr.replace("youtube | facebook | bandcamp", "")
    .replace("youtube", "")
    .replace("facebook", "")
    .replace("bandcamp", "")
    .replace("soundcloud", "")
    .replace("merch store", "")
    .replace("| |", "")