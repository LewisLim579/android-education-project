package com.example.samples2custionchoicelist;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemView extends FrameLayout implements Checkable {

	public ItemView(Context context) {
		super(context);
		init();
	}
	
	ImageView iconView,checkView;
	TextView titleView, descView, cmtView;
	ItemData mData;
	
	public interface OnCommentClickListener {
		public void onCommentClick(ItemView view, ItemData data);
	}
	
	OnCommentClickListener mListener;
	public void setOnCommentClickListener(OnCommentClickListener listener) {
		mListener = listener;
	}
	
	private void init() {
		inflate(getContext(), R.layout.view_item, this);
		iconView = (ImageView)findViewById(R.id.image_icon);
		checkView = (ImageView)findViewById(R.id.image_check);
		titleView = (TextView)findViewById(R.id.text_title);
		descView = (TextView)findViewById(R.id.text_desc);
		cmtView = (TextView)findViewById(R.id.text_cmt);
		cmtView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onCommentClick(ItemView.this, mData);
				}
			}
		});
	}
	
	public void setItemData(ItemData data) {
		mData = data;
		iconView.setImageResource(data.iconId);
		titleView.setText(data.title);
		descView.setText(data.desc);
		cmtView.setText("C : " + data.commentCount);
	}

	boolean isChecked = false;
	
	private void drawCheck() {
		if (isChecked) {
			checkView.setImageResource(android.R.drawable.checkbox_on_background);
			setBackgroundColor(Color.RED);
		} else {
			checkView.setImageResource(android.R.drawable.checkbox_off_background);
			setBackgroundColor(Color.TRANSPARENT);
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
