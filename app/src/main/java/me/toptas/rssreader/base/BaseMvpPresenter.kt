package me.toptas.rssreader.base

/**
 * Each presenter must implement this interface
 *
 * @param <V> View for the presenter
</V> */
interface BaseMvpPresenter<V : BaseView> {

    /**
     * @return true if view is attached to presenter
     */
    val isAttached: Boolean

    /**
     * Called when view attached to presenter
     *
     * @param view
     */
    fun attach(view: V)

    /**
     * Called when view is detached from presenter
     */
    fun detach()
}