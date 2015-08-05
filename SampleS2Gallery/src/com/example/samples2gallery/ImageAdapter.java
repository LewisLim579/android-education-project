package com.example.samples2gallery;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	List<Integer> items = new ArrayList<Integer>();

	public void add(int resId) {
		items.add(resId);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		if (items.size() == 0) return 0;
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return items.get(position % items.size());
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
			view.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));			
		} else {
			view = (ImageView)convertView;
		}
		view.setImageResource(items.get(position % items.size()));
		return view;
	}

}
