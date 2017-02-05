package me.toptas.rssreader;

import me.toptas.rssreader.base.BaseMvpPresenter;
import me.toptas.rssreader.base.BaseView;

/**
 * Created by ftoptas on 28/01/17.
 */

public interface MainContract {

    // User actions. Presenter will implement
    interface Presenter extends BaseMvpPresenter<MainContract.View>{
        void loadHelloText();
    }

    // Action callbacks. Activity/Fragment will implement
    interface View extends BaseView {
        void onTextLoaded(String text);
    }

}
