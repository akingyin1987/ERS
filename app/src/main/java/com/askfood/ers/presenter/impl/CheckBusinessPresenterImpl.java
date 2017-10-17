package com.askfood.ers.presenter.impl;

import com.askfood.ers.presenter.CheckBusinessContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/16.
 */

public class CheckBusinessPresenterImpl implements CheckBusinessContract.Presenter {

    CheckBusinessContract.View  mView;

    @Inject
    public CheckBusinessPresenterImpl() {
    }

    @Override
    public void attachView(CheckBusinessContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }
}
