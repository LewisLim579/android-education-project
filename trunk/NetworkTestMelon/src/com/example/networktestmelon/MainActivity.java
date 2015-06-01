package com.example.networktestmelon;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.networktestmelon.NetworkManager.OnResultListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

public class MainActivity extends ActionBarActivity {

	AsyncHttpClient client;
	private static final String CHART_URL = "http://apis.skplanetx.com/melon/charts/realtime";
	ListView listView;
	ArrayAdapter<Song> mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView)findViewById(R.id.listView1);
		mAdapter = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1);
		listView.setAdapter(mAdapter);
		
		Button btn = (Button)findViewById(R.id.btn_chart);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NetworkManager.getInstance().getMelonChart(MainActivity.this, 1, 10, new OnResultListener<MelonResult>() {
					
					@Override
					public void onSuccess(MelonResult result) {
						for (Song s : result.melon.songs.song) {
							mAdapter.add(s);
						}
					}
					
					@Override
					public void onFail(int code) {
						Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
					}
				});
//				Header[] headers = new Header[2];
//				headers[0] = new BasicHeader("Accept", "application/json");
//				headers[1] = new BasicHeader("appKey","458a10f5-c07e-34b5-b2bd-4a891e024c2a");
//				
//				RequestParams params = new RequestParams();
//				params.put("version", "1");
//				params.put("page", "1");
//				params.put("count", "10");
//				client.get(MainActivity.this, CHART_URL, headers, params, new TextHttpResponseHandler() {
//					
//					@Override
//					public void onSuccess(int statusCode, Header[] headers,
//							String responseString) {
//						Toast.makeText(MainActivity.this, responseString, Toast.LENGTH_SHORT).show();
//						Gson gson = new Gson();
//						MelonResult result = gson.fromJson(responseString, MelonResult.class);
//						for (Song s : result.melon.songs.song) {
//							mAdapter.add(s);
//						}
//					}
//					
//					@Override
//					public void onFailure(int statusCode, Header[] headers,
//							String responseString, Throwable throwable) {
//						Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
//					}
//				});
			}
		});
		client = new AsyncHttpClient();
		client.setCookieStore(new PersistentCookieStore(this));
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		client.cancelRequests(this, true);
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
