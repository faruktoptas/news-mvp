package me.toptas.rssreader.features.rss

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class RssFragmentAdapter(fm: FragmentManager,
                         private val rssFragments: ArrayList<RssFragment>,
                         private val titles: ArrayList<String>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return rssFragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getCount(): Int {
        return rssFragments.size
    }
}