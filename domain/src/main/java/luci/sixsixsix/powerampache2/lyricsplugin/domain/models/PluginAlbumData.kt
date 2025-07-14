package luci.sixsixsix.powerampache2.lyricsplugin.domain.models


data class PluginAlbumData(
    val id: String,
    val albumName: String,
    val artistName: String,
    val description: String,
    val shortDescription: String,
    val mbId: String,
    val language: String,
    val lyrics: String,
    val artistMbId: String,
    val albumArtistMbId: String,
    val imageUrl: String,
    val year: String,
    val tags: List<String>,
    val rank: Int,
    val url: String,
    val imageArtist: String,
    val urlArtist: String,
    val duration: Int,
    val listeners: Int,
    val playCount: Int,
    val tracks: List<AlbumTrack>,
)

data class AlbumTrack(
    val mbId: String,
    val title: String,
    val duration: Int,
    val url: String,
    val songArtistName: String,
    val songArtistMbId: String
)
