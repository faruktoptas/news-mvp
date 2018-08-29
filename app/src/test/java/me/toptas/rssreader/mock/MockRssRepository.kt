package me.toptas.rssreader.mock

import me.toptas.rssreader.features.rss.RssRepository
import me.toptas.rssreader.features.rss.RssResponseListener
import me.toptas.rssreader.mock.MockData.Companion.FEED_URL
import me.toptas.rssreader.mock.MockData.Companion.ITEMS
import me.toptas.rssreader.model.Error
import me.toptas.rssreader.network.RssResponse

class MockRssRepository(private val isSuccessful: Boolean) : RssRepository {

    override fun fetchRss(url: String, listener: RssResponseListener) {
        if (isSuccessful) {
            listener.getResponse(FEED_URL, RssResponse.success(ITEMS))
        } else {
            listener.getResponse(FEED_URL, RssResponse.error(Error.generic()))
        }
    }


}