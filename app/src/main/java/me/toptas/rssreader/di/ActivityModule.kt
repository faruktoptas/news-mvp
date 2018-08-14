package me.toptas.rssreader.di

import dagger.Module
import dagger.Provides
import me.toptas.rssreader.features.main.MainContract
import me.toptas.rssreader.features.main.MainPresenter
import me.toptas.rssreader.features.rss.*
import me.toptas.rssreader.network.RssService

/**
 * Created by ftoptas on 24/07/18.
 */
@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideMainPresenter(): MainContract.Presenter = MainPresenter()


    @Provides
    @ActivityScope
    fun provideRssRepository(service: RssService): RssRepository = RssRepositoryImpl(service)

    @Provides
    @ActivityScope
    fun provideRssPresenter(repository: RssRepository,
                            cache: RssCache): RssContract.Presenter = RssPresenter(repository, cache)

}