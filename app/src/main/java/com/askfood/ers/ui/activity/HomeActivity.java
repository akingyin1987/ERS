package com.askfood.ers.ui.activity;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.base.AppManager;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.HomeContract;
import com.askfood.ers.presenter.impl.HomePresenterImpl;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 系统选择
 * Created by Administrator on 2017/10/15.
 */

public class HomeActivity extends AbsBaseActivity<HomePresenterImpl> implements HomeContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.ll_encasement)
    LinearLayout llEncasement;
    @BindView(R.id.ll_review)
    LinearLayout llReview;

    @Override
    public void goToEncasementActivity() {

    }

    @Override
    public void goToReviewActivity() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_index;
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
       setToolBar(toolbar,"选择系统");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting_blue) {
             readyGo(ScanBluetootActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }



    @OnClick({R.id.ll_encasement, R.id.ll_review})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_encasement:
                //装箱登陆
                Bundle   bundle = new Bundle();
                bundle.putInt("type",0);
                readyGo(LoginActivity.class,bundle);
                break;
            case R.id.ll_review:
                //复检登录
                Bundle   bundle1 = new Bundle();
                bundle1.putInt("type",1);
                readyGo(LoginActivity.class,bundle1);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        doExitApp();
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {

            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getAppManager().AppExit(this);
            finish();
        }
    }
}
