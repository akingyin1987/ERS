package com.askfood.ers.ui.activity;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
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
import android.view.View;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;

import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.ScanBluetoothContract;
import com.askfood.ers.presenter.impl.ScanBluetoothPresenterImpl;
import com.askfood.ers.ui.adapter.BluetoothSeachAdapter;
import com.askfood.ers.utils.PreferencesUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.wuhaojie.bthelper.BtHelperClient;
import top.wuhaojie.bthelper.OnSearchDeviceListener;

/**
 * 蓝牙搜索
 * Created by Administrator on 2017/10/15.
 */

public class ScanBluetootActivity extends AbsBaseActivity<ScanBluetoothPresenterImpl> implements ScanBluetoothContract.View {


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
    @Override
    protected void initEventAndData() {


        tvCureentBluetooth.setText("当前蓝牙："+ PreferencesUtil.get("bluetooth_mac","未选择"));
        reBluetooth.setLayoutManager(new LinearLayoutManager(this));
        reBluetooth.setItemAnimator(new DefaultItemAnimator());
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
              btHelperClient.searchDevices(new OnSearchDeviceListener() {
                  @Override
                  public void onStartDiscovery() {
                      showTips("开始扫描");
                  }

                  @Override
                  public void onNewDeviceFounded(BluetoothDevice bluetoothDevice) {

                  }

                  @Override
                  public void onSearchCompleted(List<BluetoothDevice> list, List<BluetoothDevice> list1) {
                    List<BluetoothDevice>   bluetoothDevices = new LinkedList<>();
                    bluetoothDevices.addAll(list);
                    bluetoothDevices.addAll(list1);
                      mResultAdapter.setNewData(bluetoothDevices);
                  }

                  @Override
                  public void onError(Exception e) {
                      showError("扫描出错了");
                  }
              });
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






}
