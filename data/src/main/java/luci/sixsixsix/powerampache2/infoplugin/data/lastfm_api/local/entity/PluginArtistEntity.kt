package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.EMPTY_TAGS
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.parseImage
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.parseTags
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.common.tagsToString
import luci.sixsixsix.powerampache2.infoplugin.domain.models.PluginArtistData
import luci.sixsixsix.powerampache2.infoplugin.domain.models.SimilarArtist

@Entity
data class PluginArtistEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "ampacheId", defaultValue = "0")
    val ampacheId: String,
    val artistName: String,
    val description: String,
    val shortDescription: String,
    val mbId: String,
    val language: String,
    val imageUrl: String,
    val year: String,
    val url: String,
    // val links // TODO: add links
    val onTour: String,
    val similar: String,
    val listeners: Int,
    val playCount: Int,
    @ColumnInfo(name = "tags", defaultValue = EMPTY_TAGS)
    val tags: String
)

data class SimilarWrapper(val similar: List<SimilarArtist>)

fun parseSimilarArtists(similarStr: String, gson: Gson): List<SimilarArtist> = try {
    gson.fromJson<SimilarWrapper>(similarStr, SimilarWrapper::class.java).similar
} catch (e: Exception) {
    listOf()
}

fun similarListToString(similarList: List<SimilarArtist>, gson: Gson): String =
    gson.toJson(SimilarWrapper(similarList))

fun PluginArtistEntity.toPluginArtistData(gson: Gson) = PluginArtistData(
    id = ampacheId,
    artistName = artistName,
    description = description,
    shortDescription = shortDescription,
    mbId = mbId,
    language = language,
    imageUrl = parseImage(imageUrl),
    year = year,
    url = url,
    onTour = onTour,
    similar = parseSimilarArtists(similar, gson = gson),
    listeners = listeners,
    playCount = playCount,
    tags = parseTags(tags)
)

fun PluginArtistData.toPluginArtistEntity(gson: Gson) = PluginArtistEntity(
    id = artistName.trim().lowercase(),
    ampacheId = id,
    artistName = artistName,
    description = description,
    shortDescription = shortDescription,
    mbId = mbId,
    language = language,
    imageUrl = parseImage(imageUrl),
    year = year,
    url = url,
    onTour = onTour,
    similar = similarListToString(similar, gson = gson),
    listeners = listeners,
    playCount = playCount,
    tags = tagsToString(tags)
)
