package me.toptas.rssreader

import android.content.Intent
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.toptas.rssreader.di.AppModule
import me.toptas.rssreader.di.DaggerAppComponent
import me.toptas.rssreader.features.main.MainActivity
import me.toptas.rssreader.mocks.MockNetworkModule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class AllTests {

    @get:Rule
    var rule = ActivityTestRule(MainActivity::class.java,
            false,
            false)


    @Before
    fun setup() {
        // inject mock repositories then start activity
        NewsApp.setComponent(DaggerAppComponent.builder()
                .appModule(AppModule(NewsApp.instance))
                .networkModule(MockNetworkModule())
                .build())

        val intent = Intent()
        rule.launchActivity(intent)
    }


    @Test
    fun loadViewPagerTest() {
        news {
            checkViewPagerItems(rule)
            swipeLeft()
            swipeRight()
        }
    }

    @Test
    fun recyclerViewItemsTest() {
        news {
            checkRecyclerViewItemCount(rule, 5)
            checkRecyclerViewItemContent()
        }
    }

    @Test
    fun swipeRefreshTest() {
        news {
            checkRecyclerViewItemCount(rule, 5)
            swipeDown()
            checkSwipeRefresh(rule)
            checkRecyclerViewItemCount(rule, 10)
        }
    }

    @Test
    fun loadFailed() {
        news {
            swipeLeft()
            checkErrorTextView()
        }
    }
}
