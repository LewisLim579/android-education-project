package com.example.samples1tmap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.skp.Tmap.TMapView;


public class MainActivity extends ActionBarActivity {

	TMapView mapView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (TMapView)findViewById(R.id.mapView);
        new InitTMap().execute();
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
