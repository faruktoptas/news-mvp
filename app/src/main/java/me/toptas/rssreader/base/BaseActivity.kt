package me.toptas.rssreader.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), BaseView {


    /**
     * Layout resource to be inflated
     *
     * @return layout resource
     */
    @get:LayoutRes
    protected abstract val layoutResource: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        init(savedInstanceState)
    }

    /**
     * Initializations
     */
    protected abstract fun init(state: Bundle?)

}