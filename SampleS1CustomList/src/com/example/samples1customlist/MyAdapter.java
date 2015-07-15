package com.example.samples1customlist;

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
	
	public void remove(ItemData item) {
		mItems.remove(item);
		notifyDataSetChanged();
	}
	
	public void addAll(List<ItemData> items) {
		mItems.addAll(items);
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
		view = new ItemView(parent.getContext());
		view.setItemData(mItems.get(position));
		return view;
	}

}
