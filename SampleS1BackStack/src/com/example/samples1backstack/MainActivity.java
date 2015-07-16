package com.example.samples1backstack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Fragment[] list = {new FirstFragment(), new SecondFragment(), new ThirdFragment()};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.container, new BaseFragment()).commit();
		Button btn = (Button)findViewById(R.id.btn_prev);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = getSupportFragmentManager().getBackStackEntryCount();
				if (index > 0) {
					getSupportFragmentManager().popBackStack();
				} else {
					Toast.makeText(MainActivity.this, "Base...", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		btn = (Button)findViewById(R.id.btn_next);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = getSupportFragmentManager().getBackStackEntryCount();
				if (index < list.length) {
					FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
					ft.replace(R.id.container, list[index]);
					ft.addToBackStack(null);
					ft.commit();
				} else {
					getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
					Toast.makeText(MainActivity.this, "finish...", Toast.LENGTH_SHORT).show();
				}
			}
		});
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
		return super.onOptionsItemSelected(item);
	}
}
