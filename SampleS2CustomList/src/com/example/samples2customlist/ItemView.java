package com.example.samples2customlist;

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

}
