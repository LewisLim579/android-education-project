package com.example.samples1network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	TextView messageView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageView = (TextView)findViewById(R.id.text_message);
        Button btn = (Button)findViewById(R.id.btn_get_google);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new MyGoogleTask().execute("http://www.google.com");
			}
		});
    }
    
    class MyGoogleTask extends AsyncTask<String, Integer, String> {
    	@Override
    	protected String doInBackground(String... params) {
    		String urlString = params[0];
    		try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
//				conn.addRequestProperty(field, newValue);
				int code = conn.getResponseCode();
				if (code == HttpURLConnection.HTTP_OK) {
					InputStream is = conn.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					StringBuilder sb = new StringBuilder();
					String line;
					while((line=br.readLine())!= null) {
						sb.append(line);
						sb.append("\n\r");
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
    			messageView.setText(result);
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
