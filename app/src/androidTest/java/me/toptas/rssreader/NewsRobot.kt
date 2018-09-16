package me.toptas.rssreader

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import junit.framework.Assert
import me.toptas.rssreader.features.main.MainActivity
import me.toptas.rssreader.mocks.MockMainRepository
import me.toptas.rssreader.mocks.MockRssRepository
import me.toptas.rssreader.util.RecyclerViewMatcher
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun news(func: NewsRobot.() -> Unit) = NewsRobot().apply { func() }

class NewsRobot {

    fun swipeLeft() {
        val viewPager = getViewPagerInteraction()
        viewPager.perform(ViewActions.swipeLeft())
    }

    fun swipeRight() {
        val viewPager = getViewPagerInteraction()
        viewPager.perform(ViewActions.swipeRight())
    }

    fun swipeDown() {
        val viewPager = getViewPagerInteraction()
        viewPager.perform(ViewActions.swipeDown())
    }


fun checkViewPagerItems(rule: ActivityTestRule<MainActivity>) {
    val viewPager = rule.activity.findViewById(R.id.viewPager) as ViewPager
    Assert.assertNotNull(viewPager)
    Assert.assertNotNull(viewPager.adapter)
    Assert.assertTrue(MockMainRepository.FEEDS.size == viewPager.adapter?.count)
}

    fun checkRecyclerViewItemContent() {
        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.tvTitle))
                .check(matches(withText(MockRssRepository.ITEMS[0].title)))
    }


    fun checkRecyclerViewItemCount(rule: ActivityTestRule<MainActivity>, count: Int) {
        val recyclerView = rule.activity.findViewById(R.id.recyclerView) as RecyclerView
        Assert.assertNotNull(recyclerView)
        Assert.assertNotNull(recyclerView.adapter)
        Assert.assertTrue(count == recyclerView.adapter.itemCount)
    }

    fun checkSwipeRefresh(rule: ActivityTestRule<MainActivity>) {
        val swRefresh = rule.activity.findViewById(R.id.swRefresh) as SwipeRefreshLayout
        Assert.assertNotNull(swRefresh)
        Assert.assertTrue(!swRefresh.isRefreshing)
    }


    fun checkErrorTextView() {
        val textView = onView(
                allOf(withId(R.id.tvNoItems),
                        childAtPosition(
                                withParent(withId(R.id.viewPager)),
                                1),
                        isDisplayed()))
        textView.check(matches(isDisplayed()))
    }

    private fun getViewPagerInteraction() = onView(
            allOf<View>(withId(R.id.viewPager), isDisplayed()))

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }

    private fun withRecyclerView(recyclerViewId: Int) = RecyclerViewMatcher(recyclerViewId)
}