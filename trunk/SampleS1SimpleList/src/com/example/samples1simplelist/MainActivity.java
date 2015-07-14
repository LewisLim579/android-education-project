package com.example.samples1simplelist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	ListView listView;
	EditText keywordView;
	ArrayAdapter<String> mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keywordView = (EditText)findViewById(R.id.edit_item);
        listView = (ListView)findViewById(R.id.listView1);
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String text = (String)listView.getItemAtPosition(position);
//				Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
			}
		});
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        
        Button btn = (Button)findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String keyword = keywordView.getText().toString();
				if (keyword != null && !keyword.equals("")) {
					mAdapter.add(keyword);
					listView.smoothScrollToPosition(mAdapter.getCount()-1);
				}
			}
		});
        
        
        btn = (Button)findViewById(R.id.btn_selected);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (listView.getChoiceMode() == ListView.CHOICE_MODE_SINGLE) {
					int position = listView.getCheckedItemPosition();
					String text = (String)listView.getItemAtPosition(position);
					Toast.makeText(MainActivity.this, "single choice : "+text, Toast.LENGTH_SHORT).show();
				} else if (listView.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
					SparseBooleanArray array = listView.getCheckedItemPositions();
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < array.size(); i++) {
						int position = array.keyAt(i);
						if (array.get(position)) {
							String text = (String)listView.getItemAtPosition(position);
							sb.append(text);
						}
					}
					Toast.makeText(MainActivity.this, "multiple choice : " + sb.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		});
        initData();
    }
    
    private void initData() {
    	String[] array = getResources().getStringArray(R.array.items);
    	for (String s : array) {
    		mAdapter.add(s);
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
