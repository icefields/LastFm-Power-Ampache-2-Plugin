package luci.sixsixsix.powerampache2.lyricsplugin.domain.usecase

import luci.sixsixsix.powerampache2.lyricsplugin.domain.DataInfoFetcher
import javax.inject.Inject

class ClearStoredLyricsUseCase @Inject constructor(
    private val infoFetcher: DataInfoFetcher
) {
    suspend operator fun invoke() = {}// lyricsFetcher.clearStoredLyrics()
}
