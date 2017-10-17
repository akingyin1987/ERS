package com.askfood.ers.presenter.impl;

import com.askfood.ers.presenter.MainBusinessContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/15.
 */

public class MainBusinessPresenterImpl implements MainBusinessContract.Presenter {

    MainBusinessContract.View  mView;

    @Inject
    public MainBusinessPresenterImpl() {
    }


    @Override
    public void attachView(MainBusinessContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }
}
