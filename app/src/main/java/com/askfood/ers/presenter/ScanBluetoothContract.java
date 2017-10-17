package com.askfood.ers.presenter;

import com.askfood.ers.base.BasePresenter;
import com.askfood.ers.base.BaseView;


/**
 * Created by Administrator on 2017/10/15.
 */

public interface ScanBluetoothContract {

    interface View extends BaseView {

       void    stopScan();





    }

    interface Presenter extends BasePresenter<ScanBluetoothContract.View> {

        void    startScanBluetooth();

        void    stopScanBluetooth();

    }


}
