package com.example.samples1fragment;

import com.example.samples1fragment.TabTwoFragment.MessageReceiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements MessageReceiver {

	int count = 0;
	static final String TAG_TAB1 = "tab1";
	static final String TAG_TAB2 = "tab2";

	TextView messageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		messageView = (TextView)findViewById(R.id.text_message);
		Button btn = (Button) findViewById(R.id.btn_tab1);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment oldf = getSupportFragmentManager().findFragmentByTag(
						TAG_TAB1);
				if (oldf == null) {
					TabOneFragment f = new TabOneFragment();
					Bundle b = new Bundle();
					count++;
					b.putInt("count", count);
					f.setArguments(b);
					FragmentTransaction ft = getSupportFragmentManager()
							.beginTransaction();
					ft.replace(R.id.container, f, TAG_TAB1);
					ft.commit();
				} else {
					Toast.makeText(MainActivity.this, "tab1",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		btn = (Button) findViewById(R.id.btn_tab2);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment oldf = getSupportFragmentManager().findFragmentByTag(
						TAG_TAB2);
				if (oldf == null) {
					TabTwoFragment f = new TabTwoFragment();
					FragmentTransaction ft = getSupportFragmentManager()
							.beginTransaction();
					ft.replace(R.id.container, f, TAG_TAB2);
					ft.commit();
				} else {
					Toast.makeText(MainActivity.this, "tab2",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		btn = (Button) findViewById(R.id.btn_other);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						OtherActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		TabOneFragment f = new TabOneFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.container, f, TAG_TAB1);
		ft.commit();
	}
	
	@Override
	public void setMessage(String message) {
		messageView.setText(message);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Toast.makeText(this, "activity onActivityResult", Toast.LENGTH_SHORT).show();
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
