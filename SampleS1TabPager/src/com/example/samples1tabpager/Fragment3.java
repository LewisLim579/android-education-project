package com.example.samples1tabpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment3 extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_layout, container, false);
		TextView tv = (TextView)v.findViewById(R.id.text_name);
		tv.setText("Fragment3");
		return v;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Toast.makeText(getActivity(), "resume Fragment3", Toast.LENGTH_SHORT).show();
	}
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			Toast.makeText(getActivity(), "visible Fragment3", Toast.LENGTH_SHORT).show();
		}
	}
	
}
