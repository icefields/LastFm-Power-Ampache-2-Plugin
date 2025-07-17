package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserEntity(
    @PrimaryKey val id: String,
    val username: String,
    val password: String,
    val sessionSignature: String,
    val sessionKey: String,
)
