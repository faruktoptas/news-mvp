package me.toptas.rssreader.features.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import me.toptas.rssconverter.RssItem
import me.toptas.rssreader.R
import me.toptas.rssreader.base.BaseActivity
import me.toptas.rssreader.di.ActivityComponent
import me.toptas.rssreader.features.chrome.ChromeTabsWrapper
import me.toptas.rssreader.features.rss.RssFragment
import me.toptas.rssreader.features.rss.RssFragmentAdapter
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, RssFragment.OnItemSelectListener {

    @Inject
    lateinit var presenter: MainContract.Presenter
    private lateinit var wrapper: ChromeTabsWrapper


    override val layoutResource = R.layout.activity_main

    override fun inject(component: ActivityComponent) {
        component.inject(this)
    }

    override fun init(state: Bundle?) {
        setSupportActionBar(toolbar)
        tablayout.setupWithViewPager(viewPager)
        tablayout.tabMode = TabLayout.MODE_SCROLLABLE

        presenter.attach(this)
        presenter.loadRssFragments()
        wrapper = ChromeTabsWrapper(this)
    }

    override fun onLoadRssFragments() {
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val fragmentList = ArrayList<RssFragment>()
        val titles = ArrayList<String>()
        for (feed in FeedParser().parseFeeds(this)) {
            fragmentList.add(RssFragment.newInstance(feed))
            titles.add(feed.title)
        }

        val adapter = RssFragmentAdapter(supportFragmentManager, fragmentList, titles)
        viewPager.adapter = adapter
    }

    override fun onItemSelected(rssItem: RssItem) {
        wrapper.openCustomtab(rssItem.link)
    }

    override fun onStart() {
        super.onStart()
        wrapper.bindCustomTabsService()
    }

    override fun onStop() {
        super.onStop()
        wrapper.unbindCustomTabsService()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
