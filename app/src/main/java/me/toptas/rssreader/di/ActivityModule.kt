package me.toptas.rssreader.di

import android.app.Application
import dagger.Module
import dagger.Provides
import me.toptas.rssreader.features.main.MainContract
import me.toptas.rssreader.features.main.MainPresenter
import me.toptas.rssreader.features.main.MainRepository
import me.toptas.rssreader.features.main.MainRepositoryImpl
import me.toptas.rssreader.features.rss.*
import me.toptas.rssreader.network.RssService

/**
 * Created by ftoptas on 24/07/18.
 */
@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideMainPresenter(repository: MainRepository): MainContract.Presenter = MainPresenter(repository)

    @Provides
    @ActivityScope
    fun provideRssPresenter(repository: RssRepository,
                            cache: RssCache): RssContract.Presenter = RssPresenter(repository, cache)

}