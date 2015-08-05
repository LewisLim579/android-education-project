package com.example.samples2custionchoicelist;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.samples2custionchoicelist.ItemView.OnCommentClickListener;

public class MyAdapter extends BaseAdapter implements ItemView.OnCommentClickListener {

	List<ItemData> items = new ArrayList<ItemData>();

	ItemView.OnCommentClickListener mCommentListener;
	
	public void setOnCommentClickListener(OnCommentClickListener listener) {
		mCommentListener = listener;
	}
	
	public void add(ItemData item) {
		items.add(item);
		notifyDataSetChanged();
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
//			if (mCommentListener != null) {
//				view.setOnCommentClickListener(mCommentListener);
//			}
			view.setOnCommentClickListener(this);
		} else {
			view = (ItemView) convertView;
		}
		view.setItemData(items.get(position));
		return view;
	}

	@Override
	public void onCommentClick(ItemView view, ItemData data) {
		if (mCommentListener != null) {
			mCommentListener.onCommentClick(view, data);
		}
	}

}
