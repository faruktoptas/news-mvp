package me.toptas.rssreader.base;

public  class BasePresenter<V extends BaseView> {

    protected V mView;


    public void attach(V view) {
        mView = view;
    }

    public void detach() {
        mView = null;
    }

    public boolean isAttached() {
        return mView != null;
    }


}