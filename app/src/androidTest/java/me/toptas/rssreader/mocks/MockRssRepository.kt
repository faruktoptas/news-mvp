package me.toptas.rssreader.mocks

import me.toptas.rssconverter.RssItem
import me.toptas.rssreader.features.rss.RssRepository
import me.toptas.rssreader.features.rss.RssResponseListener
import me.toptas.rssreader.model.Error
import me.toptas.rssreader.network.RssResponse

class MockRssRepository : RssRepository {

    private var lastIndex = 1
    private val count = 5

    override fun fetchRss(url: String, listener: RssResponseListener) {
        when (url) {
            SUCCESS_URL -> listener.getResponse(url, RssResponse.success(ITEMS.subList(0, lastIndex++ * count)))
            ERROR_URL -> listener.getResponse(url, RssResponse.error(Error.generic()))
        }
    }

    companion object {
        const val SUCCESS_URL = "https://github.com"
        const val ERROR_URL = "error_url"

        val ITEMS = listOf(
                RssItem().apply {
                    title = "title0"
                    link = SUCCESS_URL
                },
                RssItem().apply {
                    title = "title1"
                    link = SUCCESS_URL
                },
                RssItem().apply {
                    title = "title2"
                    link = SUCCESS_URL
                },
                RssItem().apply {
                    title = "title3"
                    link = SUCCESS_URL
                },
                RssItem().apply {
                    title = "title4"
                    link = SUCCESS_URL
                },
                RssItem().apply {
                    title = "title5"
                    link = SUCCESS_URL
                },
                RssItem().apply {
                    title = "title6"
                    link = SUCCESS_URL
                },
                RssItem().apply {
                    title = "title7"
                    link = SUCCESS_URL
                },
                RssItem().apply {
                    title = "title8"
                    link = SUCCESS_URL
                },
                RssItem().apply {
                    title = "title9"
                    link = SUCCESS_URL
                }

        )
    }

}