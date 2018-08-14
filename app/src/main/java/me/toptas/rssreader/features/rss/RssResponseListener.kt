package me.toptas.rssreader.features.rss

import me.toptas.rssreader.network.RssResponse

/**
 * Created by ftoptas on 24/07/18.
 */
interface RssResponseListener {
    fun getResponse(url: String, response: RssResponse)
}