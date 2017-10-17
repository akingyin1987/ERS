package com.askfood.ers.presenter.impl;

import com.askfood.ers.presenter.BillModifyContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/16.
 */

public class BillModifyPresenterImpl implements BillModifyContract.Presenter {

    @Inject
    public BillModifyPresenterImpl() {
    }

    BillModifyContract.View  mView;
    @Override
    public void attachView(BillModifyContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }
}
