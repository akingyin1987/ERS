package com.askfood.ers;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.askfood.ers.base.call.AppCallBack;
import com.askfood.ers.daocore.db.help.DbCore;
import com.askfood.ers.injection.component.ApplicationComponent;
import com.askfood.ers.injection.component.DaggerApplicationComponent;
import com.askfood.ers.injection.module.ApplicationModule;
import com.askfood.ers.injection.module.GreenDaoModule;
import com.askfood.ers.net.config.CommonConstants;
import com.askfood.ers.utils.KissTools;
import com.askfood.ers.utils.PreferencesUtil;
import com.askfood.ers.utils.Utils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/10/15.
 */

public class ERSApp extends Application {
    private   static ERSApp cassApp;

    private ApplicationComponent applicationComponent;

    public   static ERSApp get(){

        return  cassApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        cassApp = this;
        Utils.init(this);
        KissTools.setContext(getApplicationContext());

        DbCore.enableQueryBuilderLog();
        DbCore.init(getApplicationContext());
        CommonConstants.API_HOST="";

       // CrashReport.initCrashReport(getApplicationContext());
        Bugly.init(getApplicationContext(), "cfa43dc3ae", false);
        getApplicationComponent().inject(this);
        PreferencesUtil.setDefaultName("ers_setting_pre");
    }


    public    ApplicationComponent  getApplicationComponent(){
        if(null == applicationComponent){
            applicationComponent = DaggerApplicationComponent.builder().
                    applicationModule(new ApplicationModule(this))
                    .greenDaoModule(new GreenDaoModule(DbCore.getDaoSession(),getApplicationContext()))
                    .build();
        }
        return  applicationComponent;
    }

    //申请手机必要的隐私权限
    public   void   applyPrivacyAuth(@NonNull Activity activity, @Nullable
    final AppCallBack<Boolean> callBack){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override public void accept(Boolean aBoolean) throws Exception {
                        if(null != callBack){
                            callBack.call(aBoolean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        Timber.d(throwable);
                        if(null != callBack){
                            callBack.call(false);
                        }
                    }
                });
    }
}
