package com.example.samples1expandablelist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupCollapseListener;


public class MainActivity extends ActionBarActivity {

	ExpandableListView listView;
	MyAdapter mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ExpandableListView)findViewById(R.id.expandableListView1);
        mAdapter = new MyAdapter();
        listView.setAdapter(mAdapter);
        listView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			
			@Override
			public void onGroupCollapse(int groupPosition) {
				listView.expandGroup(groupPosition);
			}
		});
        
        initData();
        
        for (int i = 0; i < mAdapter.getGroupCount(); i++) {
        	listView.expandGroup(i);
        }
    }
    
    private void initData() {
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 5; j++) {
    			mAdapter.add("group"+i, "child"+j);
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
