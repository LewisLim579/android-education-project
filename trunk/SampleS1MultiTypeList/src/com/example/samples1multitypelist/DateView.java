package com.example.samples1multitypelist;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

public class DateView extends FrameLayout {

	public DateView(Context context) {
		super(context);
		init();
	}
	
	TextView dateView;
	DateData mData;
	
	private void init() {
		inflate(getContext(), R.layout.view_date, this);
		dateView = (TextView)findViewById(R.id.text_date);
	}
	
	public void setDateData(DateData data) {
		mData = data;
		dateView.setText(data.date);
	}

}
