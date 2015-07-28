package com.example.osmelon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	ListView listView;
	ArrayAdapter<Song> mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView1);
        mAdapter = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
        
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new MyMelonTask().execute();
			}
		});
    }

    private static final String URL = "http://apis.skplanetx.com/melon/charts/realtime";
    private static final String PARAM = "?count=%s&page=%s&version=1";
    
    class MyMelonTask extends AsyncTask<String, Integer, String> {
    	@Override
    	protected String doInBackground(String... params) {
    		String urlString = URL + String.format(PARAM, "10", "1");
    		try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.addRequestProperty("Accept", "application/json");
				conn.addRequestProperty("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a");
				int code = conn.getResponseCode();
				if (code == HttpURLConnection.HTTP_OK) {
					StringBuilder sb = new StringBuilder();
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					while((line = br.readLine()) != null) {
						sb.append(line).append("\n\r");
					}
					return sb.toString();
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
    	
    	@Override
    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    		if (result != null) {
    			Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
    			Gson gson = new Gson();
    			MelonResult mr = gson.fromJson(result, MelonResult.class);
    			for (Song s : mr.melon.songs.songlist) {
    				mAdapter.add(s);
    			}
//				try {
//					JSONObject jobject = new JSONObject(result);
//	    			JSONObject melonObject = jobject.optJSONObject("melon");
//	    			MelonResult mr = new MelonResult();
//	    			if (melonObject != null) {
//	    				mr.parseJSON(melonObject);
////	    				mr.melon = new Melon();
////	    				mr.melon.menuId = melonObject.optInt("menuId", 0);
////	    				// ...
////	    				// ...
//	    			}
//	    			// ....
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
    		} else {
    			Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
    		}
    	}
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
