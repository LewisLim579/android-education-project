package com.example.samples1googlemap;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
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
	EditText inputView;
	Map<Marker,POIData> mPoiResolver = new HashMap<Marker,POIData>();
	Map<POIData,Marker> mMarkerResolver = new HashMap<POIData, Marker>();
	ListView listView;
	ArrayAdapter<POIData> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inputView = (EditText)findViewById(R.id.edit_input);
		listView = (ListView)findViewById(R.id.listView1);
		mAdapter = new ArrayAdapter<POIData>(this, android.R.layout.simple_list_item_1);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				POIData poi = (POIData)listView.getItemAtPosition(position);
				Marker m = mMarkerResolver.get(poi);
				moveMarkerPosition(m);
//				moveMap(m.getPosition().latitude, m.getPosition().longitude);
//				m.showInfoWindow();
			}
		});
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
				String text = inputView.getText().toString();
				POIData poi = new POIData();
				poi.title = text;
				poi.description = "desc : " + text;
				mAdapter.add(poi);
				options.title(poi.title);
				options.snippet(poi.description);
				options.icon(BitmapDescriptorFactory.defaultMarker());
				options.anchor(0.5f, 1);
				options.draggable(true);
				Marker m = mMap.addMarker(options);
				
				mMarkerResolver.put(poi, m);
				mPoiResolver.put(m, poi);
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

	private void moveMarkerPosition(final Marker m) {
		CameraUpdate update = CameraUpdateFactory.newLatLng(m.getPosition());
		mMap.animateCamera(update, new CancelableCallback() {
			
			@Override
			public void onFinish() {
				m.showInfoWindow();
			}
			
			@Override
			public void onCancel() {
				m.showInfoWindow();
			}
		});
	}
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
	public void onInfoWindowClick(Marker marker) {
		POIData poi = mPoiResolver.get(marker);
		
		Toast.makeText(this, "info window click : " + poi.created, Toast.LENGTH_SHORT).show();
		
		marker.hideInfoWindow();
	}
}
