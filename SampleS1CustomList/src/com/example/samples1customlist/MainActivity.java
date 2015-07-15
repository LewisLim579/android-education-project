package com.example.samples1customlist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.samples1customlist.MyAdapter.OnAdapterItemListener;


public class MainActivity extends ActionBarActivity {

	ListView listView;
	MyAdapter mAdapter;
	int[] imageIds = { 
			R.drawable.sample_0, 			
			R.drawable.sample_1, 			
			R.drawable.sample_2, 			
			R.drawable.sample_3, 			
			R.drawable.sample_4, 			
			R.drawable.sample_5, 			
			R.drawable.sample_6, 			
			R.drawable.sample_7, 			
	};
	ImageView fullView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullView = (ImageView)findViewById(R.id.image_full);
        fullView.setVisibility(View.GONE);
        fullView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				fullView.setVisibility(View.GONE);
			}
		});
        listView = (ListView)findViewById(R.id.listView1);
        mAdapter = new MyAdapter();
        mAdapter.setOnAdapterItemListener(new OnAdapterItemListener() {
			
			@Override
			public void onAdapterItemClick(MyAdapter adapter, ItemView view,
					ItemData data) {
				fullView.setImageResource(data.iconResId);
				fullView.setVisibility(View.VISIBLE);
			}
		});
        listView.setAdapter(mAdapter);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(MainActivity.this, "item click", Toast.LENGTH_SHORT).show();
			}
		});
        
        initData();
    }
    
    private void initData() {
    	for (int i = 0 ; i < 20 ; i++) {
    		ItemData d = new ItemData();
    		d.iconResId = imageIds[i % imageIds.length];
    		d.title = "title " + i;
    		d.desc = "desc " + i;
    		mAdapter.add(d);
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
