/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.base.rx;

import android.Manifest;
import android.app.Activity;
import android.support.annotation.NonNull;

import com.askfood.ers.base.call.AppCallBack;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * @ Description:
 * @ Author king
 * @ Date 2017/9/18 10:35
 * @ Version V1.0
 */

public class RxPermissionsUtil {

      //申请拍照权限
      // 写文件 调用camera
      public   static   void    applyPhotographAuth(@NonNull final Activity activity, final AppCallBack<Boolean> appCallBack){
            RxPermissions  rxPermissions = new RxPermissions(activity);
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                 Manifest.permission.CAMERA)
                .subscribe(new ApplySucAuth(appCallBack), new ApplyErrorAuth(appCallBack));
      }

      //申请百度定位(手机状态，获取位置，读写卡)
      public   static   void    applyBaiduLocAuth(@NonNull Activity  activity,AppCallBack<Boolean> appCallBack){
            RxPermissions   rxPermissions = new RxPermissions(activity);
            rxPermissions.request(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new ApplySucAuth(appCallBack), new ApplyErrorAuth(appCallBack));
      }

      //拍视频 权限
      public  static   void   applyTackVideoAuth(@NonNull Activity  activity,AppCallBack<Boolean>  appCallBack){
            RxPermissions   rxPermissions = new RxPermissions(activity);
            rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
               )
                .subscribe(new ApplySucAuth(appCallBack), new ApplyErrorAuth(appCallBack));
      }



   static   class   ApplySucAuth implements  Consumer<Boolean>{
            private    AppCallBack<Boolean>   mAppCallBack;

            public ApplySucAuth(AppCallBack<Boolean> appCallBack) {
                  mAppCallBack = appCallBack;
            }

            @Override public void accept(Boolean aBoolean) throws Exception {
                  if(null != mAppCallBack){
                        mAppCallBack.call(aBoolean);
                  }
            }
      }

    static   class   ApplyErrorAuth  implements Consumer<Throwable>{
            private    AppCallBack<Boolean>   mAppCallBack;

            public ApplyErrorAuth(AppCallBack<Boolean> appCallBack) {
                  mAppCallBack = appCallBack;
            }

            @Override public void accept(Throwable throwable) throws Exception {
                  if(null != mAppCallBack){
                        mAppCallBack.call(false);
                  }
            }
      }
}
