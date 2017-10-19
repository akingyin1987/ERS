package com.askfood.ers.ui.activity;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.MainBusinessContract;
import com.askfood.ers.presenter.impl.MainBusinessPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 装箱业务
 * Created by Administrator on 2017/10/15.
 */

public class MainBusinessActivity extends AbsBaseActivity<MainBusinessPresenterImpl> implements MainBusinessContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_month_piece)
    TextView tvMonthPiece;
    @BindView(R.id.tv_month_row)
    TextView tvMonthRow;
    @BindView(R.id.tv_month_package)
    TextView tvMonthPackage;
    @BindView(R.id.tv_day_piece)
    TextView tvDayPiece;
    @BindView(R.id.tv_day_row)
    TextView tvDayRow;
    @BindView(R.id.tv_day_package)
    TextView tvDayPackage;
    @BindView(R.id.ll_bill_go)
    LinearLayout llBillGo;
    @BindView(R.id.ll_needdo_bill)
    LinearLayout llNeeddoBill;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_main_business;
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
        setToolBar(toolbar,"单据");
    }



    @OnClick(R.id.ll_bill_go)
    public void onLlBillGoClicked() {


    }

    @OnClick(R.id.ll_needdo_bill)
    public void onLlNeeddoBillClicked() {

        readyGo(GrabListActivity.class);
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {

        //抢单
        Bundle  bundle = new Bundle();
        bundle.putString("title","抢单-单据");
        readyGo(GrabBillActivity.class,bundle);

    }
}
