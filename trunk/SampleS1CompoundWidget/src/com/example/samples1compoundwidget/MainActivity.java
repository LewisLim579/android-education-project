package com.example.samples1compoundwidget;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.samples1compoundwidget.ItemView.OnItemImageClickListener;


public class MainActivity extends ActionBarActivity {

	ItemView itemView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        itemView = (ItemView)findViewById(R.id.itemView1);
        ItemData data = new ItemData();
        data.iconResId = R.drawable.ic_launcher;
        data.title = "My CompoundWidget";
        data.desc = "CompoundWidget is ...";
        itemView.setItemData(data);
        itemView.setOnItemImageClickListener(new OnItemImageClickListener() {
			
			@Override
			public void onImageClick(ItemView view) {
				String title = (view.getItemData() == null) ? "null" : view.getItemData().title;
				Toast.makeText(MainActivity.this, "item clicked : " + title, Toast.LENGTH_SHORT).show();
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
