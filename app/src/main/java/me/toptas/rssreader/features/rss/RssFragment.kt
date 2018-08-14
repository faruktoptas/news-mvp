package me.toptas.rssreader.features.rss

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_rss.*
import me.toptas.rssconverter.RssItem
import me.toptas.rssreader.R
import me.toptas.rssreader.base.BaseFragment
import me.toptas.rssreader.di.ActivityComponent
import me.toptas.rssreader.model.Error
import me.toptas.rssreader.model.Feed
import javax.inject.Inject


class RssFragment : BaseFragment(), RssContract.View, SwipeRefreshLayout.OnRefreshListener, RssItemsAdapter.OnItemClickListener {

    @Inject
    lateinit var presenter: RssContract.Presenter

    private lateinit var feed: Feed
    private lateinit var adapter: RssItemsAdapter
    private var listener: OnItemSelectListener? = null


    override val layoutResource = R.layout.fragment_rss

    override fun inject(component: ActivityComponent) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feed = arguments!!.getSerializable(KEY_FEED) as Feed
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        adapter = RssItemsAdapter(activity!!, this)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        swRefresh.setOnRefreshListener(this)
        presenter.loadRssItems(feed, true)
    }

    override fun onRssItemsLoaded(rssItems: List<RssItem>) {
        adapter.setItems(rssItems)
        tvNoItems.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun onBrowse(rssItem: RssItem) {
        listener?.onItemSelected(rssItem)
    }

    override fun onItemSelected(rssItem: RssItem) {
        presenter.browseRssUrl(rssItem)
    }

    override fun onRefresh() {
        presenter.loadRssItems(feed, false)
    }

    override fun showLoading() {
        if (isAdded) swRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        if (isAdded) swRefresh.isRefreshing = false
    }

    override fun onFail(error: Error) {
        tvNoItems.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnItemSelectListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        private const val KEY_FEED = "key_feed"

        fun newInstance(feed: Feed): RssFragment {
            val rssFragment = RssFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_FEED, feed)
            rssFragment.arguments = bundle
            return rssFragment
        }
    }

    interface OnItemSelectListener {
        fun onItemSelected(rssItem: RssItem)
    }
}