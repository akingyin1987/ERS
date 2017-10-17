package com.askfood.ers.bluetooth;



import java.util.ArrayList;
import java.util.Set;




import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;


/**
 * 蓝牙配对帮助类
 * @author zlcd
 *
 */
public class BluetoothHelp {
	// 蓝牙打印的UUID
		public static final String SPP_UUID = "00001101-0000-1000-8000-00805f9b34fb";
		private static final String TAG = "BluetoothHelp";
		// 蓝牙适配器
		private BluetoothAdapter mBluetoothAdapter;
		private ProgressDialog mpd;
		private AlertDialog dialog = null;
		private BluetoothDevice mBluetoothReader;
		private Context context;
		// 蓝牙读卡器设备标识
		private   String BLUETOOTH_MAC = "C0:00:00:0C:8F";

		// 消息标识符
		public static final int MESSAGE_STATE_CHANGE = 1;

		public static final int MESSAGE_DEVICE_NAME = 4;
		public static final int MESSAGE_DIALOG = 5;
		public static final String DIALOG = "dialog";

		public static final int STATE_CONNECTED = 2;
		public static final int STATE_CONNECTING = 1;

		private EnableBluetoothDeviceTask mEnableBluetoothDeviceTask;// 开启蓝牙

		
        public static final int MODE_PRIVATE = 0;
		

		

		public Context getContext() {
			return context;
		}

		public void setContext(Context context) {
			this.context = context;
		}


	

		public BluetoothHelp(Context context) {
			this.context = context;

			init();
			
		}

		/**
		 * 初始化
		 */
		public void init() {
			// 初始化
			mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			mpd = new ProgressDialog(context);
			mpd.setCancelable(false);
			dialog = new AlertDialog.Builder(context).setPositiveButton("确定", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).create();			

		}

		public boolean CheckBluetooth() {

			// 检查蓝牙设备
			if (mBluetoothAdapter == null) {
				Log.d(TAG, "没有找到蓝牙设备");
				new AlertDialog.Builder(context).setCancelable(false).setIcon(android.R.drawable.ic_dialog_info).setTitle("警告").setMessage("没有找到蓝牙设备").setPositiveButton("确定", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// 通知用户开启蓝牙
						Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
						context.startActivity(intent);
					}
				});
				return false;
			}
			return true;
		}

		
		// 搜索蓝牙设备
		public void requestOpenBluetoothDevice() {


			//如果蓝牙未打开则请求打开蓝牙
			if (!mBluetoothAdapter.isEnabled()) {
				mEnableBluetoothDeviceTask = new EnableBluetoothDeviceTask();// 开启蓝牙
				mEnableBluetoothDeviceTask.execute();
				return;
			}

			if(mBluetoothAdapter.getBondedDevices().size() == 0){
				return;
			}
		}



		private class EnableBluetoothDeviceTask extends AsyncTask<Integer, Integer, Boolean> {

			private static final int miSLEEP_TIME = 200;
			private static final int miWATI_TIME = 20;
			private static final int mTRY_TIME = 3;
			private BluetoothAdapter adapter = mBluetoothAdapter;

			private EnableBluetoothDeviceTask() {
			}

			@Override
			protected void onPreExecute() {
				closeDialog();
				mpd = new ProgressDialog(context);
				mpd.setCancelable(false);
				mpd.setTitle("提示");
				mpd.setMessage("正在开启蓝牙设备，请稍后....");
				mpd.show();
			}

			@Override
			protected Boolean doInBackground(Integer... params) {
				Boolean res = false;
				for (int i = 0; i < mTRY_TIME; i++) {

					if (!adapter.isEnabled()) {
						// 打开蓝牙设备
						adapter.enable();
					}

					for (int j = 0; j < miWATI_TIME; j++) {
						if (adapter.isEnabled()) {
							res = true;
							break;
						} else {
							try {
								Thread.sleep(miSLEEP_TIME);
							} catch (InterruptedException e) {
								break;
							}
						}
					}
				}

				return res;
			}

			@Override
			protected void onPostExecute(Boolean result) {
				closeDialog();
				if (!result) {

					adapter.disable();
				}
			}
		}

	

	
		



		private void connectionLost(String msg) {
			@SuppressWarnings("unused")
			AlertDialog dialog = new AlertDialog.Builder(context).setTitle("警告").setCancelable(false).setIcon(android.R.drawable.ic_dialog_info).setMessage(msg).setPositiveButton("确定", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			}).show();
		}


		/**
		 * 关闭
		 */
		public void closeBluetoohHelp() {
			closeDialog();


			if (mEnableBluetoothDeviceTask != null) {
				mEnableBluetoothDeviceTask.cancel(true);
			}
			
		}

		private void closeDialog() {
          try{
				if (null != mpd && mpd.isShowing()) {
					mpd.dismiss();
				}
				if (null != dialog && dialog.isShowing()) {
					dialog.dismiss();
				}
			}catch (Exception e){
				e.printStackTrace();
			}

		}
}
