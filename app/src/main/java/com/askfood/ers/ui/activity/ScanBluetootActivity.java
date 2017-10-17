package com.askfood.ers.ui.activity;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;

import com.askfood.ers.base.rx.RxUtil;
import com.askfood.ers.bluetooth.BluetoothHelp;
import com.askfood.ers.bluetooth.PrintUtils;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.ScanBluetoothContract;
import com.askfood.ers.presenter.impl.ScanBluetoothPresenterImpl;
import com.askfood.ers.ui.adapter.BluetoothSeachAdapter;
import com.askfood.ers.utils.PreferencesUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.SafeObserver;
import top.wuhaojie.bthelper.BtHelperClient;
import top.wuhaojie.bthelper.MessageItem;
import top.wuhaojie.bthelper.OnSearchDeviceListener;
import top.wuhaojie.bthelper.OnSendMessageListener;

/**
 * 蓝牙搜索
 * Created by Administrator on 2017/10/15.
 */

public class ScanBluetootActivity extends AbsBaseActivity<ScanBluetoothPresenterImpl> implements ScanBluetoothContract.View,OnSendMessageListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_cureent_bluetooth)
    TextView tvCureentBluetooth;
    @BindView(R.id.re_bluetooth)
    RecyclerView reBluetooth;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    @Inject
    public BluetoothSeachAdapter mResultAdapter;

    public BtHelperClient   btHelperClient;
    public BluetoothHelp    bluetoothHelp;
    @Override
    protected void initEventAndData() {


        tvCureentBluetooth.setText("当前蓝牙："+ PreferencesUtil.get("bluetooth_mac","未选择"));
        reBluetooth.setLayoutManager(new LinearLayoutManager(this));
        reBluetooth.setItemAnimator(new DefaultItemAnimator());
        reBluetooth.setAdapter(mResultAdapter);
        btHelperClient = BtHelperClient.from(this);
        mResultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String  address = mResultAdapter.getItem(position).getAddress();
                PreferencesUtil.put("bluetooth_mac",address);
                tvCureentBluetooth.setText("当前蓝牙："+ address);
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshBluebooth();
            }
        });
        bluetoothHelp = new BluetoothHelp(this);
    }


    public   void   refreshBluebooth(){
        swipe.setRefreshing(true);
        if(bluetoothHelp.CheckBluetooth()){
            bluetoothHelp.requestOpenBluetoothDevice();
        }
        btHelperClient.searchDevices(new OnSearchDeviceListener() {
            @Override
            public void onStartDiscovery() {
                showTips("开始扫描");
            }



            @Override
            public void onNewDeviceFound(BluetoothDevice device) {
                System.out.println("----------"+device.getAddress());
            }

            @Override
            public void onSearchCompleted(List<BluetoothDevice> list, List<BluetoothDevice> list1) {
                System.out.println("onSearchCompleted-----"+list.size()+""+list1.size());
                List<BluetoothDevice>   bluetoothDevices = new LinkedList<>();
                bluetoothDevices.addAll(list);
                bluetoothDevices.addAll(list1);
                mResultAdapter.setNewData(bluetoothDevices);
                swipe.setRefreshing(false);
            }

            @Override
            public void onError(Exception e) {
                swipe.setRefreshing(false);
                showError("扫描出错了");
            }
        });
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_bluetooth;
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
      setToolBar(toolbar,"蓝牙设置");
    }


    @Override
    public void stopScan() {

        swipe.setRefreshing(false);
    }



    @OnClick(R.id.fab)
    public   void  fab(){
        refreshBluebooth();
    }


    @OnClick(R.id.btn_printtest)
    public   void  btn_printtest(){
       String   addr = PreferencesUtil.get("bluetooth_mac","");
        if(TextUtils.isEmpty(addr)){
            showError("当前蓝牙设备未选中，无法测试");
            return;
        }

        RxUtil.createData(btHelperClient.connect(addr)).compose(RxUtil.<BluetoothSocket>IO_Main())
                .subscribe(new Consumer<BluetoothSocket>() {
                    @Override
                    public void accept(BluetoothSocket bluetoothSocket) throws Exception {
                        if(null != bluetoothSocket){
                             print(bluetoothSocket);
                        }else{
                            showError("连接失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                      throwable.printStackTrace();
                        showError("出错了"+throwable.getMessage());
                    }
                });

    }


    public   void   print(BluetoothSocket  socket){
        RxUtil.createData(socket).subscribe(new Consumer<BluetoothSocket>() {
            @Override
            public void accept(BluetoothSocket socket) throws Exception {
               PrintUtils.setOutputStream(socket.getOutputStream());
               PrintUtils.selectCommand(PrintUtils.RESET);
               PrintUtils.selectCommand(PrintUtils.LINE_SPACING_DEFAULT);
               PrintUtils.selectCommand(PrintUtils.ALIGN_CENTER);
               PrintUtils.printText("重庆问天食品有限公司\n\n");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                showError("打印出错了"+throwable.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != btHelperClient){
            btHelperClient.close();
        }
        if(null != bluetoothHelp){
            bluetoothHelp.closeBluetoohHelp();
        }
    }


    @Override
    public void onError(Exception e) {
        e.printStackTrace();
        System.out.println("--------------------");
        RxUtil.createData(e).compose(RxUtil.<Exception>IO_Main()).subscribe(new Consumer<Exception>() {
            @Override
            public void accept(Exception e) throws Exception {
                CrashReport.postCatchedException(e);
                showError("出错了"+e.getMessage());
            }
        });
    }

    @Override
    public void onConnectionLost(Exception e) {
        e.printStackTrace();
        RxUtil.createData(e).compose(RxUtil.<Exception>IO_Main()).subscribe(new Consumer<Exception>() {
            @Override
            public void accept(Exception e) throws Exception {
                CrashReport.postCatchedException(e);
                showError("与蓝牙设备连接丢失，请重新尝试"+e.getMessage());
            }
        });

    }

    @Override
    public void onSuccess(int status, String response) {

    }
}
