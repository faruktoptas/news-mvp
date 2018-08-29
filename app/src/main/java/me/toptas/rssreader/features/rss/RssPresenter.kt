package me.toptas.rssreader.features.rss

import me.toptas.rssconverter.RssItem
import me.toptas.rssreader.base.BasePresenter
import me.toptas.rssreader.model.Feed
import me.toptas.rssreader.network.RssResponse
import javax.inject.Inject

class RssPresenter @Inject
constructor(private val repository: RssRepository,
            private val cache: RssCache) :
        BasePresenter<RssContract.View>(), RssContract.Presenter, RssResponseListener {


    override fun loadRssItems(feed: Feed, fromCache: Boolean) {
        if (cache.hasUrl(feed.url) && fromCache) {
            view?.onRssItemsLoaded(cache.getContent(feed.url))
        } else {
            view?.showLoading()
            repository.fetchRss(feed.url, this)
        }
    }

    override fun browseRssUrl(rssItem: RssItem) {
        view?.onBrowse(rssItem)
    }


    override fun getResponse(url: String, response: RssResponse) {
        response.success?.apply {
            if (isNotEmpty()) {
                cache.addContent(url, this)
                view?.onRssItemsLoaded(this)
            }
        }

        response.error?.apply {
            view?.onFail(this)
        }

        view?.hideLoading()
    }

}