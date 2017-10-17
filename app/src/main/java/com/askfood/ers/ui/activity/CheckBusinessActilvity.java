package com.askfood.ers.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.base.dialog.DialogCallBack;
import com.askfood.ers.base.dialog.DialogUtil;
import com.askfood.ers.base.rx.RxPermissionsUtil;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.CheckBusinessContract;
import com.askfood.ers.presenter.impl.CheckBusinessPresenterImpl;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 复检业务
 * Created by Administrator on 2017/10/16.
 */

public class CheckBusinessActilvity extends AbsBaseActivity<CheckBusinessPresenterImpl> implements CheckBusinessContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_Shipper)
    TextView tvShipper;
    @BindView(R.id.tv_area_food)
    TextView tvAreaFood;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_whole_total)
    TextView tvWholeTotal;
    @BindView(R.id.tv_zero_total)
    TextView tvZeroTotal;
    @BindView(R.id.btn_print)
    Button btnPrint;
    @BindView(R.id.btn_bill_modify)
    Button btnBillModify;
    @BindView(R.id.btn_Spare_modify)
    Button btnSpareModify;
    @BindView(R.id.btn_start_check)
    Button btnStartCheck;

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_recheck_business;
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
       setToolBar(toolbar,"复检");
    }



    @OnClick(R.id.btn_print)
    public void onBtnPrintClicked() {

        RxPermissions   rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_ADMIN)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(aBoolean){

                            showSucces("开始连接蓝牙");
                        }else{
                            showWarning("请允许蓝牙使用");
                        }
                    }
                });
    }

    @OnClick(R.id.btn_bill_modify)
    public void onBtnBillModifyClicked() {
       //单据修改
        readyGo(BillModifyActivity.class);

    }

    @OnClick(R.id.btn_Spare_modify)
    public void onBtnSpareModifyClicked() {
         //零件修改
        DialogUtil.modifySpareDialog(this, new DialogCallBack<Integer>() {
            @Override
            public void call(Integer integer) {
                if(null != integer){
                    showSucces(String.valueOf(integer.intValue()));
                }
            }
        });
    }

    @OnClick(R.id.btn_start_check)
    public void onBtnStartCheckClicked() {

    }
}
