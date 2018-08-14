package me.toptas.rssreader.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import me.toptas.rssreader.NewsApp
import me.toptas.rssreader.R
import me.toptas.rssreader.di.ActivityComponent
import me.toptas.rssreader.di.ActivityModule
import me.toptas.rssreader.di.DaggerActivityComponent
import me.toptas.rssreader.model.Error

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
                .activityModule(ActivityModule())
                .build())
        init(savedInstanceState)
    }

    /**
     * Initializations
     */
    protected abstract fun init(state: Bundle?)

    override fun onFail(error: Error) {
        error.message = when (error.code) {
            Error.ERROR_GENERIC -> {
                getString(R.string.err_rss_no_item)
            }
            Error.ERROR_NETWORK -> {
                getString(R.string.err_network)
            }
            else -> getString(R.string.err_rss_no_item)

        }

        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

}