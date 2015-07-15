package com.example.samples1customlist;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemView extends FrameLayout {

	public ItemView(Context context) {
		super(context);
		init();
	}
	
	ImageView iconView;
	TextView titleView, descView;
	ItemData mData;
	private void init() {
		inflate(getContext(), R.layout.view_item, this);
		iconView = (ImageView)findViewById(R.id.image_icon);
		titleView = (TextView)findViewById(R.id.text_title);
		descView = (TextView)findViewById(R.id.text_desc);
	}
	
	public void setItemData(ItemData data) {
		mData = data;
		iconView.setImageResource(data.iconResId);
		titleView.setText(data.title);
		descView.setText(data.desc);
	}

}
