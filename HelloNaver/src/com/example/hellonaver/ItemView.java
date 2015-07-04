package com.example.hellonaver;

import android.content.Context;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemView extends LinearLayout {

	public ItemView(Context context) {
		super(context);
		init();
	}
	ImageView iconView;
	TextView titleView, directorView;
	MovieItem mData;
	private void init() {
		inflate(getContext(), R.layout.view_item, this);
		iconView = (ImageView)findViewById(R.id.image_icon);
		titleView = (TextView)findViewById(R.id.text_title);
		directorView = (TextView)findViewById(R.id.text_director);
	}
	
	public void setMovieItem(MovieItem item) {
		mData = item;
		titleView.setText(Html.fromHtml(item.title));
		directorView.setText(item.director);
		
	}
	

}
