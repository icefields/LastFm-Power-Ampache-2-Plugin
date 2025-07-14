package luci.sixsixsix.powerampache2.lyricsplugin.domain.usecase

import luci.sixsixsix.powerampache2.lyricsplugin.domain.DataInfoFetcher
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.PluginArtistData
import javax.inject.Inject

class FetchArtistInfoUseCase @Inject constructor(
    private val infoFetcher: DataInfoFetcher
) {
    operator fun invoke(
        artistId: String,
        mbId: String,
        artistName: String,
        callback: (PluginArtistData?) -> Unit
    ) = infoFetcher.fetchArtistData(
        artistId = artistId,
        artistMbId = mbId,
        artistName = artistName,
        callback
    )
}
