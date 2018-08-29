package me.toptas.rssreader.features.rss

import me.toptas.rssconverter.RssFeed
import me.toptas.rssreader.model.Error
import me.toptas.rssreader.network.ApiCallback
import me.toptas.rssreader.network.RssResponse
import me.toptas.rssreader.network.RssService
import javax.inject.Inject

class RssRepositoryImpl @Inject constructor(private val service: RssService) : RssRepository {

    override fun fetchRss(url: String, listener: RssResponseListener) {
        service.getRss(url).enqueue(object : ApiCallback<RssFeed>() {
            override fun onSuccess(response: RssFeed) {
                listener.getResponse(url, RssResponse.success(response.items))
            }

            override fun onFail(error: Error) {
                listener.getResponse(url, RssResponse.error(error))
            }
        })
    }


}

interface RssRepository {
    fun fetchRss(url: String, listener: RssResponseListener)
}