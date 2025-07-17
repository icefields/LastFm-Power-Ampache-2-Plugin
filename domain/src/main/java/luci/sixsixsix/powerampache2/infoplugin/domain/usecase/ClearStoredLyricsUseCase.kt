package luci.sixsixsix.powerampache2.infoplugin.domain.usecase

import luci.sixsixsix.powerampache2.infoplugin.domain.DataInfoFetcher
import javax.inject.Inject

class ClearStoredLyricsUseCase @Inject constructor(
    private val infoFetcher: DataInfoFetcher
) {
    suspend operator fun invoke() = {}// lyricsFetcher.clearStoredLyrics()
}
