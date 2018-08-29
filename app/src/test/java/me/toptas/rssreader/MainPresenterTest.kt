package me.toptas.rssreader

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import me.toptas.rssreader.features.main.MainContract
import me.toptas.rssreader.features.main.MainPresenter
import me.toptas.rssreader.mock.MockData
import me.toptas.rssreader.mock.MockMainRepository
import me.toptas.rssreader.model.Feed
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MainPresenterTest {


    private lateinit var mainPresenter: MainContract.Presenter
    private val view: MainContract.View = mock()

    @Before
    fun setup() {

        val repository = MockMainRepository()
        mainPresenter = MainPresenter(repository)
        mainPresenter.attach(view)

    }

    @Test
    fun testLoadItems() {
        mainPresenter.loadRssFragments()

        argumentCaptor<List<Feed>>().apply {
            verify(view).onLoadRssFragments(capture())

            Assert.assertEquals(firstValue, MockData.FEEDS)
        }

    }
}