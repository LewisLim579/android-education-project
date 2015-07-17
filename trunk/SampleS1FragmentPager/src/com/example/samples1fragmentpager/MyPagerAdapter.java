package com.example.samples1fragmentpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return new Fragment1();
		case 1:
			return new Fragment2();
		case 2:
			return new Fragment3();
		case 3:
			return new Fragment4();
		case 4:
		default:
			return new Fragment5();
		}
	}

	@Override
	public int getCount() {
		return 5;
	}

}
