package me.toptas.rssreader.features.main

import me.toptas.rssreader.base.BaseMvpPresenter
import me.toptas.rssreader.base.BaseView
import me.toptas.rssreader.model.Feed

/**
 * Created by ftoptas on 28/01/17.
 */

interface MainContract {

    // User actions. Presenter will implement
    interface Presenter : BaseMvpPresenter<View> {
        fun loadRssFragments()

    }

    // Action callbacks. Activity/Fragment will implement
    interface View : BaseView {
        fun onLoadRssFragments(feeds: List<Feed>)
    }

}
