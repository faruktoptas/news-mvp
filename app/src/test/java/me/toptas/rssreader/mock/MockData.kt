package me.toptas.rssreader.mock

import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem
import me.toptas.rssreader.model.Feed

class MockData {
    companion object {
        const val FEED_URL = "https://mock_url"
        val RSS_FEED = RssFeed().apply {

        }

        val FEEDS = listOf(
                Feed(0, "MockTitle0", "http2://mock_url_0"),
                Feed(1, "MockTitle1", "http2://mock_url_1"),
                Feed(2, "MockTitle2", "http2://mock_url_2")
        )

        val ITEMS = listOf(
                RssItem().apply { title = "title0" },
                RssItem().apply { title = "title1" }
        )
    }
}