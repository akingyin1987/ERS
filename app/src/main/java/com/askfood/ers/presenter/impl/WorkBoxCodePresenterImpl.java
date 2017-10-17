package com.askfood.ers.presenter.impl;

import com.askfood.ers.presenter.WorkBoxCodeContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/15.
 */

public class WorkBoxCodePresenterImpl implements WorkBoxCodeContract.Presenter {

    WorkBoxCodeContract.View  mView;
    @Inject
    public WorkBoxCodePresenterImpl() {
    }

    @Override
    public void attachView(WorkBoxCodeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }
}
