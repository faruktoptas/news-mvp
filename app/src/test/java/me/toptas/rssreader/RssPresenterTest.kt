package me.toptas.rssreader

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import me.toptas.rssconverter.RssItem
import me.toptas.rssreader.features.rss.RssCache
import me.toptas.rssreader.features.rss.RssContract
import me.toptas.rssreader.features.rss.RssPresenter
import me.toptas.rssreader.mock.MockData
import me.toptas.rssreader.mock.MockRssRepository
import me.toptas.rssreader.model.Error
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RssPresenterTest {

    private lateinit var presenter: RssContract.Presenter
    private val cache = RssCache()
    private val view: RssContract.View = mock()
    private var repository = MockRssRepository(true)

    @Before
    fun setup() {
        presenter = RssPresenter(repository, cache)
        presenter.attach(view)
    }

    @Test
    fun testLoadRssItems() {
        presenter.loadRssItems(MockData.FEEDS[0], false)

        argumentCaptor<List<RssItem>>().apply {
            verify(view).showLoading()
            verify(view).onRssItemsLoaded(capture())
            Assert.assertTrue(firstValue == MockData.ITEMS)
            verify(view).hideLoading()

        }
    }

    @Test
    fun testLoadRssItemsFromCache() {
        cache.addContent(MockData.FEEDS[0].url, MockData.ITEMS)
        presenter = RssPresenter(repository, cache)
        presenter.attach(view)
        presenter.loadRssItems(MockData.FEEDS[0], true)

        argumentCaptor<List<RssItem>>().apply {
            verify(view).onRssItemsLoaded(capture())
            Assert.assertTrue(firstValue == MockData.ITEMS)
        }
    }

    @Test
    fun testFailRssItems() {
        presenter = RssPresenter(MockRssRepository(false), cache)
        presenter.attach(view)
        presenter.loadRssItems(MockData.FEEDS[0], true)

        argumentCaptor<Error>().apply {
            verify(view).onFail(capture())
            Assert.assertTrue(firstValue == Error.generic())
            verify(view).hideLoading()
        }
    }


}