package com.example.osslidingmenu;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends SlidingFragmentActivity implements
		MenuFragment.MenuCallback {

	SlidingMenu mSM;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.menu_layout);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new MainFragment()).commit();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.menu_container, new MenuFragment()).commit();
		}

		mSM = getSlidingMenu();
		

		mSM.setMode(SlidingMenu.LEFT_RIGHT);
		mSM.setBehindOffsetRes(R.dimen.sliding_offset);
		mSM.setShadowWidthRes(R.dimen.shadow_width);
		mSM.setShadowDrawable(R.drawable.shadow);
		mSM.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mSM.setFadeDegree(0.35f);
		mSM.setSecondaryMenu(R.layout.menu_right_layout);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(
				android.R.drawable.ic_dialog_map);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == android.R.id.home) {
			toggle();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onMenuSelected(int menuId) {
		switch (menuId) {
		case MenuFragment.MENU_MAIN:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new MainFragment()).commit();
			break;
		case MenuFragment.MENU_ONE:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new MenuOneFragment()).commit();
			break;
		case MenuFragment.MENU_TWO:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new MenuTwoFragment()).commit();
			break;
		}
		
		showContent();
	}
}
