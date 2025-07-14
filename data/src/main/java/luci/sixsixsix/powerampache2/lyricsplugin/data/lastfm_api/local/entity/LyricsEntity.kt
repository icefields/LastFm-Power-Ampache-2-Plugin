package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LyricsEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "lyricsUrl", defaultValue = "")
    val lyricsUrl: String = "",
    @ColumnInfo(name = "lyrics", defaultValue = "")
    val lyrics: String = "",
    @ColumnInfo(name = "artistName", defaultValue = "")
    val artistName: String = "",
    @ColumnInfo(name = "songTitle", defaultValue = "")
    val songTitle: String = ""
)
