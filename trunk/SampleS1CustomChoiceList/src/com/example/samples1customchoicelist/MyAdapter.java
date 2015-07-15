package com.example.samples1customchoicelist;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter {

	List<ItemData> mItems = new ArrayList<ItemData>();
	
	public void add(ItemData item) {
		mItems.add(item);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemView view;
		if (convertView == null) {
			view = new ItemView(parent.getContext());
		} else {
			view = (ItemView)convertView;
		}
		view.setItemData(mItems.get(position));
		return view;
	}

}
