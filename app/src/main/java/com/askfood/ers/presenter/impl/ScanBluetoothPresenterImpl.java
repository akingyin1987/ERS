package com.askfood.ers.presenter.impl;


import com.askfood.ers.presenter.ScanBluetoothContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/15.
 */

public class ScanBluetoothPresenterImpl implements ScanBluetoothContract.Presenter {


    ScanBluetoothContract.View  mView;


    @Inject
    public ScanBluetoothPresenterImpl() {
    }

    @Override
    public void attachView(ScanBluetoothContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void startScanBluetooth() {

    }

    @Override
    public void initialization() {

    }

    @Override
    public void stopScanBluetooth() {

    }
}
