package me.toptas.rssreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import me.toptas.rssreader.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainContract.View, View.OnClickListener {


    private TextView mTextView;

    private MainPresenter mPresenter;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        mTextView = (TextView) findViewById(R.id.tvHello);
        mTextView.setOnClickListener(this);
        mPresenter = new MainPresenter();
        mPresenter.attach(this);
        mPresenter.loadHelloText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    @Override
    public void onTextLoaded(String text) {
        mTextView.setText(text);
    }


    @Override
    public void onClick(View view) {
        mPresenter.loadHelloText();
    }
}
