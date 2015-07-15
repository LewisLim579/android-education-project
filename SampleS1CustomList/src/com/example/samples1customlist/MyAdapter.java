package com.example.samples1customlist;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter implements ItemView.OnImageClickListener {

	List<ItemData> mItems = new ArrayList<ItemData>();
	
	public interface OnAdapterItemListener {
		public void onAdapterItemClick(MyAdapter adapter, ItemView view, ItemData data);
	}
	OnAdapterItemListener mListener;
	public void setOnAdapterItemListener(OnAdapterItemListener listener) {
		mListener = listener;
	}
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
		if (convertView == null) {
			view = new ItemView(parent.getContext());
			view.setOnImageClickListener(this);
		} else {
			view = (ItemView)convertView;
		}
		view.setItemData(mItems.get(position));
		return view;
	}

	@Override
	public void onImageClick(ItemView view, ItemData data) {
		if (mListener != null) {
			mListener.onAdapterItemClick(this, view, data);
		}
	}

}
