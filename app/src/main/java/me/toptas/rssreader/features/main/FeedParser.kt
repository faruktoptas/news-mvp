package me.toptas.rssreader.features.main

import android.content.Context
import com.google.gson.Gson
import me.toptas.rssreader.model.Feed
import me.toptas.rssreader.util.Utils
import java.util.*


class FeedParser {

    fun parseFeeds(context: Context): List<Feed> {
        val jsonString = Utils.readFromAssets(context, RSS_FILE)
        val gson = Gson()
        val feeds = gson.fromJson(jsonString, Array<Feed>::class.java)
        return Arrays.asList(*feeds)
    }

    companion object {
        private const val RSS_FILE = "rss.json"
    }
}