package com.askfood.ers.presenter.impl;

import com.askfood.ers.presenter.GrabBillContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/15.
 */

public class GrabBillPresenterImpl implements GrabBillContract.Presenter {

    GrabBillContract.View  mView;
    @Inject
    public GrabBillPresenterImpl() {
    }

    @Override
    public void attachView(GrabBillContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }
}
