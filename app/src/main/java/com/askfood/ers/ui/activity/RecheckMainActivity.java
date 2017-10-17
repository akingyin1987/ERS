package com.askfood.ers.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.base.dialog.DialogCallBack;
import com.askfood.ers.base.dialog.DialogUtil;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.RecheckMainContract;
import com.askfood.ers.presenter.impl.RecheckMainPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 复检主界面
 * Created by Administrator on 2017/10/16.
 */

public class RecheckMainActivity extends AbsBaseActivity<RecheckMainPresenterImpl> implements RecheckMainContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_client_name)
    TextView  tv_client_name;
    @BindView(R.id.tv_type)
    TextView  tv_type;
    @BindView(R.id.tv_recheck_cout)
    TextView   tv_recheck_cout;
    @Override
    protected void initEventAndData() {

    }



    @Override
    protected int getContentViewID() {
        return R.layout.activity_recheck;
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

    @OnClick(R.id.btn_modify_arae)
    public   void  btn_modify_arae(){

        DialogUtil.modifyAraeFoodDialog(this, new DialogCallBack<String>() {
            @Override
            public void call(String s) {
              if(!TextUtils.isEmpty(s)){
                  showSucces(s);
                  configModifyAraeFood();
              }
            }
        });

    }


    public   void   configModifyAraeFood(){
        DialogUtil.modifyAraeFoodConfigDialog(this, "abcd", "重庆家乐福", "123", new DialogCallBack<String>() {
            @Override
            public void call(String s) {
                showMessage(s);
            }
        });
    }

    @OnClick(R.id.btn_start_check)
    public   void  btn_start_check(){
          readyGo(CheckBusinessActilvity.class);
    }
}
