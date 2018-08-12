package me.toptas.rssreader.features.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import me.toptas.rssreader.R
import me.toptas.rssreader.base.BaseActivity
import me.toptas.rssreader.di.ActivityComponent
import me.toptas.rssreader.features.chrome.ChromeTabsWrapper
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var mPresenter: MainContract.Presenter
    private lateinit var wrapper: ChromeTabsWrapper


    override val layoutResource = R.layout.activity_main

    override fun inject(component: ActivityComponent) {
        component.inject(this)
    }

    override fun init(state: Bundle?) {
        mPresenter.attach(this)
        mPresenter.loadHelloText()

        tvHello.setOnClickListener {
            mPresenter.loadHelloText()
        }
        wrapper = ChromeTabsWrapper(this)
    }

    override fun onTextLoaded(text: String) {
        tvHello.text = text
    }

    override fun onStart() {
        super.onStart()
        wrapper.bindCustomTabsService()
    }

    override fun onStop() {
        super.onStop()
        wrapper.unbindCustomTabsService()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detach()
    }


}
