package com.example.networktestnaver;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter {

	List<MovieItem> items = new ArrayList<MovieItem>();

	public void addAll(List<MovieItem> items) {
		this.items.addAll(items);
		notifyDataSetChanged();
	}
	
	String keyword;
	int total;
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getTotal() {
		return total;
	}
	
	public int getStart() {
		if (total > items.size()) {
			return items.size() + 1;
		}
		return -1;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
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
		view.setMovieItem(items.get(position));
		return view;
	}

}
