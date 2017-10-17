package com.askfood.ers.presenter.impl;

import com.askfood.ers.presenter.HomeContract;

import javax.inject.Inject;


/**
 * Created by Administrator on 2017/10/15.
 */

public class HomePresenterImpl  implements HomeContract.Presenter {

    HomeContract.View  view;

    @Inject
    public HomePresenterImpl() {
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }
}
