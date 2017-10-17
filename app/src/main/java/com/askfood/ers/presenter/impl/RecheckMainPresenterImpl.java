package com.askfood.ers.presenter.impl;

import com.askfood.ers.presenter.RecheckMainContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/16.
 */

public class RecheckMainPresenterImpl implements RecheckMainContract.Presenter {

    RecheckMainContract.View  mView;

    @Inject
    public RecheckMainPresenterImpl() {
    }

    @Override
    public void attachView(RecheckMainContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }
}
