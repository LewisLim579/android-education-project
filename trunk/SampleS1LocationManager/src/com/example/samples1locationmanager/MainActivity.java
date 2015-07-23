package com.example.samples1locationmanager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	LocationManager mLM;
	String mProvider;
	TextView messageView;
	EditText inputView;
	ListView listView;
	ArrayAdapter<Address> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		messageView = (TextView) findViewById(R.id.text_message);
		mLM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setPowerRequirement(Criteria.POWER_HIGH);
		criteria.setCostAllowed(true);
		mProvider = mLM.getBestProvider(criteria, false);
		listView = (ListView)findViewById(R.id.listView1);
		mAdapter = new ArrayAdapter<Address>(this, android.R.layout.simple_list_item_1);
		listView.setAdapter(mAdapter);
		inputView = (EditText)findViewById(R.id.edit_input);
		
		Button btn = (Button)findViewById(R.id.btn_search);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (Geocoder.isPresent()) {
					String keyword = inputView.getText().toString();
					if (!TextUtils.isEmpty(keyword)) {
						Geocoder coder = new Geocoder(MainActivity.this, Locale.getDefault());
						try {
							List<Address> list = coder.getFromLocationName(keyword, 10);
							mAdapter.clear();
							for (Address addr : list) {
								mAdapter.add(addr);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		
	}

	LocationListener mListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			switch (status) {
			case LocationProvider.AVAILABLE:
				break;
			case LocationProvider.OUT_OF_SERVICE:
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				break;
			}
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLocationChanged(Location location) {
			String message = "location : " + location.getLatitude() + ","
					+ location.getLongitude();
			messageView.setText(message);
			
			if (Geocoder.isPresent()) {
				Geocoder coder = new Geocoder(MainActivity.this, Locale.KOREAN);
				try {
					List<Address> list = coder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
					mAdapter.clear();
					for (Address addr : list) {
						mAdapter.add(addr);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			mLM.removeUpdates(this);			
		}
	};

	boolean isFirst = true;

	@Override
	protected void onStart() {
		super.onStart();
		if (mProvider == null) {
			return;
		}
		if (!mLM.isProviderEnabled(mProvider)) {
			if (isFirst) {
				Intent intent = new Intent(
						Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(intent);
				isFirst = false;
			} else {
				Toast.makeText(this, "plz... location setting...", Toast.LENGTH_SHORT).show();
				finish();
			}
		}

		Location location = mLM.getLastKnownLocation(mProvider);
		if (location != null) {
			mListener.onLocationChanged(location);
		}
		mLM.requestLocationUpdates(mProvider, 0, 0, mListener);
	}

	@Override
	protected void onStop() {
		super.onStop();
		mLM.removeUpdates(mListener);
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
