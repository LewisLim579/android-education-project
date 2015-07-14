package com.example.samples1compoundwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemView extends LinearLayout {

	public ItemView(Context context) {
		super(context);
		init();
	}
	
	public ItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	ImageView iconView;
	TextView titleView, descView;
	ItemData mData;
	
	public interface OnItemImageClickListener {
		public void onImageClick(ItemView view);
	}
	
	OnItemImageClickListener mListener;
	
	public void setOnItemImageClickListener(OnItemImageClickListener listener) {
		mListener = listener;
	}
	
	
	private void init() {
		setOrientation(LinearLayout.HORIZONTAL);
		inflate(getContext(), R.layout.view_item, this);
		iconView = (ImageView)findViewById(R.id.image_icon);
		iconView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onImageClick(ItemView.this);
				}
			}
		});
		titleView = (TextView)findViewById(R.id.text_title);
		descView = (TextView)findViewById(R.id.text_description);
	}
	
	public void setItemData(ItemData data) {
		mData = data;
		iconView.setImageResource(data.iconResId);
		titleView.setText(data.title);
		descView.setText(data.desc);
	}
	
	public ItemData getItemData() {
		return mData;
	}
	
	public String getTitle() {
		return (mData== null) ? null : mData.title;
	}
	
	public String getDescription() {
		return (mData == null) ? null : mData.desc;
	}
	

}
