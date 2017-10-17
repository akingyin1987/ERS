package com.askfood.ers.ui.activity;


import android.content.Intent;
import android.widget.ImageView;

import com.askfood.ers.R;
import com.askfood.ers.base.SimpleActivity;
import com.askfood.ers.base.dialog.ToastUtil;
import com.askfood.ers.utils.PreferencesUtil;

import java.util.zip.ZipEntry;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ZxingScanActivity extends SimpleActivity implements QRCodeView.Delegate{
    @BindView(R.id.zxingview)
    ZXingView zxingview;
    @BindView(R.id.iv_light)
    ImageView ivLight;

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zxing_scan;
    }

    @Override
    protected void initEventAndData() {
        ButterKnife.bind(this);
        zxingview.setDelegate(this);
        int   light = PreferencesUtil.get("zxing_ligth",0);
        ivLight.setTag(light);
        if(light == 0){
            ivLight.setImageResource(R.drawable.icon_close_light);
        }else if(light == 1){
            ivLight.setImageResource(R.drawable.icon_open_light);
            zxingview.openFlashlight();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        zxingview.startCamera();
        zxingview.showScanRect();
        zxingview.startSpot();
    }

    @Override
    protected void onStop() {
        zxingview.stopCamera();
        zxingview.stopSpot();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zxingview.onDestroy();

        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String s) {
        zxingview.stopCamera();
        ToastUtil.showInfo(this,s);
        Intent  intent  = new Intent();
        intent.putExtra("data",s);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        ToastUtil.showError(this,"相机出错了，将返回");
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick(R.id.iv_light)
    public   void   onLight(){
        try {
            int  tag = Integer.parseInt(ivLight.getTag().toString());
            if(tag == 0){
                ivLight.setTag(1);
                ivLight.setImageResource(R.drawable.icon_open_light);
                zxingview.openFlashlight();
            }else{
                zxingview.closeFlashlight();
                ivLight.setTag(0);
                ivLight.setImageResource(R.drawable.icon_close_light);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
