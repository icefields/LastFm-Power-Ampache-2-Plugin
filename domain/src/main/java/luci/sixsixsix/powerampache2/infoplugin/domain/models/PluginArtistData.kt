package luci.sixsixsix.powerampache2.infoplugin.domain.models

data class PluginArtistData(
    val id: String,
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
    val similar: List<SimilarArtist>,
    val listeners: Int,
    val playCount: Int,
    val tags: List<String>
)

data class SimilarArtist(
    val name: String,
    val url: String,
    val image: String,
    val mbId: String
)
