package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.artist.Bio
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.artist.Similar
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models.artist.Stats
import luci.sixsixsix.powerampache2.infoplugin.domain.models.SimilarArtist


@Keep
data class Artist(
    @SerializedName("mbid")
    val mbid: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null,

    // THE FOLLOWING IS AVAILABLE WITH THE getArtistInfo call
    @SerializedName("bio")
    val bio: Bio? = Bio(),
    @SerializedName("image")
    val image: List<Image>? = listOf(),
    @SerializedName("ontour")
    val ontour: String? = "",
    @SerializedName("similar")
    val similar: Similar? = Similar(),
    @SerializedName("stats")
    val stats: Stats? = Stats(),
    @SerializedName("streamable")
    val streamable: String? = "",
    @SerializedName("tags")
    val tags: Tags? = Tags(),
)

fun Artist.toSimilarArtist() = SimilarArtist(
    name = name ?: "",
    url = url ?: "",
    image = image?.last()?.text ?: "",
    mbId = mbid ?: ""
)
