package me.toptas.rssreader

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import me.toptas.rssreader.di.AppComponent
import me.toptas.rssreader.di.AppModule
import me.toptas.rssreader.di.DaggerAppComponent

class NewsApp : Application() {


    companion object {
        lateinit var instance: Application
        private var appComponent: AppComponent? = null

        fun component(): AppComponent {
            return appComponent!!
        }

        fun setComponent(component: AppComponent) {
            appComponent = component
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LeakCanary.install(this)
        setComponent(DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build())

    }


}