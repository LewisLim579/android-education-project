package com.example.samples1tabpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	ViewPager pager;
	TabHost tabHost;
	TabsAdapter mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager)findViewById(R.id.pager);
        tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup();
        mAdapter = new TabsAdapter(this, getSupportFragmentManager(), tabHost, pager);
        
        mAdapter.addTab(tabHost.newTabSpec("tab1").setIndicator("TAB1"), Fragment1.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab2").setIndicator("TAB2"), Fragment2.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab3").setIndicator("TAB3"), Fragment3.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab4").setIndicator("TAB4"), Fragment4.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab5").setIndicator("TAB5"), Fragment5.class, null);
        
        mAdapter.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				
			}
		});
        
        mAdapter.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
        
        if (savedInstanceState != null) {
        	mAdapter.onRestoreInstanceState(savedInstanceState);
        	tabHost.setCurrentTabByTag(savedInstanceState.getString("tabId"));
        }
        
        
        
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	mAdapter.onSaveInstanceState(outState);
    	outState.putString("tabId", tabHost.getCurrentTabTag());
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
