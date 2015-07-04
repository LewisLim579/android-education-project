package com.example.hellonaver;

import android.support.v7.app.ActionBarActivity;

public class ParentActivity extends ActionBarActivity {

	@Override
	protected void onDestroy() {
		super.onDestroy();
		NetworkManager.getInstance().cancelAll(this);
	}
	
}
