package me.toptas.rssreader.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ftoptas on 24/07/18.
 */
@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    internal fun provideApplication() = app
}