package me.toptas.rssreader.network

import me.toptas.rssconverter.RssFeed
import me.toptas.rssreader.model.Error

/**
 * Created by ftoptas on 24/07/18.
 */
data class RssResponse(val success: RssFeed? = null, val error: Error? = null) {

    companion object {
        fun success(data: RssFeed): RssResponse = RssResponse(data, null)

        fun error(error: Error) = RssResponse(null, error)
    }
}