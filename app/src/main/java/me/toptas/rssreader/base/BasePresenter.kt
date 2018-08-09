package me.toptas.rssreader.base

open class BasePresenter<V : BaseView> : BaseMvpPresenter<V> {

    /**
     * Attached view
     */
    var view: V? = null


    override val isAttached = view != null


    override fun attach(view: V) {
        this.view = view
    }

    override fun detach() {
        view = null
    }
}