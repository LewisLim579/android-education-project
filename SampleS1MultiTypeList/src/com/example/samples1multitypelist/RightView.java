package com.example.samples1multitypelist;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

public class RightView extends FrameLayout {

	public RightView(Context context) {
		super(context);
		init();
	}
	
	TextView messageView;
	RightData mData;
	
	private void init() {
		inflate(getContext(), R.layout.view_right, this);
		messageView = (TextView)findViewById(R.id.text_message);
	}
	
	public void setRightData(RightData data) {
		mData = data;
		messageView.setText(data.message);
	}

}
