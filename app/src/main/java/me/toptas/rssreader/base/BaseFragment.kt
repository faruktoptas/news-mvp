package me.toptas.rssreader.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.toptas.rssreader.NewsApp
import me.toptas.rssreader.di.ActivityComponent
import me.toptas.rssreader.di.ActivityModule
import me.toptas.rssreader.di.DaggerActivityComponent

abstract class BaseFragment : Fragment(), BaseView, AsyncCallbackView {

    @get:LayoutRes
    protected abstract val layoutResource: Int

    abstract fun inject(component: ActivityComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject(DaggerActivityComponent.builder()
                .appComponent(NewsApp.component())
                .activityModule(ActivityModule())
                .build())

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResource, container, false)
    }
}