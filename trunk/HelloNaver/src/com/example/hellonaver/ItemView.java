package com.example.hellonaver;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

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
	DisplayImageOptions options;
	private void init() {
		inflate(getContext(), R.layout.view_item, this);
		iconView = (ImageView)findViewById(R.id.image_icon);
		titleView = (TextView)findViewById(R.id.text_title);
		directorView = (TextView)findViewById(R.id.text_director);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(50))
		.build();
		
	}
	
	public void setMovieItem(MovieItem item) {
		mData = item;
		titleView.setText(Html.fromHtml(item.title));
		directorView.setText(item.director);
		ImageLoader.getInstance().displayImage(item.image, iconView, options);
	}
	

}
