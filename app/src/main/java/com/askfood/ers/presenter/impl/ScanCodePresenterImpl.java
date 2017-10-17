package com.askfood.ers.presenter.impl;


import com.askfood.ers.presenter.ScanCodeContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/15.
 */

public class ScanCodePresenterImpl implements ScanCodeContract.Presenter {

    ScanCodeContract.View  mView;

    @Inject
    public ScanCodePresenterImpl() {
    }

    @Override
    public void attachView(ScanCodeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }
}
