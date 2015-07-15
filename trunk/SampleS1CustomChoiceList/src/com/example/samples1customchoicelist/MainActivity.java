package com.example.samples1customchoicelist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	ListView listView;
	MyAdapter mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView1);
        mAdapter = new MyAdapter();
        listView.setAdapter(mAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        Button btn = (Button)findViewById(R.id.btn_select_list);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SparseBooleanArray array = listView.getCheckedItemPositions();
				StringBuilder sb = new StringBuilder();
				for (int index = 0; index < array.size(); index++) {
					int position = array.keyAt(index);
					if (array.get(position)) {
						ItemData data = (ItemData)listView.getItemAtPosition(position);
						sb.append(data.title).append(",");
					}
				}
				Toast.makeText(MainActivity.this, "checked items : " + sb.toString(), Toast.LENGTH_SHORT).show();
			}
		});
        initData();
    }
    
    private void initData() {
    	for (int i = 0; i < 20; i++) {
    		ItemData d = new ItemData();
    		d.iconId = R.drawable.ic_launcher;
    		d.title = "title " + i;
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
