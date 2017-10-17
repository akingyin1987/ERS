package com.askfood.ers.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.base.call.AppCallBack;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.LoginContract;
import com.askfood.ers.presenter.impl.LoginPresenterImpl;
import com.askfood.ers.utils.AppUtils;
import com.askfood.ers.utils.PreferencesUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/15.
 */

public class LoginActivity extends AbsBaseActivity<LoginPresenterImpl> implements LoginContract.View {


    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.iv_clean_phone)
    ImageView ivCleanPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.clean_password)
    ImageView cleanPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView ivShowPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.regist)
    TextView regist;
    @BindView(R.id.forget_password)
    TextView forgetPassword;
    @BindView(R.id.body)
    LinearLayout body;
    @BindView(R.id.tv_imei)
    TextView tvImei;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.service)
    LinearLayout service;
    @BindView(R.id.root)
    LinearLayout root;

    @Inject
    LoginPresenterImpl   loginPresenter;




    @Override
    public void goToHomeActivity() {

    }

    @Override
    public void cleanUserName() {
       etMobile.setText("");
    }

    @Override
    public void cleanPass() {
      etPassword.setText("");
    }

    boolean  flag = false;
    @Override
    public void showPass() {
        if(flag ){
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivShowPwd.setImageResource(R.drawable.pass_gone);
            flag = false;
        }else{
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ivShowPwd.setImageResource(R.drawable.pass_visuable);
            flag = true;
        }
        String pwd = etPassword.getText().toString();
        if (!TextUtils.isEmpty(pwd))
            etPassword.setSelection(pwd.length());
    }

    @Override
    public void login() {

    }


    int    type;

    @Override
    protected void initEventAndData() {
        tvVersion.setText("V"+ AppUtils.getAppVersionName());
        type = getIntent().getIntExtra("type",0);//0=装箱 1=复检
        etMobile.setText(PreferencesUtil.get("userAcount",""));

        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0 || etPassword.getText().length() == 0){
                    btnLogin.setEnabled(false);
                }else{
                    btnLogin.setEnabled(true);
                }
                if (!TextUtils.isEmpty(s) && ivCleanPhone.getVisibility() == View.GONE) {
                    ivCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    ivCleanPhone.setVisibility(View.GONE);
                }
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0 || etMobile.getText().length() == 0){
                    btnLogin.setEnabled(false);
                }else{
                    btnLogin.setEnabled(true);
                }
                if (!TextUtils.isEmpty(s) && ivShowPwd.getVisibility() == View.GONE) {
                    ivShowPwd.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    ivShowPwd.setVisibility(View.GONE);
                }

            }
        });

        ERSApp.get().applyPrivacyAuth(this, new AppCallBack<Boolean>() {
            @Override public void call(Boolean aBoolean) {
                if(aBoolean){

                }else{
                    showWarning("请允许应用必要的权限");
                }
            }
        });
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_login;
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

    }



    @OnClick(R.id.close)
    public void onCloseClicked() {
        finish();
    }

    @OnClick(R.id.iv_clean_phone)
    public void onIvCleanPhoneClicked() {
        etMobile.setText("");
    }

    @OnClick(R.id.clean_password)
    public void onCleanPasswordClicked() {
        etPassword.setText("");
    }

    @OnClick(R.id.iv_show_pwd)
    public void onIvShowPwdClicked() {
       showPass();
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
        PreferencesUtil.put("userAcount",etMobile.getText().toString().trim());
        if(type == 0){
            //进入装箱
            readyGo(MainBusinessActivity.class);
        }else if(type == 1){
            //进入复检
            readyGo(RecheckMainActivity.class);
        }
        finish();
    }


}
