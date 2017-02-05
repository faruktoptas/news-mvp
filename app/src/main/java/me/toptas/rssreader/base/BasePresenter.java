package me.toptas.rssreader.base;

public class BasePresenter<V extends BaseView> implements BaseMvpPresenter<V> {

    /**
     * Attached view
     */
    private V mView;


    @Override
    public void attach(V view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public boolean isAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }
}