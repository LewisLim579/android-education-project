package com.example.samples2androidcomponent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context, MyService.class);
		i.putExtra("count", 200);
		context.startService(i);
	}

}
