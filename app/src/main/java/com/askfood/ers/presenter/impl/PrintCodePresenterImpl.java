package com.askfood.ers.presenter.impl;

import com.askfood.ers.presenter.PrintCodeBoxContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/15.
 */

public class PrintCodePresenterImpl implements PrintCodeBoxContract.Presenter {

    PrintCodeBoxContract.View   mView;

    @Inject
    public PrintCodePresenterImpl() {
    }

    @Override
    public void attachView(PrintCodeBoxContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }
}
