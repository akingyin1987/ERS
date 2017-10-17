package com.askfood.ers.ui.adapter;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askfood.ers.R;
import com.chad.library.adapter.base.BaseViewHolder;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/15.
 */

public class BluetoothViewHoler  extends BaseViewHolder {

    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.txt_mac)
    TextView txt_mac;
    @BindView(R.id.txt_rssi)
    TextView txt_rssi;


    public BluetoothViewHoler(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
    public BluetoothViewHoler(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_scan_result, parent, false));
    }

    public   void   bind(BluetoothDevice  result){

        BluetoothDevice device = result;
        txt_name.setText(device.getName());
       txt_mac.setText(device.getAddress());
        txt_rssi.setVisibility(View.GONE);
    }
}
