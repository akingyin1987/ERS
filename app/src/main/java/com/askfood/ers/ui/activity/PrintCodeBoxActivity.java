package com.askfood.ers.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.PrintCodeBoxContract;
import com.askfood.ers.presenter.impl.PrintCodePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/15.
 */

public class PrintCodeBoxActivity extends AbsBaseActivity<PrintCodePresenterImpl> implements PrintCodeBoxContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_bill_number)
    TextView tvBillNumber;
    @BindView(R.id.tv_client_name)
    TextView tvClientName;
    @BindView(R.id.tv_zone_food)
    TextView tvZoneFood;
    @BindView(R.id.tv_last_good)
    TextView tvLastGood;
    @BindView(R.id.cb_view_lastlast)
    RadioButton cbViewLastlast;
    @BindView(R.id.cb_view_last)
    RadioButton cbViewLast;
    @BindView(R.id.btn_config)
    Button btnConfig;

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_print_codebox;
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
          setToolBar(toolbar,"打印箱码");
    }



    @OnClick(R.id.btn_config)
    public void onViewClicked() {
       readyGo(PickingActivity.class);

    }
}
