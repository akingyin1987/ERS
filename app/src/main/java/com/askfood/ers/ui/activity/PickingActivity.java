package com.askfood.ers.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.base.AppManager;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.PickingContract;
import com.askfood.ers.presenter.impl.PickingPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 拣货
 * Created by Administrator on 2017/10/15.
 */

public class PickingActivity extends AbsBaseActivity<PickingPresenterImpl> implements PickingContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_boxtotal)
    TextView tvBoxtotal;
    @BindView(R.id.tv_whole_thing)
    TextView tvWholeThing;
    @BindView(R.id.tv_zero_foods)
    TextView tvZeroFoods;
    @BindView(R.id.edit_total)
    EditText editTotal;
    @BindView(R.id.edit_deposit_area)
    EditText editDepositArea;
    @BindView(R.id.btn_config)
    Button btnConfig;

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_picking;
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
        setToolBar(toolbar,"拣货");
    }



    @OnClick(R.id.btn_config)
    public void onViewClicked() {
        AppManager.getAppManager().finishActivity(PrintCodeBoxActivity.class);
        AppManager.getAppManager().finishActivity(ScanCodeActivity.class);
        AppManager.getAppManager().finishActivity(WorkBoxCodeActivity.class);
        finish();

    }
}
