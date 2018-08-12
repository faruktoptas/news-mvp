package me.toptas.rssreader.di

import android.app.Application
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ftoptas on 24/07/18.
 */
@Singleton
@Component(modules = [
    AppModule::class

])
interface AppComponent {

    fun app(): Application


}