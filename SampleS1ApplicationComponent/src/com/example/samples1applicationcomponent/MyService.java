package com.example.samples1applicationcomponent;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	public static final String ACTION_MOD_TEN = "com.example.samples1applicationcomponent.action.zeroToTen";
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	boolean isRunning = false;
	int mCount = 0;
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "onCreate...", Toast.LENGTH_SHORT).show();
		isRunning = true;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(isRunning) {
					mCount++;
					Log.i("MyService", "count : " + mCount);
					if (mCount % 10 == 0) {
						Intent intent = new Intent(ACTION_MOD_TEN);
						intent.putExtra("count", mCount);
//						sendBroadcast(intent);
						sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
							@Override
							public void onReceive(Context context, Intent intent) {
								int code = getResultCode();
								if (code == Activity.RESULT_CANCELED) {
									Toast.makeText(MyService.this, "not process", Toast.LENGTH_SHORT).show();
								}
							}
						}, null, Activity.RESULT_CANCELED, null, null);
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		
		registerReceiver(screenReceiver, filter);
	}
	
	BroadcastReceiver screenReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
				Toast.makeText(context, "Screen on", Toast.LENGTH_SHORT).show();
			} else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
				Log.i("MyService", "Screen off");
			}
		}
		
	};

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "onStartCommand...", Toast.LENGTH_SHORT).show();
		if (intent != null) {
			int count = intent.getIntExtra("count", 0);
			mCount = count;
		}
		return Service.START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		isRunning = false;
		Toast.makeText(this, "onDestroy...", Toast.LENGTH_SHORT).show();
		unregisterReceiver(screenReceiver);
	}
}
