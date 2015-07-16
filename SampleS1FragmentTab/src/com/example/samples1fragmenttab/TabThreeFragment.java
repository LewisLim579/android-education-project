package com.example.samples1fragmenttab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabThreeFragment extends Fragment {

	FragmentTabHost tabHost;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_three, container, false);
		tabHost = (FragmentTabHost)v.findViewById(R.id.tabhost);
		tabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("TAB1"), TabChildOneFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("TAB2"), TabChildTwoFragment.class, null);
		return v;
	}

}
