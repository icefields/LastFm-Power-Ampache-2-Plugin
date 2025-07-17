package luci.sixsixsix.powerampache2.infoplugin.domain.usecase

import luci.sixsixsix.powerampache2.infoplugin.domain.DataInfoFetcher
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(
    private val infoFetcher: DataInfoFetcher
) {
    suspend operator fun invoke(
        username: String, password: String
    ) = infoFetcher.authenticate(username, password)
}