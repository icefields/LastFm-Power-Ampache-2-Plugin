package luci.sixsixsix.powerampache2.lyricsplugin.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.DataInfoFetcherImpl
import luci.sixsixsix.powerampache2.lyricsplugin.data.local.SharedPreferencesManagerImpl
import luci.sixsixsix.powerampache2.lyricsplugin.domain.DataInfoFetcher
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager


@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModule {
    @Binds abstract fun bindLyricsFetcher(lyricsFetcherImpl: DataInfoFetcherImpl): DataInfoFetcher
    @Binds abstract fun bindSharedPreferencesManager(sharedPreferencesManager: SharedPreferencesManagerImpl): SharedPreferencesManager
}
