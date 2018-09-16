package me.toptas.rssreader.mocks

import me.toptas.rssreader.features.main.MainRepository
import me.toptas.rssreader.model.Feed

class MockMainRepository : MainRepository {

    override fun parseFeeds() = FEEDS

    companion object {
        val FEEDS = listOf(
                Feed(0, "title1", MockRssRepository.SUCCESS_URL),
                Feed(1, "title2", MockRssRepository.ERROR_URL)
        )
    }
}