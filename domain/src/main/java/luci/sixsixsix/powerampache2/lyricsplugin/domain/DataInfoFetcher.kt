package luci.sixsixsix.powerampache2.lyricsplugin.domain

import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.PluginAlbumData
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.PluginArtistData
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.PluginSongData

interface DataInfoFetcher {
    fun fetchSongData(
        songId: String,
        songMbId: String,
        songTitle: String,
        albumTitle: String,
        artistName: String,
        callback: (PluginSongData?) -> Unit
    )

    fun fetchAlbumData(
        albumId: String,
        albumMbId: String,
        albumTitle: String,
        artistName: String,
        callback: (PluginAlbumData?) -> Unit
    )

    fun fetchArtistData(
        artistId: String,
        artistMbId: String,
        artistName: String,
        callback: (PluginArtistData?) -> Unit
    )

    suspend fun clearStoredData()
}
