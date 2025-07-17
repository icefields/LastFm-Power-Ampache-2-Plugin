package luci.sixsixsix.powerampache2.infoplugin.domain.usecase

import luci.sixsixsix.powerampache2.infoplugin.domain.DataInfoFetcher
import luci.sixsixsix.powerampache2.infoplugin.domain.models.PluginSongData
import javax.inject.Inject

class FetchSongInfoUseCase @Inject constructor(
    private val infoFetcher: DataInfoFetcher
) {
    operator fun invoke(
        songId: String,
        mbId: String,
        songTitle: String,
        albumTitle: String,
        artistName: String,
        callback: (PluginSongData?) -> Unit
    ) = infoFetcher.fetchSongData(
        songId = songId,
        songMbId = mbId,
        songTitle = songTitle,
        albumTitle = albumTitle,
        artistName = artistName,
        callback
    )
}
