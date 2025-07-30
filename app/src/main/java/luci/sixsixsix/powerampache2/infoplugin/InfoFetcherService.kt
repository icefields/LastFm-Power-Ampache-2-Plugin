package luci.sixsixsix.powerampache2.infoplugin

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import luci.sixsixsix.powerampache2.infoplugin.common.ACTION_ALBUM
import luci.sixsixsix.powerampache2.infoplugin.common.ACTION_ARTIST
import luci.sixsixsix.powerampache2.infoplugin.common.ACTION_SONG
import luci.sixsixsix.powerampache2.infoplugin.common.KEY_ACTION
import luci.sixsixsix.powerampache2.infoplugin.common.KEY_ID
import luci.sixsixsix.powerampache2.infoplugin.common.KEY_MBID
import luci.sixsixsix.powerampache2.infoplugin.common.KEY_REQUEST_ALBUM_TITLE
import luci.sixsixsix.powerampache2.infoplugin.common.KEY_REQUEST_ARTIST_NAME
import luci.sixsixsix.powerampache2.infoplugin.common.KEY_REQUEST_JSON
import luci.sixsixsix.powerampache2.infoplugin.common.KEY_REQUEST_SONG_TITLE
import luci.sixsixsix.powerampache2.infoplugin.domain.usecase.FetchAlbumInfoUseCase
import luci.sixsixsix.powerampache2.infoplugin.domain.usecase.FetchArtistInfoUseCase
import luci.sixsixsix.powerampache2.infoplugin.domain.usecase.FetchSongInfoUseCase
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class InfoFetcherService : Service() {
    private val gson = Gson()
    @Inject lateinit var fetchSongInfoUseCase: FetchSongInfoUseCase
    @Inject lateinit var fetchAlbumInfoUseCase: FetchAlbumInfoUseCase
    @Inject lateinit var fetchArtistInfoUseCase: FetchArtistInfoUseCase

    private val messenger = Messenger(object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val requestStr = msg.data.getString(KEY_REQUEST_JSON) ?: return
            val request = JSONObject(requestStr)

            val action = request.optString(KEY_ACTION)
            val mbId = request.optString(KEY_MBID)
            val id = request.optString(KEY_ID)
            val song = request.optString(KEY_REQUEST_SONG_TITLE)
            val albumTitle = request.optString(KEY_REQUEST_ALBUM_TITLE)
            val artistName = request.optString(KEY_REQUEST_ARTIST_NAME)

            val replyTo = msg.replyTo ?: return

            when(action) {
                ACTION_SONG -> fetchSongInfoUseCase(
                    mbId = mbId,
                    songId = id,
                    songTitle = song,
                    albumTitle =  albumTitle,
                    artistName =  artistName
                ) { songData ->
                    try { // avoid android.os.DeadObjectException
                        replyTo.send(getReplyMessage(songData))
                    } catch (e: Exception) { e.printStackTrace() }
                }
                ACTION_ALBUM -> fetchAlbumInfoUseCase(
                    albumId = id,
                    mbId = mbId,
                    albumTitle = albumTitle,
                    artistName = artistName,
                ) { albumData ->
                    try { replyTo.send(getReplyMessage(albumData)) }
                    catch (e: Exception) { e.printStackTrace() }
                }
                ACTION_ARTIST -> fetchArtistInfoUseCase(
                    artistId = id,
                    mbId = mbId,
                    artistName = artistName
                ) { artistData ->
                    try { replyTo.send(getReplyMessage(artistData)) }
                    catch (e: Exception) { e.printStackTrace() }
                }
                else -> println("request action missing")
            }

        }
    })

    private fun getReplyMessage(jsonData: Any?) = Message.obtain().apply {
        data = Bundle().apply {
            putString(KEY_REQUEST_JSON, gson.toJson(jsonData))
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return messenger.binder
    }
}
