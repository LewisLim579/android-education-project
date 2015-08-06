package com.example.samples2tabfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChildTab1Fragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message, container, false);
		TextView tv = (TextView)view.findViewById(R.id.text_message);
		tv.setText("Child Tab 1 Content");
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getActivity().setTitle("ChildTab1");
	}
	
}
