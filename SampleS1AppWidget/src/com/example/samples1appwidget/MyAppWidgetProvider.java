package com.example.samples1appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider {


	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_item);
		views.setImageViewResource(R.id.image_icon, R.drawable.gallery_photo_1);
		views.setTextViewText(R.id.text_title, "gallery photo 1");
		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
		views.setOnClickPendingIntent(R.id.image_icon, pi);
		
		appWidgetManager.updateAppWidget(appWidgetIds, views);
		
		
	}
}
