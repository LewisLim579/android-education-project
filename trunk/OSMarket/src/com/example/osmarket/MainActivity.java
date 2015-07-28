package com.example.osmarket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MainActivity extends ActionBarActivity {

	ListView listView;
	ArrayAdapter<Product> mAdapter;
	EditText keywordView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView1);
        mAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
        keywordView = (EditText)findViewById(R.id.edit_keyword);
        Button btn = (Button)findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String keyword = keywordView.getText().toString();
				if (!TextUtils.isEmpty(keyword)) {
					new MarketTask().execute(keyword);
				}
			}
		});
    }
    
    class MarketTask extends AsyncTask<String, Integer, MarketResult> {
    	@Override
    	protected MarketResult doInBackground(String... params) {
    		String keyword = params[0];
    		try {
				String urlText = String.format("http://apis.skplanetx.com/11st/common/products?count=&page=&searchKeyword=%s&sortCode=&option=&version=1", URLEncoder.encode(keyword, "utf-8"));
				URL url = new URL(urlText);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.addRequestProperty("Accept", "application/json");
				conn.addRequestProperty("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a");
				int code = conn.getResponseCode();
				if (code == HttpURLConnection.HTTP_OK) {
					InputStreamReader isr = new InputStreamReader(conn.getInputStream());
					Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
					MarketResult mr = gson.fromJson(isr, MarketResult.class);
					return mr;
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
    	protected void onPostExecute(MarketResult result) {
    		super.onPostExecute(result);
    		if (result != null) {
    			for (Product p : result.productSearchResponse.products.product) {
    				mAdapter.add(p);
    			}
    		} else {
    			Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
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
