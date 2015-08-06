package com.example.samples2androidcomponent;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private static final String TAG = "MyService";
	boolean isRunning;
	int mCount;
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "onCreate...", Toast.LENGTH_SHORT).show();
		mCount = 0;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				isRunning = true;
				while(isRunning) {
					mCount++;
					
					Log.i(TAG,"count : " + mCount);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}).start();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "onStartCommand...", Toast.LENGTH_SHORT).show();
		if (intent != null) {
			mCount = intent.getIntExtra("count", 0);
		}
		return Service.START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "onDestroy...", Toast.LENGTH_SHORT).show();
		isRunning = false;
	}
}
