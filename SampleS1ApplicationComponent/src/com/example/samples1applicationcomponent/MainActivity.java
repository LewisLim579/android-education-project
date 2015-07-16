package com.example.samples1applicationcomponent;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	EditText inputView;
	TextView resultView;
	private static final int REQUEST_CODE_OTHER = 0;
	int count = 100;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputView = (EditText)findViewById(R.id.edit_input);
        resultView = (TextView)findViewById(R.id.text_result);
        
        Button btn = (Button)findViewById(R.id.btn_other);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String input = inputView.getText().toString();
				Intent intent = new Intent(MainActivity.this, OtherActivity.class);
				intent.putExtra(OtherActivity.EXTRA_KEYWORD, input);
				startActivityForResult(intent, REQUEST_CODE_OTHER);
			}
		});
        
        btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MyService.class);
				intent.putExtra("count", count);
				startService(intent);
				count+=100;
			}
		});
        
        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MyService.class);
				stopService(intent);
			}
		});
        
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (requestCode == REQUEST_CODE_OTHER) {
    		if (resultCode == Activity.RESULT_OK) {
    			String message = data.getStringExtra(OtherActivity.RESULT_PARAM);
    			resultView.setText(message);
    		}
    	}
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	IntentFilter filter = new IntentFilter(MyService.ACTION_MOD_TEN);
    	registerReceiver(mCountReceiver, filter);
    }
    
    BroadcastReceiver mCountReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(MainActivity.this, "Activity Receive Count : " + intent.getIntExtra("count", 0), Toast.LENGTH_SHORT).show();
			setResultCode(Activity.RESULT_OK);
		}
	};
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(mCountReceiver);
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
