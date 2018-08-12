package me.toptas.rssreader.di

import dagger.Component
import me.toptas.rssreader.features.main.MainActivity

/**
 * Created by ftoptas on 24/07/18.
 */
@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}