package luci.sixsixsix.powerampache2.infoplugin.domain.usecase

import luci.sixsixsix.powerampache2.infoplugin.domain.DataInfoFetcher
import luci.sixsixsix.powerampache2.infoplugin.domain.models.PluginAlbumData
import javax.inject.Inject

class FetchAlbumInfoUseCase @Inject constructor(
    private val infoFetcher: DataInfoFetcher
) {
    operator fun invoke(
        albumId: String,
        mbId: String,
        albumTitle: String,
        artistName: String,
        callback: (PluginAlbumData?) -> Unit
    ) = infoFetcher.fetchAlbumData(
        albumId = albumId,
        albumMbId = mbId,
        albumTitle = albumTitle,
        artistName = artistName,
        callback
    )
}
