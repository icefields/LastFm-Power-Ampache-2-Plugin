package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.local.PluginDatabase
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.local.entity.toPluginAlbumData
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.local.entity.toPluginAlbumEntity
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.local.entity.toPluginArtistData
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.local.entity.toPluginArtistEntity
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.album.toPluginAlbumData
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.artist.toPluginArtistData
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.song.toPluginSongData
import luci.sixsixsix.powerampache2.lyricsplugin.domain.DataInfoFetcher
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.PluginAlbumData
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.PluginArtistData
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.PluginSongData
import javax.inject.Inject

class DataInfoFetcherImpl @Inject constructor(
    private val api: MainNetwork,
    private val db: PluginDatabase,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val applicationCoroutineScope: CoroutineScope
): DataInfoFetcher {
    private var fetchJob: Job? = null
    private var fetchAlbumJob: Job? = null
    private val gson = Gson()

    override fun fetchSongData(
        songId: String,
        songMbId: String,
        songTitle: String,
        albumTitle: String,
        artistName: String,
        callback: (PluginSongData?) -> Unit
    ) {
        fetchJob?.cancel()
        fetchJob = applicationCoroutineScope.launch {
            val songDb = null//db.dao.getSongLyrics(songTitle = songTitle, artistName = artistName)
            if(songDb != null) {
                callback(null) //lyricsDb.toSongLyrics())
            } else {
                val songInfo = try {
                    api.getSongInfo(
                        track = songTitle,
                        mbId = if (songMbId.isNotBlank()) songMbId else null,
                        artist = artistName
                    ).toPluginSongData(
                        songId = songId,
                        albumName = albumTitle,
                        artistName = artistName,
                        mbId = songMbId,
                        songName = songTitle
                    ).also {
                        // update db
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
                callback(songInfo)
            }
        }
    }

    override fun fetchAlbumData(
        albumId: String,
        albumMbId: String,
        albumTitle: String,
        artistName: String,
        callback: (PluginAlbumData?) -> Unit
    ) {
        fetchAlbumJob?.cancel()
        fetchAlbumJob = applicationCoroutineScope.launch {
            val albumDb = db.dao.getAlbum(albumMbId = albumMbId, albumName = albumTitle, artistName = artistName)
            if(albumDb != null) {
                callback(albumDb.toPluginAlbumData())
                // request the album to refresh db data
                requestAlbum(
                    albumId = albumId,
                    albumTitle = albumTitle,
                    albumMbId = albumMbId,
                    artistName = artistName
                )
            } else {
                val albumInfo = requestAlbum(
                    albumId = albumId,
                    albumTitle = albumTitle,
                    albumMbId = albumMbId,
                    artistName = artistName
                )
                callback(albumInfo)
            }
        }
    }

    override fun fetchArtistData(
        artistId: String,
        artistMbId: String,
        artistName: String,
        callback: (PluginArtistData?) -> Unit
    ) {
        fetchAlbumJob?.cancel()
        fetchAlbumJob = applicationCoroutineScope.launch {
            val artistDb = db.dao.getArtist(artistMbId = artistMbId, artistName = artistName)
            if(artistDb != null) {
                callback(artistDb.toPluginArtistData(gson))
                // request the artist to refresh db data
                requestArtist(
                    artistMbId = artistMbId,
                    artistName = artistName,
                    artistId = artistId
                )
            } else {
                val artistInfo = requestArtist(
                    artistMbId = artistMbId,
                    artistName = artistName,
                    artistId = artistId
                )
                callback(artistInfo)
            }
        }
    }

    private suspend fun requestAlbum(
        albumId: String,
        albumMbId: String,
        albumTitle: String,
        artistName: String,
    ): PluginAlbumData? = try {
        api.getAlbumInfo(
            album = albumTitle,
            mbId = if (albumMbId.isNotBlank()) albumMbId else null,
            artist = artistName
        ).toPluginAlbumData(
            albumId = albumId,
            albumName = albumTitle,
            artistName = artistName,
            mbId = albumMbId
        ).also {
            db.dao.updateAlbums(it.toPluginAlbumEntity())
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    private suspend fun requestArtist(
        artistId: String,
        artistMbId: String,
        artistName: String,
    ): PluginArtistData? = try {
        api.getArtistInfo(
            mbId = if (artistMbId.isNotBlank()) artistMbId else null,
            artist = artistName
        ).toPluginArtistData(
            artistId = artistId,
            artistName = artistName,
            mbId = artistMbId
        ).also {
            db.dao.updateArtist(it.toPluginArtistEntity(gson))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    override suspend fun clearStoredData() {
        db.dao.clearAlbums()
    }
}

/*
    override fun fetchLyrics(
        songTitle: String,
        albumTitle: String,
        artistName: String,
        callback: (SongLyrics?) -> Unit
    ) {
        fetchJob?.cancel()
        fetchJob = applicationCoroutineScope.launch {
            val lyricsDb = db.dao.getSongLyrics(songTitle = songTitle, artistName = artistName)
            if(lyricsDb != null) {
                callback(lyricsDb.toSongLyrics())
            } else {
                val lyrics = try {
                    api.getLyrics(
                        bearerToken = "Bearer ${sharedPreferencesManager.token}",
                        songTitle = songTitle
                    ).toSongGenius(songTitle, artistName).also {
                        val isLyrics = it.lyrics.isNotBlank() || it.lyricsUrl.isNotBlank()
                        if (isLyrics && songTitle.isNotBlank() && artistName.isNotBlank()) {
                            db.dao.updateLyrics(it.toLyricsEntity(artistName = artistName, songTitle = songTitle))
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
                callback(lyrics)
            }
        }
    }
 */