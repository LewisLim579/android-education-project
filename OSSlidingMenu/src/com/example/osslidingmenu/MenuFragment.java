package com.example.osslidingmenu;

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

public class MenuFragment extends Fragment {
	ListView listView;
	ArrayAdapter<String> mAdapter;
	public static final int MENU_MAIN = 0;
	public static final int MENU_ONE = 1;
	public static final int MENU_TWO = 2;
	
	public interface MenuCallback {
		public void onMenuSelected(int menuId);
	}
	
	MenuCallback mCallback;
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof MenuCallback) {
			mCallback = (MenuCallback)activity;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_menu, container, false);
		listView = (ListView)v.findViewById(R.id.listView1);
		mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
		listView.setAdapter(mAdapter);
		mAdapter.add("Main");
		mAdapter.add("One");
		mAdapter.add("Two");
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch(position) {
				case 0 :
					selectMenu(MENU_MAIN);
					break;
				case 1 :
					selectMenu(MENU_ONE);
					break;
				case 2 :
					selectMenu(MENU_TWO);
					break;
				}
			}
		});
		return v;
	}
	
	private void selectMenu(int menuId) {
		if (mCallback != null) {
			mCallback.onMenuSelected(menuId);
		}
	}
}
