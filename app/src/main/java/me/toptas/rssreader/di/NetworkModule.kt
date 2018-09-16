package me.toptas.rssreader.di

import android.app.Application
import dagger.Module
import dagger.Provides
import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssreader.features.main.MainRepository
import me.toptas.rssreader.features.main.MainRepositoryImpl
import me.toptas.rssreader.features.rss.RssRepository
import me.toptas.rssreader.features.rss.RssRepositoryImpl
import me.toptas.rssreader.network.RssService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by ftoptas on 24/07/18.
 */
@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
            .baseUrl("http://example.com")
            .addConverterFactory(RssConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(RssService::class.java)

    @Provides
    @Singleton
    open fun provideRssRepository(service: RssService): RssRepository = RssRepositoryImpl(service)

    @Provides
    @Singleton
    open fun provideMainRepository(app: Application): MainRepository = MainRepositoryImpl(app)


}