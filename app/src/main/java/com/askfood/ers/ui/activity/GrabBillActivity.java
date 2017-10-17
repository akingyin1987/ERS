package com.askfood.ers.ui.activity;


import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.base.AppManager;
import com.askfood.ers.base.dialog.DialogCallBack;
import com.askfood.ers.base.dialog.DialogUtil;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.GrabBillContract;
import com.askfood.ers.presenter.impl.GrabBillPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 单据
 * Created by Administrator on 2017/10/15.
 */

public class GrabBillActivity extends AbsBaseActivity<GrabBillPresenterImpl> implements GrabBillContract.View {

    public String title;
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
    @BindView(R.id.tv_zero_foods)
    TextView tvZeroFoods;
    @BindView(R.id.tv_bit_package)
    TextView tvBitPackage;
    @BindView(R.id.tv_small_package)
    TextView tvSmallPackage;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_grab_bill;
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
        title = getIntent().getStringExtra("title");
        setToolBar(toolbar,title);
    }


    public    int   error;

    @Override
    public void onBackPressed() {
        DialogUtil.configPasswork(this, new DialogCallBack<String>() {
            @Override
            public void call(String s) {
                showTips("密码："+s);
                if(TextUtils.equals("123",s)){
                    finish();
                }else{
                    if(error >=3){
                        AppManager.getAppManager().finishActivity(LoginActivity.class);
                        finish();
                        return;
                    }
                    error++;
                    showError("密码错误，连接三次错误将会出到系统选择！");
                }

            }
        });
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        readyGo(WorkBoxCodeActivity.class);
    }
}
