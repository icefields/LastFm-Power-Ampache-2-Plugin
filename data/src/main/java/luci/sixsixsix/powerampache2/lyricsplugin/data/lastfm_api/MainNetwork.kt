package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api

import luci.sixsixsix.powerampache2.lyricsplugin.data.common.AMPACHE_USER_AGENT
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.common.LASTFM_API_KEY
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.album.LastFmAlbumDto
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.artist.LastFmArtistDto
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.models.song.LastFmSongDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MainNetwork {
    @Headers("User-Agent: $AMPACHE_USER_AGENT")
    @GET("2.0/?method=track.getInfo&format=json")
    suspend fun getSongInfo(
        @Query("api_key") apiKey: String = LASTFM_API_KEY,
        @Query("track") track: String,
        @Query("artist") artist: String,
        @Query("mbid") mbId: String? = null
    ): LastFmSongDto

    @Headers("User-Agent: $AMPACHE_USER_AGENT")
    @GET("2.0/?method=album.getInfo&format=json")
    suspend fun getAlbumInfo(
        @Query("api_key") apiKey: String = LASTFM_API_KEY,
        @Query("album") album: String,
        @Query("artist") artist: String,
        @Query("autocorrect") autocorrect: Int = 1,
        @Query("mbid") mbId: String? = null,
        @Query("lang") lang: String? = "en", // The language to return the biography in, expressed as an ISO 639 alpha-2 code.
    ): LastFmAlbumDto

    /**
     * artist (Required (unless mbid)] : The artist name
     * mbid (Optional) : The musicbrainz id for the artist
     * lang (Optional) : The language to return the biography in, expressed as an ISO 639 alpha-2 code.
     * autocorrect[0|1] (Optional) : Transform misspelled artist names into correct artist names, returning the correct version instead. The corrected artist name will be returned in the response.
     * username (Optional) : The username for the context of the request. If supplied, the user's playcount for this artist is included in the response.
     * api_key (Required) : A Last.fm API key.
     */
    @Headers("User-Agent: $AMPACHE_USER_AGENT")
    @GET("2.0/?method=artist.getInfo&format=json")
    suspend fun getArtistInfo(
        @Query("api_key") apiKey: String = LASTFM_API_KEY,
        @Query("artist") artist: String,
        @Query("mbid") mbId: String? = null,
        @Query("username") username: String? = null,
        @Query("lang") lang: String? = "en", // The language to return the biography in, expressed as an ISO 639 alpha-2 code.
    ): LastFmArtistDto
}
