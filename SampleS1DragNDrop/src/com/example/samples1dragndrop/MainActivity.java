package com.example.samples1dragndrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	ListView listView;
	ArrayAdapter<String> mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView1);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
        
        TextView tv = (TextView)findViewById(R.id.text_item_1);
        tv.setOnLongClickListener(mLongClickListener);
        tv = (TextView)findViewById(R.id.text_item_2);
        tv.setOnLongClickListener(mLongClickListener);
        tv = (TextView)findViewById(R.id.text_item_3);
        tv.setOnLongClickListener(mLongClickListener);
        
        listView.setOnDragListener(new View.OnDragListener() {
			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				switch(event.getAction()) {
				case DragEvent.ACTION_DRAG_STARTED :
					return true;
				case DragEvent.ACTION_DRAG_ENTERED :
				case DragEvent.ACTION_DRAG_EXITED :
				case DragEvent.ACTION_DRAG_LOCATION :
					break;
				case DragEvent.ACTION_DROP :
					float x = event.getX(), y = event.getY();
					int position = listView.pointToPosition((int)x, (int)y);
					String data = event.getClipData().getItemAt(0).getText().toString();
					if (position != ListView.INVALID_POSITION) {
						mAdapter.insert(data, position);
					} else {
						mAdapter.add(data);
					}
					return true;
				case DragEvent.ACTION_DRAG_ENDED :
					break;
				}
				return false;
			}
		});
    }

    OnLongClickListener mLongClickListener = new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			String tag = v.getTag().toString();
			ClipData.Item item = new ClipData.Item(tag);
			ClipData data = new ClipData(tag, new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
			v.startDrag(data, new DragShadowBuilder(v), v, 0);
			return true;
		}
	};
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
