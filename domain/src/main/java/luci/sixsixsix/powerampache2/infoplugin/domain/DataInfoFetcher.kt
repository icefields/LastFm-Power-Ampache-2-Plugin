package luci.sixsixsix.powerampache2.infoplugin.domain

import luci.sixsixsix.powerampache2.infoplugin.domain.models.PluginAlbumData
import luci.sixsixsix.powerampache2.infoplugin.domain.models.PluginArtistData
import luci.sixsixsix.powerampache2.infoplugin.domain.models.PluginSongData

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

    suspend fun authenticate(username: String, password: String)

    suspend fun clearStoredData()
}
