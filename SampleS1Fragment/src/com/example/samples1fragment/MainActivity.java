package com.example.samples1fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	int count = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn_tab1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TabOneFragment f = new TabOneFragment();
				Bundle b = new Bundle();
				count++;
				b.putInt("count", count);
				f.setArguments(b);
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.container, f);
				ft.commit();
			}
		});
        
        btn = (Button)findViewById(R.id.btn_tab2);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TabTwoFragment f = new TabTwoFragment();
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.container, f);
				ft.commit();
			}
		});
        
        btn = (Button)findViewById(R.id.btn_other);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, OtherActivity.class);
				startActivity(intent);
			}
		});
        
		TabOneFragment f = new TabOneFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.container, f);
		ft.commit();
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
