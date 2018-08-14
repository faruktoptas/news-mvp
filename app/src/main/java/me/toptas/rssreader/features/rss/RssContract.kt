package me.toptas.rssreader.features.rss

import me.toptas.rssconverter.RssItem
import me.toptas.rssreader.base.AsyncCallbackView
import me.toptas.rssreader.base.BaseMvpPresenter
import me.toptas.rssreader.base.BaseView
import me.toptas.rssreader.model.Feed

interface RssContract {

    // User actions. Presenter will implement
    interface Presenter : BaseMvpPresenter<View> {
        fun loadRssItems(feed: Feed, fromCache: Boolean)
        fun browseRssUrl(rssItem: RssItem)
    }

    // Action callbacks. Activity/Fragment will implement
    interface View : BaseView, AsyncCallbackView {
        fun onRssItemsLoaded(rssItems: List<RssItem>)
        fun onBrowse(rssItem: RssItem)
    }
}