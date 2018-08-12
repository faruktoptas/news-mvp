package me.toptas.rssreader.di

import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides
import me.toptas.rssreader.features.main.MainContract
import me.toptas.rssreader.features.main.MainPresenter

/**
 * Created by ftoptas on 24/07/18.
 */
@Module
class ActivityModule(private val activity: FragmentActivity) {

    @Provides
    @ActivityScope
    fun provideMainPresenter(): MainContract.Presenter = MainPresenter()

}