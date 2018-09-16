package me.toptas.rssreader.mocks

import android.app.Application
import dagger.Provides
import me.toptas.rssreader.di.NetworkModule
import me.toptas.rssreader.features.main.MainRepository
import me.toptas.rssreader.features.rss.*
import me.toptas.rssreader.network.RssService
import javax.inject.Singleton

class MockNetworkModule : NetworkModule() {

    @Provides
    @Singleton
    override fun provideRssRepository(service: RssService): RssRepository = MockRssRepository()

    @Provides
    @Singleton
    override fun provideMainRepository(app: Application): MainRepository = MockMainRepository()
}