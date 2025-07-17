package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import luci.sixsixsix.powerampache2.infoplugin.data.common.DATABASE_VERSION
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.local.entity.PluginAlbumEntity
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.local.entity.PluginArtistEntity
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.local.entity.UserEntity

@Database(
    entities = [
        PluginAlbumEntity::class,
        PluginArtistEntity::class,
        UserEntity::class
    ],
    version = DATABASE_VERSION,
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
                AutoMigration(from = 4, to = 5)
    ],
    exportSchema = true
)
abstract class PluginDatabase: RoomDatabase() {
    abstract val dao: PluginDao
}
