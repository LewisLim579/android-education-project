package com.example.samples1customlist;

import android.content.Context;
import android.view.View;
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
	
	public interface OnImageClickListener {
		public void onImageClick(ItemView view, ItemData data);
	}
	
	OnImageClickListener mListener;
	public void setOnImageClickListener(OnImageClickListener listener) {
		mListener = listener;
	}
	
	private void init() {
		inflate(getContext(), R.layout.view_item, this);
		iconView = (ImageView)findViewById(R.id.image_icon);
		iconView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onImageClick(ItemView.this, mData);
				}
			}
		});
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
