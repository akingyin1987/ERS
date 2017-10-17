package com.askfood.ers.ui.activity;


import android.Manifest;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.base.rx.RxPermissionsUtil;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.WorkBoxCodeContract;
import com.askfood.ers.presenter.impl.WorkBoxCodePresenterImpl;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/10/15.
 */

public class WorkBoxCodeActivity extends AbsBaseActivity<WorkBoxCodePresenterImpl> implements WorkBoxCodeContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_bill_number)
    TextView tvBillNumber;
    @BindView(R.id.tv_client_name)
    TextView tvClientName;
    @BindView(R.id.tv_row_num)
    TextView tvRowNum;
    @BindView(R.id.tv_whole_thing)
    TextView tvWholeThing;
    @BindView(R.id.tv_bit_package)
    TextView tvBitPackage;
    @BindView(R.id.tv_small_package)
    TextView tvSmallPackage;
    @BindView(R.id.tv_zone_food)
    TextView tvZoneFood;
    @BindView(R.id.btn_Print_code)
    Button btnPrintCode;
    @BindView(R.id.tv_boxcode)
    TextView tvBoxcode;
    @BindView(R.id.tv_commodity_name)
    TextView tvCommodityName;
    @BindView(R.id.tv_commodity_barcode)
    TextView tvCommodityBarcode;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_start_work;
    }

    @Override
    protected void injectDagger() {
        ButterKnife.bind(this);
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(ERSApp.get().getApplicationComponent()).build();
        activityComponent.inject(this);
    }

    @Override
    protected void initToolBar() {
        setToolBar(toolbar,"装箱作业");
    }



    @OnClick(R.id.btn_Print_code)
    public void onBtnPrintCodeClicked() {
        readyGo(PrintCodeBoxActivity.class);
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {

        RxPermissions  rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if(aBoolean){
                    readyGo(ScanCodeActivity.class);
                }else{
                    showMessage("请打开相机使用权限");
                }
            }
        });
    }
}
