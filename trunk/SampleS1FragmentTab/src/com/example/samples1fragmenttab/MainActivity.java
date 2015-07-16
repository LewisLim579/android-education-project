package com.example.samples1fragmenttab;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	FragmentTabHost tabHost;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("ONE"), TabOneFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("TWO"), TabTwoFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("THREE"), TabThreeFragment.class, null);
        
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals("tab1")) {
					Toast.makeText(MainActivity.this, "tab1 clicked", Toast.LENGTH_SHORT).show();
				}
			}
		});
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
