package me.toptas.rssreader.mock

import me.toptas.rssreader.features.main.MainRepository
import me.toptas.rssreader.model.Feed

class MockMainRepository : MainRepository {

    override fun parseFeeds(): List<Feed> {
        return MockData.FEEDS
    }

}