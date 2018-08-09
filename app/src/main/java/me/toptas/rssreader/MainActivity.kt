package me.toptas.rssreader

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import me.toptas.rssreader.base.BaseActivity

class MainActivity : BaseActivity(), MainContract.View {


    private lateinit var mPresenter: MainContract.Presenter

    override val layoutResource = R.layout.activity_main

    override fun init(state: Bundle?) {
        mPresenter = MainPresenter()
        mPresenter.attach(this)
        mPresenter.loadHelloText()

        tvHello.setOnClickListener {
            mPresenter.loadHelloText()
        }
    }

    override fun onTextLoaded(text: String) {
        tvHello.text = text
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detach()
    }


}
