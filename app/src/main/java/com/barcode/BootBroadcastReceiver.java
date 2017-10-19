package com.barcode;

import java.io.File;

import com.askfood.ers.ERSApp;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.jb.Preference;
import android.os.Handler;
import android.os.Message;


public class BootBroadcastReceiver extends BroadcastReceiver {

	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1000:
				Context arg0 = (Context) msg.obj;
				if (Preference.getScanSelfopenSupport(
						ERSApp.get().getApplicationContext(), true)) {
					Intent service = new Intent(arg0, ScanService.class);
					arg0.startService(service);
					System.out.println("BootBroadcastReceiver serviceup");
				}
				break;

			default:
				break;
			}
		};
	};

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		System.out.println("onReceive");
		if (// Intent.ACTION_SCREEN_ON.equals(arg1.getAction())
		// Intent.ACTION_USER_PRESENT.equals(arg1.getAction())
		// Intent.ACTION_BOOT_COMPLETED.equals(arg1.getAction())
		"ScanServiceDestroy".equals(arg1.getAction())) {
			if (Preference.getScanSelfopenSupport(
					ERSApp.get().getApplicationContext(), true)) {
				Intent service = new Intent(arg0, ScanService.class);
				arg0.startService(service);
				System.out.println("BootBroadcastReceiver ScanServiceDestroy");
			}
		} else if (Intent.ACTION_BOOT_COMPLETED.equals(arg1.getAction())&&(!exist("/dev/moto_sdl"))) {
			if (Preference.getScanSelfopenSupport(
					ERSApp.get().getApplicationContext(), true)) {
				Intent service = new Intent(arg0, ScanService.class);
				arg0.startService(service);
				System.out.println("BootBroadcastReceiver ACTION_BOOT_COMPLETED");
			}
//			Message message = new Message();
//			message.what = 1000;
//			message.obj = arg0;
//			mHandler.sendMessageDelayed(message, 5000);
//			System.out.println("BootBroadcastReceiver restart");
		}
	}
	private boolean exist(String path){
		File file = new File(path);
		if (!file.exists()) {
			return false;
		}else
			return true;
	}
}
