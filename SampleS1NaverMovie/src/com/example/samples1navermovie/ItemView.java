package com.example.samples1navermovie;

import android.content.Context;
import android.text.Html;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ItemView extends FrameLayout {

	public ItemView(Context context) {
		super(context);
		init();
	}
	
	ImageView iconView;
	TextView titleView, directorView;
	MovieItem item;
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
	
	public void setMovieItem(MovieItem data) {
		item = data;
		titleView.setText(Html.fromHtml(data.title));
		directorView.setText(data.director);
		ImageLoader.getInstance().displayImage(data.image, iconView, options);
	}

}
