package me.toptas.rssreader.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import me.toptas.rssreader.NewsApp
import me.toptas.rssreader.di.ActivityComponent
import me.toptas.rssreader.di.ActivityModule
import me.toptas.rssreader.di.DaggerActivityComponent

abstract class BaseActivity : AppCompatActivity(), BaseView {


    /**
     * Layout resource to be inflated
     *
     * @return layout resource
     */
    @get:LayoutRes
    protected abstract val layoutResource: Int

    abstract fun inject(component: ActivityComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        inject(DaggerActivityComponent.builder()
                .appComponent(NewsApp.component())
                .activityModule(ActivityModule(this))
                .build())
        init(savedInstanceState)
    }

    /**
     * Initializations
     */
    protected abstract fun init(state: Bundle?)

}