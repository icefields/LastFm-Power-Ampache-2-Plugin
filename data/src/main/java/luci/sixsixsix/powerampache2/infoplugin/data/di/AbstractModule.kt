package luci.sixsixsix.powerampache2.infoplugin.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.DataInfoFetcherImpl
import luci.sixsixsix.powerampache2.infoplugin.data.local.SharedPreferencesManagerImpl
import luci.sixsixsix.powerampache2.infoplugin.domain.DataInfoFetcher
import luci.sixsixsix.powerampache2.infoplugin.domain.local.SharedPreferencesManager


@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModule {
    @Binds abstract fun bindLyricsFetcher(lyricsFetcherImpl: DataInfoFetcherImpl): DataInfoFetcher
    @Binds abstract fun bindSharedPreferencesManager(sharedPreferencesManager: SharedPreferencesManagerImpl): SharedPreferencesManager
}
