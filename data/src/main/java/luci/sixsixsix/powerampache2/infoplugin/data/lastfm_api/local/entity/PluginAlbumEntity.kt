package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.EMPTY_TAGS
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.parseImage
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.parseTags
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.tagsToString
import luci.sixsixsix.powerampache2.infoplugin.domain.models.PluginAlbumData

@Entity
data class PluginAlbumEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "ampacheId", defaultValue = "0")
    val ampacheId: String,
    val albumName: String,
    val artistName: String,
    @ColumnInfo(name = "description", defaultValue = "")
    val description: String,
    val shortDescription: String,
    val mbId: String,
    val language: String,
    val lyrics: String,
    val artistMbId: String,
    val albumArtistMbId: String,
    val imageUrl: String,
    val year: String,
    @ColumnInfo(name = "tags", defaultValue = EMPTY_TAGS)
    val tags: String,// List<String>,
    val rank: Int,
    val url: String,
    val imageArtist: String,
    val urlArtist: String,
    val duration: Int,
    val listeners: Int,
    val playCount: Int,
    //val tracks: String//List<AlbumTrack>,
)

fun PluginAlbumEntity.toPluginAlbumData() = PluginAlbumData(
    id = ampacheId,
    albumName = albumName,
    artistName = artistName,
    description = description,
    shortDescription = shortDescription,
    mbId = mbId,
    language = language,
    lyrics = lyrics,
    artistMbId = artistMbId,
    albumArtistMbId = albumArtistMbId,
    imageUrl = parseImage(imageUrl),
    year = year,
    tags = parseTags(tags),
    rank = rank,
    url = url,
    imageArtist = parseImage(imageArtist),
    urlArtist = urlArtist,
    duration = duration,
    listeners = listeners,
    playCount = playCount,
    tracks = listOf() // TODO
)

fun PluginAlbumData.toPluginAlbumEntity() = PluginAlbumEntity(
    id = "${artistName.lowercase().trim()}|${albumName.lowercase().trim()}",
    ampacheId = id,
    albumName = albumName,
    artistName = artistName,
    description = description,
    shortDescription = shortDescription,
    mbId = mbId,
    language = language,
    lyrics = lyrics,
    artistMbId = artistMbId,
    albumArtistMbId = albumArtistMbId,
    imageUrl = parseImage(imageUrl),
    year = year,
    tags = tagsToString(tags),
    rank = rank,
    url = url,
    imageArtist = parseImage(imageArtist),
    urlArtist = urlArtist,
    duration = duration,
    listeners = listeners,
    playCount = playCount,
    //tracks = listOf() // TODO
)

