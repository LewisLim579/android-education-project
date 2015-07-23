package com.example.samples1googlemap;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity implements
		GoogleMap.OnMarkerClickListener,
		GoogleMap.OnMarkerDragListener,
		GoogleMap.OnInfoWindowClickListener {

	GoogleMap mMap;
	LocationManager mLM;
	String mProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpMapIfNeeded();
		mLM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mProvider = LocationManager.GPS_PROVIDER;
		Button btn = (Button) findViewById(R.id.btn_marker);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				CameraPosition position = mMap.getCameraPosition();
				LatLng target = position.target;
				MarkerOptions options = new MarkerOptions();
				options.position(target);
				options.title("My Marker");
				options.snippet("snippet");
				options.icon(BitmapDescriptorFactory.defaultMarker());
				options.anchor(0.5f, 1);
				options.draggable(true);
				Marker m = mMap.addMarker(options);
			}
		});
	}

	LocationListener mListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onProviderDisabled(String provider) {

		}

		@Override
		public void onLocationChanged(Location location) {
			moveMap(location.getLatitude(), location.getLongitude());
			mLM.removeUpdates(this);
		}
	};

	private void moveMap(double lat, double lng) {
		CameraPosition position = new CameraPosition.Builder()
				.target(new LatLng(lat, lng))
				// .bearing(30)
				// .tilt(30)
				.zoom(16).build();
		CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
		mMap.moveCamera(update);
	}

	@Override
	protected void onStart() {
		super.onStart();
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
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		if (mMap == null) {
			SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.fragment1);
			mMap = smf.getMap();
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	private void setUpMap() {
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.setOnMarkerClickListener(this);
		mMap.setOnMarkerDragListener(this);
		mMap.setOnInfoWindowClickListener(this);
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

	@Override
	public boolean onMarkerClick(Marker marker) {
		Toast.makeText(this, "marker click : " + marker.getTitle(), Toast.LENGTH_SHORT).show();
		marker.showInfoWindow();
		return true;
	}

	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
		LatLng target = marker.getPosition();
		Toast.makeText(this, "drag end...." + target.latitude + "," + target.longitude, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		Toast.makeText(this, "info window click" + arg0.getTitle(), Toast.LENGTH_SHORT).show();
		arg0.hideInfoWindow();
	}
}
