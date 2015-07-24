package com.example.samples1notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	NotificationManager mNM;
	int mId = 1;
	int messageId = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        
        Button btn = (Button)findViewById(R.id.btn_notification);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
				messageId++;
				builder.setSmallIcon(android.R.drawable.ic_dialog_info);
				builder.setTicker("Notification Test...");
				builder.setContentTitle("Noti Test... : " + messageId);
				builder.setContentText("notification .....");
				builder.setWhen(System.currentTimeMillis());
				
				Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
				intent.setData(Uri.parse("myscheme://com.example.samples1notification/"+messageId));
				intent.putExtra(NotificationActivity.EXTRA_MESSAGE, "noti test...." + messageId);
				
				PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				builder.setContentIntent(pi);
				
				builder.setDefaults(NotificationCompat.DEFAULT_ALL);				
				builder.setAutoCancel(true);
				
				Notification n = builder.build();
				mNM.cancel(mId);
				mNM.notify(mId, n);
				
			}
		});
        
        
        btn = (Button)findViewById(R.id.btn_progress);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				current = 0;
				mHandler.post(downloadRunnable);
			}
		});
        
        btn = (Button)findViewById(R.id.btn_big_text);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
				messageId++;
				builder.setSmallIcon(android.R.drawable.ic_dialog_info);
				builder.setTicker("Notification Test...");
				builder.setContentTitle("Noti Test... : " + messageId);
				builder.setContentText("notification .....");
				builder.setWhen(System.currentTimeMillis());
				
				NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
				style.bigText("test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...test...");
				builder.setStyle(style);
				Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
				intent.setData(Uri.parse("myscheme://com.example.samples1notification/"+messageId));
				intent.putExtra(NotificationActivity.EXTRA_MESSAGE, "noti test...." + messageId);
				
				PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				builder.setContentIntent(pi);
				
				builder.setDefaults(NotificationCompat.DEFAULT_ALL);				
				builder.setAutoCancel(true);
				
				Notification n = builder.build();
				mNM.notify(mId, n);
			}
		});
    }

    Handler mHandler = new Handler(Looper.getMainLooper());
    
    int current = 0;
    Runnable downloadRunnable = new Runnable() {
		
		@Override
		public void run() {
			if (current <= 100) {
				NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
				builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
				builder.setTicker("progress : " + current);
				builder.setContentTitle("download .... " + current);
				builder.setProgress(100, current, false);
				builder.setOnlyAlertOnce(true);
				builder.setOngoing(true);
				mNM.notify(1000, builder.build());
				current += 5;
				mHandler.postDelayed(this, 500);
			} else {
				mNM.cancel(1000);
			}
		}
	};
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
