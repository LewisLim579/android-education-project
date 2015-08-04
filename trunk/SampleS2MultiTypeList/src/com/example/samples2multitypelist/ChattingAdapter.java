package com.example.samples2multitypelist;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ChattingAdapter extends BaseAdapter {
	List<ChattingData> items = new ArrayList<ChattingData>();

	private static final int VIEW_TYPE_COUNT = 3;

	private static final int VIEW_INDEX_SEND = 0;
	private static final int VIEW_INDEX_RECEIVE = 1;
	private static final int VIEW_INDEX_DATE = 2;

	public void add(ChattingData item) {
		items.add(item);
		notifyDataSetChanged();
	}
	
	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE_COUNT;
	}

	@Override
	public int getItemViewType(int position) {
		ChattingData d = items.get(position);
		if (d instanceof SendData) {
			return VIEW_INDEX_SEND;
		} else if (d instanceof ReceiveData) {
			return VIEW_INDEX_RECEIVE;
		} else if (d instanceof DateData) {
			return VIEW_INDEX_DATE;
		}
		return VIEW_INDEX_SEND;
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
		switch (getItemViewType(position)) {
		case VIEW_INDEX_SEND: {
			SendView view;
			if (convertView != null && convertView instanceof SendView) {
				view = (SendView) convertView;
			} else {
				view = new SendView(parent.getContext());
			}
			view.setSendData((SendData) items.get(position));
			return view;
		}
		case VIEW_INDEX_RECEIVE: {
			ReceiveView view;
			if (convertView != null && convertView instanceof ReceiveView) {
				view = (ReceiveView) convertView;
			} else {
				view = new ReceiveView(parent.getContext());
			}
			view.setReceiveData((ReceiveData) items.get(position));
			return view;
		}
		case VIEW_INDEX_DATE: {
			DateView view;
			if (convertView != null && convertView instanceof DateView) {
				view = (DateView) convertView;
			} else {
				view = new DateView(parent.getContext());
			}
			view.setDateData((DateData) items.get(position));
			return view;
		}
		}
		return null;
	}

}
