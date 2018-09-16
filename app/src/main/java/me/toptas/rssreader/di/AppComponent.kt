package me.toptas.rssreader.di

import android.app.Application
import dagger.Component
import me.toptas.rssreader.features.main.MainRepository
import me.toptas.rssreader.features.rss.RssRepository
import me.toptas.rssreader.network.RssService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by ftoptas on 24/07/18.
 */
@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class
])
interface AppComponent {

    fun app(): Application

    fun okHttpClient(): OkHttpClient

    fun retrofit(): Retrofit

    fun service(): RssService

    fun mainRepository(): MainRepository

    fun rssRepository(): RssRepository

}