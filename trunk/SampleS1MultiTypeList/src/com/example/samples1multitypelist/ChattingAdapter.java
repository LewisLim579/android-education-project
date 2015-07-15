package com.example.samples1multitypelist;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ChattingAdapter extends BaseAdapter {

	List<ChattingMessage> mItems = new ArrayList<ChattingMessage>();

	private static final int VIEW_TYPE_COUNT = 3;
	private static final int VIEW_RECEIVE_INDEX = 0;
	private static final int VIEW_SEND_INDEX = 1;
	private static final int VIEW_DATE_INDEX = 2;

	public void add(ChattingMessage item) {
		mItems.add(item);
		notifyDataSetChanged();
	}
	
	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE_COUNT;
	}

	@Override
	public int getItemViewType(int position) {
		switch (mItems.get(position).getType()) {
		case ChattingMessage.TYPE_RECEIVE:
			return VIEW_RECEIVE_INDEX;
		case ChattingMessage.TYPE_SEND:
			return VIEW_SEND_INDEX;
		case ChattingMessage.TYPE_DATE:
		default:
			return VIEW_DATE_INDEX;
		}
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
		switch (getItemViewType(position)) {
		case VIEW_RECEIVE_INDEX: {
			LeftView view;
			if (convertView != null && convertView instanceof LeftView) {
				view = (LeftView)convertView;
			} else {
				view = new LeftView(parent.getContext());
			}
			view.setLeftData((LeftData)mItems.get(position));
			return view;
		}
		case VIEW_SEND_INDEX: {
			RightView view;
			if (convertView != null && convertView instanceof RightView) {
				view = (RightView)convertView;
			} else {
				view = new RightView(parent.getContext());
			}
			view.setRightData((RightData)mItems.get(position));
			return view;
		}
		case VIEW_DATE_INDEX:
		default: {
			DateView view;
			if (convertView != null && convertView instanceof DateView) {
				view = (DateView)convertView;
			}else {
				view = new DateView(parent.getContext());
			}
			view.setDateData((DateData)mItems.get(position));
			return view;
		}
		}
	}

}
