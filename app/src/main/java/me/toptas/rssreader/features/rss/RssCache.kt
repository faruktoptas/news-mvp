package me.toptas.rssreader.features.rss

import me.toptas.rssconverter.RssItem
import java.util.*
import javax.inject.Inject

class RssCache @Inject constructor() {

    private val contentMap = HashMap<String, List<RssItem>>()

    fun hasUrl(url: String): Boolean {
        return contentMap.containsKey(url)
    }

    fun addContent(url: String, items: List<RssItem>) {
        contentMap[url] = items
    }

    fun getContent(url: String): List<RssItem> {
        return contentMap[url]!!
    }
}