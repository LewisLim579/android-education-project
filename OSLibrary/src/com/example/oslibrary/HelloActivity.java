package com.example.oslibrary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelloActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		LinearLayout ll = new LinearLayout(this);
//		ll.setOrientation(LinearLayout.VERTICAL);
//		TextView tv = new TextView(this);
//		tv.setText("Hello, Library Activity");
//		ll.addView(tv);
//		setContentView(ll);
		
		setContentView(R.layout.activity_hello);
	}
}
