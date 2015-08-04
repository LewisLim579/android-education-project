package com.example.samples2customlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	ListView listView;
	MyAdapter mAdapter;
	int[] resIds = { R.drawable.gallery_photo_1,
			R.drawable.gallery_photo_2,
			R.drawable.gallery_photo_3,
			R.drawable.gallery_photo_4,
			R.drawable.gallery_photo_5,
			R.drawable.gallery_photo_6,
			R.drawable.gallery_photo_7,
			R.drawable.gallery_photo_8
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView1);
        mAdapter = new MyAdapter();
        mAdapter.setOnCommentClickListener(new ItemView.OnCommentClickListener() {
			
			@Override
			public void onCommentClick(ItemView view, ItemData data) {
				Intent intent = new Intent(MainActivity.this, CommentActivity.class);
				startActivity(intent);
			}
		});
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ItemData data = (ItemData)listView.getItemAtPosition(position);
				Toast.makeText(MainActivity.this, "title : " + data.title, Toast.LENGTH_SHORT).show();
			}
		});
        initData();
    }
    
    private void initData() {
    	for (int i = 0; i < 20 ; i++) {
    		ItemData d = new ItemData();
    		d.iconId = resIds[i % resIds.length];
    		d.title = "title : " + i;
    		d.desc = "desc : " + i;
    		d.commentCount = i;
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
