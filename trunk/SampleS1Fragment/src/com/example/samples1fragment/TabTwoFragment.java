package com.example.samples1fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TabTwoFragment extends Fragment {

	ListView listView;
	ArrayAdapter<String> mAdapter;
	
	public interface MessageReceiver {
		public void setMessage(String message);
	}
	
	MessageReceiver mCallback = null;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof MessageReceiver) {
			mCallback = (MessageReceiver)activity;
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
		initData();
	}
	
	private void initData() {
		for (int i = 0; i < 20; i++) {
			mAdapter.add("item"+i);
		}
	}
	
	public String getFragmentMessage() {
		return "Hi! Fragment";
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tab2, container, false);
		listView = (ListView)view.findViewById(R.id.listView1);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String message = (String)listView.getItemAtPosition(position);
				if (mCallback != null) {
					mCallback.setMessage(message);
				}
//				((MainActivity)getActivity()).setMessage(message);
			}
		});
		return view;
	}
}
