package com.example.samples2expandablelistview;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ChildView extends FrameLayout {

	public ChildView(Context context) {
		super(context);
		init();
	}
	
	TextView nameView;
	private void init() {
		inflate(getContext(), R.layout.view_child, this);
		nameView = (TextView)findViewById(R.id.text_message);
	}
	
	public void setChildData(ChildData data) {
		nameView.setText(data.childName);
	}
	
	public void setLastView(boolean last) {
		if (last) {
			setBackgroundColor(Color.GREEN);
		} else {
			setBackgroundColor(Color.TRANSPARENT);
		}
	}

}
