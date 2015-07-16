package com.example.samples1fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TabTwoFragment extends Fragment {

	ListView listView;
	ArrayAdapter<String> mAdapter;
	
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
		return view;
	}
}
