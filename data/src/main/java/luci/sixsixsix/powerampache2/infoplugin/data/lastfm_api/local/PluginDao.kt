package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.local.entity.PluginAlbumEntity
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.local.entity.PluginArtistEntity


@Dao
interface PluginDao {
    @Query("""SELECT * FROM PluginAlbumEntity""")
    fun getAllAlbums(): Flow<List<PluginAlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAlbums(lyricsEntity: PluginAlbumEntity)

    @Query("DELETE FROM PluginAlbumEntity")
    suspend fun clearAlbums()

    @Query("""SELECT * FROM PluginAlbumEntity 
        WHERE (:albumMbId != '' AND LOWER(mbId) == LOWER(:albumMbId))
        OR (LOWER(albumName) == LOWER(:albumName) AND LOWER(artistName) == LOWER(:artistName))""")
    suspend fun getAlbum(albumMbId: String, albumName: String, artistName: String): PluginAlbumEntity?


    @Query("""SELECT * FROM PluginArtistEntity""")
    fun getAllArtists(): Flow<List<PluginArtistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateArtist(lyricsEntity: PluginArtistEntity)

    @Query("DELETE FROM PluginArtistEntity")
    suspend fun clearArtists()

    @Query("""SELECT * FROM PluginArtistEntity 
        WHERE (:artistMbId != '' AND LOWER(mbId) == LOWER(:artistMbId))
        OR LOWER(artistName) == LOWER(:artistName)""")
    suspend fun getArtist(artistMbId: String, artistName: String): PluginArtistEntity?
}
