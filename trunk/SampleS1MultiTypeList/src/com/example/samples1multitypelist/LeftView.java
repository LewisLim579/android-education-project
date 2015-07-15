package com.example.samples1multitypelist;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class LeftView extends FrameLayout {

	public LeftView(Context context) {
		super(context);
		init();
	}

	ImageView iconView;
	TextView messageView;
	LeftData mData;
	
	private void init() {
		inflate(getContext(), R.layout.view_left, this);
		iconView = (ImageView)findViewById(R.id.image_icon);
		messageView = (TextView)findViewById(R.id.text_message);
	}
	
	public void setLeftData(LeftData data) {
		mData = data;
		iconView.setImageResource(data.iconId);
		messageView.setText(data.message);
	}

}
