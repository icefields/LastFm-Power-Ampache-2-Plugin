package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.artist


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.common.DESCRIPTION_NOT_VALID
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.common.removeHtmlAnchor
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.Artist
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.album.sanitizeDescription
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.toSimilarArtist
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.PluginArtistData

@Keep
data class LastFmArtistDto(
    @SerializedName("artist")
    val artist: Artist? = null
)

fun LastFmArtistDto.toPluginArtistData(
    artistId: String,
    mbId: String,
    artistName: String
) = PluginArtistData(
    id = artistId,
    artistName = artistName,
    description = sanitizeDescription(artist?.bio?.content?.removeHtmlAnchor() ?: DESCRIPTION_NOT_VALID),
    shortDescription = sanitizeDescription(artist?.bio?.summary?.removeHtmlAnchor() ?: DESCRIPTION_NOT_VALID),
    mbId = artist?.mbid ?: mbId,
    language = "",
    imageUrl =  artist?.image?.last()?.text ?: "",
    year = "",
    url = artist?.url ?: "",
    onTour = artist?.ontour ?: "",
    similar = artist?.similar?.artist?.mapNotNull { it.toSimilarArtist() } ?: listOf(),
    listeners = artist?.stats?.listeners ?: 0,
    playCount = artist?.stats?.playcount ?: 0,
    tags = artist?.tags?.tag?.mapNotNull { it?.name } ?: listOf(),
)
