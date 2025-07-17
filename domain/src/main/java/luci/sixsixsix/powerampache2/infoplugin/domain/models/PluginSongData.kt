package luci.sixsixsix.powerampache2.infoplugin.domain.models

data class PluginSongData(
    val id: String,
    val title: String = "",
    val albumName: String = "",
    val artistName: String = "",
    val description: String = "",
    val shortDescription: String = "",
    val mbId: String = "",
    val language: String = "",
    val lyrics: String = "",
    val albumMbId: String = "",
    val artistMbId: String = "",
    val imageUrl: String = "",
    val year: String = "",
    val url: String,
    val artistAlbum: String,
    val imageAlbum: String,
    val urlAlbum: String,
    val imageArtist: String,
    val urlArtist: String,
    val duration: Int,
    val listeners: Int,
    val playCount: Int,
    val topTags: List<String>,
    val position: Int
)
