package com.askfood.ers.presenter.impl;

import com.askfood.ers.presenter.PickingContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/15.
 */

public class PickingPresenterImpl implements PickingContract.Presenter {

    PickingContract.View  mView;

    @Inject
    public PickingPresenterImpl() {
    }

    @Override
    public void attachView(PickingContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }
}
