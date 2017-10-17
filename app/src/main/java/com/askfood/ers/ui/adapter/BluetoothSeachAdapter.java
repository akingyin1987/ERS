package com.askfood.ers.ui.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.askfood.ers.injection.scope.ActivityContext;
import com.chad.library.adapter.base.BaseQuickAdapter;




import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/15.
 */

public class BluetoothSeachAdapter  extends BaseQuickAdapter<BluetoothDevice,BluetoothViewHoler> {

    LayoutInflater  mInflater;


    @Inject
    public BluetoothSeachAdapter(@ActivityContext Context  context) {
        super(null);
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    protected BluetoothViewHoler onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return new BluetoothViewHoler(mInflater,parent);
    }

    @Override
    protected void convert(BluetoothViewHoler helper, BluetoothDevice item) {
        helper.bind(item);
    }
}
