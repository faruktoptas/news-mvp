package me.toptas.rssreader.features.main

import me.toptas.rssreader.base.BasePresenter
import javax.inject.Inject

/**
 * Created by ftoptas on 28/01/17.
 */

class MainPresenter @Inject constructor() : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun loadRssFragments() {
        view?.onLoadRssFragments()
    }

}
