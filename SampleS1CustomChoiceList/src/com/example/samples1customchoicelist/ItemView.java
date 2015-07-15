package com.example.samples1customchoicelist;

import android.content.Context;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemView extends FrameLayout implements Checkable {

	public ItemView(Context context) {
		super(context);
		init();
	}

	ImageView iconView, checkView;
	TextView titleView;
	ItemData mData;
	
	private void init() {
		inflate(getContext(), R.layout.view_item, this);
		iconView = (ImageView)findViewById(R.id.imageView1);
		titleView = (TextView)findViewById(R.id.textView1);
		checkView = (ImageView)findViewById(R.id.imageView2);
	}
	
	public void setItemData(ItemData data) {
		mData = data;
		iconView.setImageResource(data.iconId);
		titleView.setText(data.title);
	}
	
	boolean isChecked;
	
	private void drawCheck() {
		if (isChecked) {
			checkView.setImageResource(android.R.drawable.checkbox_on_background);
		} else {
			checkView.setImageResource(android.R.drawable.checkbox_off_background);
		}
	}
	
	@Override
	public void setChecked(boolean checked) {
		if (isChecked != checked) {
			isChecked = checked;
			drawCheck();
		}
	}

	@Override
	public boolean isChecked() {
		return isChecked;
	}

	@Override
	public void toggle() {
		setChecked(!isChecked);
	}

}
