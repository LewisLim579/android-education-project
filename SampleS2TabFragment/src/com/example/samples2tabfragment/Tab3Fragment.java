package com.example.samples2tabfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tab3Fragment extends Fragment {
	FragmentTabHost tabHost;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_child_tab, container, false);
		tabHost = (FragmentTabHost)view.findViewById(R.id.tabhost);
		tabHost.setup(getActivity(), getChildFragmentManager(), R.id.childtabcontent);
		tabHost.addTab(tabHost.newTabSpec("child1").setIndicator("CHILD1"), ChildTab1Fragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("child2").setIndicator("CHILD2"), ChildTab2Fragment.class, null);
		return view;
	}
}
