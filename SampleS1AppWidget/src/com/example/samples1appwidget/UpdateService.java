package com.example.samples1appwidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.RemoteViews;

public class UpdateService extends Service {

	int[] imageResIds = { R.drawable.gallery_photo_1 ,
			R.drawable.gallery_photo_2 ,
			R.drawable.gallery_photo_3 ,
			R.drawable.gallery_photo_4 ,
			R.drawable.gallery_photo_5 ,
			R.drawable.gallery_photo_6 ,
			R.drawable.gallery_photo_7 ,
			R.drawable.gallery_photo_8 
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	AppWidgetManager mAWM;
	int mIndex = 0;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mAWM = AppWidgetManager.getInstance(this);
		mHandler.post(widgetUpdateRunnable);
	}
	
	Handler mHandler = new Handler(Looper.getMainLooper());
	
	
	Runnable widgetUpdateRunnable = new Runnable() {
		
		@Override
		public void run() {
			RemoteViews views = new RemoteViews(getPackageName(), R.layout.appwidget_item);
			views.setImageViewResource(R.id.image_icon, imageResIds[mIndex % imageResIds.length]);
			views.setTextViewText(R.id.text_title, "title " + mIndex);
			Intent intent = new Intent(UpdateService.this, MainActivity.class);
			PendingIntent pi = PendingIntent.getActivity(UpdateService.this, 0, intent, 0);
			views.setOnClickPendingIntent(R.id.image_icon, pi);
			
			ComponentName cm = new ComponentName(UpdateService.this, MyAppWidgetProvider.class);
			int[] appWidgetIds = mAWM.getAppWidgetIds(cm);
			
			mAWM.updateAppWidget(appWidgetIds, views);
			
			mIndex++;
			mHandler.postDelayed(this, 2000);
		}
	};
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacks(widgetUpdateRunnable);
	}
}
