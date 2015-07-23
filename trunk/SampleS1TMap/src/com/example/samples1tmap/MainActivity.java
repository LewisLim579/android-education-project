package com.example.samples1tmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;


public class MainActivity extends ActionBarActivity {

	TMapView mapView;
	LocationManager mLM;
	String mProvider;
	int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (TMapView)findViewById(R.id.mapView);
        mLM = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mProvider = LocationManager.GPS_PROVIDER;
        Button btn = (Button)findViewById(R.id.btn_add_marker);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TMapMarkerItem item = new TMapMarkerItem();
				TMapPoint point = mapView.getCenterPoint();
				item.setTMapPoint(point);
				Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(android.R.drawable.ic_dialog_info)).getBitmap();
				
				item.setIcon(bitmap);
				item.setPosition(0.5f, 1f);
				item.setCalloutTitle("marker test");
				item.setCalloutSubTitle("sub title");
				
				Bitmap left = ((BitmapDrawable)getResources().getDrawable(android.R.drawable.ic_input_get)).getBitmap();
				item.setCalloutLeftImage(left);
				
				Bitmap right = ((BitmapDrawable)getResources().getDrawable(android.R.drawable.ic_dialog_map)).getBitmap();
				item.setCalloutRightButtonImage(right);
				
				item.setCanShowCallout(true);
				
				mapView.addMarkerItem("markerid"+id++, item);
				
				
			}
		});
        new InitTMap().execute();
    }
    
    private void moveMap(double lat, double lng) {
    	mapView.setCenterPoint(lng, lat);
    	mapView.setZoomLevel(17);
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
			mLM.removeUpdates(mListener);
		}
	};
    
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
	
    class InitTMap extends AsyncTask<Void, Void, Boolean> {
    	@Override
    	protected Boolean doInBackground(Void... params) {
    		mapView.setSKPMapApiKey("458a10f5-c07e-34b5-b2bd-4a891e024c2a");
    		return true;
    	}
    	
    	@Override
    	protected void onPostExecute(Boolean result) {
    		super.onPostExecute(result);
    		setUpMap();
    	}
    }

    private void setUpMap() {
    	mapView.setMapType(TMapView.MAPTYPE_STANDARD);
    	mapView.setTrafficInfo(true);
//    	mapView.setTrackingMode(true);
//    	mapView.setCompassMode(true);
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
