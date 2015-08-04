package com.example.samples2multitypelist;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

public class DateView extends FrameLayout {

	public DateView(Context context) {
		super(context);
		init();
	}
	
	TextView messageView;
	
	private void init() {
		inflate(getContext(), R.layout.view_date, this);
		messageView = (TextView)findViewById(R.id.text_date);
		
	}
	
	public void setDateData(DateData data) {
		messageView.setText(data.date);
	}

}
