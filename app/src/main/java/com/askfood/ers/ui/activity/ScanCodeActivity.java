package com.askfood.ers.ui.activity;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.jb.Preference;
import android.jb.barcode.BarcodeManager;
import android.jb.utils.WakeLockUtil;
import android.os.IBinder;
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
import com.askfood.ers.base.rx.RxUtil;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.ScanCodeContract;
import com.askfood.ers.presenter.impl.ScanCodePresenterImpl;
import com.barcode.ScanDeviceType;
import com.barcode.ScanListener;
import com.barcode.ScanService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/10/15.
 */

public class ScanCodeActivity extends AbsBaseActivity<ScanCodePresenterImpl> implements ScanCodeContract.View ,ScanListener {


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
    @BindView(R.id.btn_scan)
    Button btn_scan;
    private Intent service;
    private ScanService scanService;
    private BarcodeManager scanManager;
    private int scan_time_limit = 300;
    WakeLockUtil mWakeLockUtil = null;
    @Override
    protected void initEventAndData() {
        File  file = new File("/dev/moto_sdl");
        System.out.println(file.exists());
        Preference.setScanDeviceType(this, ScanDeviceType.OneD);
        service = new Intent(this, ScanService.class);
        startService(service);
        bindService(service, serviceConnection, BIND_AUTO_CREATE);
        mWakeLockUtil = new WakeLockUtil(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jb.action.F4key");
        registerReceiver(f4Receiver, intentFilter);
    }
    private BroadcastReceiver f4Receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // Bundle bundle = intent.getExtras();
            if (intent.hasExtra("F4key")) {
                if (intent.getStringExtra("F4key").equals("down")) {


                } else if (intent.getStringExtra("F4key").equals("up")) {

                }
            }
        }
    };
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
      try {
          if(edFoodNumber.getText().toString().trim().isEmpty()){
              showError("请输入件数");
              return;
          }
          int   current = Integer.parseInt(tvBoxcode.getText().toString().trim());
          int   inputNum = Integer.parseInt(edFoodNumber.getText().toString().trim());
          if(current != inputNum){
              DialogUtil.showConfigDialog(this, "当前输入数量与录入数不一致，确认当前输入?", new DialogCallBack<Boolean>() {
                  @Override
                  public void call(Boolean aBoolean) {
                      if(aBoolean){
                          finish();
                      }
                  }
              });
          }else{
              finish();
          }
      }catch (Exception e){
          e.printStackTrace();
      }
    }


    long  nowTime=0L,lastTime=0L;
    @OnClick(R.id.btn_scan)
    public  void  btn_scan(){

        if (scanService != null) {

            btn_scan.setEnabled(false);
            nowTime = System.currentTimeMillis();

            if (nowTime - lastTime > 200) {

                scanManager.Barcode_Stop();
                scanManager.Barcode_Start();
                mWakeLockUtil.lock();// 保持屏幕唤醒
                lastTime = nowTime;

            }
            Observable.timer(3, TimeUnit.SECONDS).compose(RxUtil.<Long>IO_Main())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                           btn_scan.setEnabled(true);
                        }
                    });

        } else {

           showError("扫描器出错");

        }


    }
    boolean bind = false;
    ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bind = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bind = true;
            ScanService.MyBinder myBinder = (ScanService.MyBinder) service;
            scanService = myBinder.getService();
            //
            scanManager = scanService.getScanManager();

            scanService.setOnScanListener(ScanCodeActivity.this);
            scanService.setActivityUp(true);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if (scanService != null){
            scanService.setActivityUp(false);
        }

        ScanService.isScanActivityUp = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (scanService != null)
            scanService.setActivityUp(true);
        ScanService.isScanActivityUp = false;

    }

    @Override
    public void result(String content) {
        System.out.println("contnet="+content);
    }

    @Override
    public void henResult(String codeType, String context) {
        System.out.println("content="+codeType+":"+codeType);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (scanService != null) {
            if (null != scanManager) {
                System.out.println("ScanActivity Barcode_Stop5");
                scanManager.Barcode_Stop();
            }
        }

        if (bind) {
            unbindService(serviceConnection);
            if (!Preference.getScanSelfopenSupport(this, true)) {
                this.stopService(service);
            }
        }

        mWakeLockUtil.unLock();//

        if(null != f4Receiver){
            this.unregisterReceiver(f4Receiver);
        }

    }
}
