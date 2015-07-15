package com.example.samples1gallery;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	List<Integer> mItems = new ArrayList<Integer>();
	int width, height;
	
	public ImageAdapter(Context context) {
		width = context.getResources().getDimensionPixelSize(R.dimen.gallery_item_width);
		height = context.getResources().getDimensionPixelSize(R.dimen.gallery_item_height);
	}
	
	public void add(int id) {
		mItems.add(id);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {		
		return mItems.size() == 0 ? 0 : Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position % mItems.size());
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView view;
		if (convertView == null) {
			view = new ImageView(parent.getContext());
			Gallery.LayoutParams params = new Gallery.LayoutParams(width, height);
			view.setLayoutParams(params);
			view.setScaleType(ImageView.ScaleType.FIT_XY);
		} else {
			view = (ImageView)convertView;
		}
		view.setImageResource(mItems.get(position % mItems.size()));
		return view;
	}

}
