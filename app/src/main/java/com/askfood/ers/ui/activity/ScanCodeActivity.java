package com.askfood.ers.ui.activity;


import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.base.dialog.DialogCallBack;
import com.askfood.ers.base.dialog.DialogUtil;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.ScanCodeContract;
import com.askfood.ers.presenter.impl.ScanCodePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/15.
 */

public class ScanCodeActivity extends AbsBaseActivity<ScanCodePresenterImpl> implements ScanCodeContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_zone_food)
    TextView tvZoneFood;
    @BindView(R.id.tv_commodity_name)
    TextView tvCommodityName;
    @BindView(R.id.tv_boxcode)
    TextView tvBoxcode;
    @BindView(R.id.ed_food_number)
    EditText edFoodNumber;
    @BindView(R.id.btn_config)
    Button btnConfig;

    @Override
    protected void initEventAndData() {
         readyGoForResult(ZxingScanActivity.class,1);
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_scan_code;
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
      setToolBar(toolbar,"扫码");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String  result = data.getStringExtra("data");
                showSucces(result);
            }
        }
    }

    @OnClick(R.id.btn_config)
    public void onViewClicked() {
        finish();
    }


    @OnClick(R.id.btn_scan)
    public  void  btn_scan(){
        DialogUtil.showConfigDialog(this, "当前数据未提交将会被自动认为放弃", new DialogCallBack<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if(aBoolean){
                    readyGoForResult(ZxingScanActivity.class,1);
                }
            }
        });

    }
}
