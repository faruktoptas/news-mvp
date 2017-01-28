package me.topas.rssreader;

import java.util.Random;

import me.topas.rssreader.base.BasePresenter;

/**
 * Created by ftoptas on 28/01/17.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private String[] helloTexts = {"BONJOUR", "HOLA", "HALLO", "MERHABA", "HELLO", "CIAO", "KONNICHIWA"};

    @Override
    public void loadHelloText() {
        Random random = new Random();
        mView.onTextLoaded(helloTexts[random.nextInt(helloTexts.length)]);

    }
}
