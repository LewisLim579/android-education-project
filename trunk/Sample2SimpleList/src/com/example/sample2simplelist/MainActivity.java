package com.example.sample2simplelist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView listView;
	EditText keywordView;
	ArrayList<String> mData = new ArrayList<String>();
	MyAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		makeData();
		keywordView = (EditText) findViewById(R.id.itemText);
		listView = (ListView) findViewById(R.id.listView1);
		// listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//		mAdapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_multiple_choice, mData);
		mAdapter = new MyAdapter(this,mData);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view,
					int position, long id) {
				String text = (String) listView.getItemAtPosition(position);
				Toast.makeText(MainActivity.this, "selected Item : " + text,
						Toast.LENGTH_SHORT).show();

			}
		});

		Button btn = (Button) findViewById(R.id.addItem);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String text = keywordView.getText().toString();
//				mAdapter.add(text);
			}
		});

		btn = (Button) findViewById(R.id.showSelect);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// int pos = listView.getCheckedItemPosition();
				// String text = (String)listView.getItemAtPosition(pos);
				String text = "";
				SparseBooleanArray array = listView.getCheckedItemPositions();
				for (int i = 0; i < array.size(); i++) {
					if (array.get(i)) {
						text += (String)listView.getItemAtPosition(i) + ",";
					}
				}
				
			}
		});
	}

	private void makeData() {
		String[] array = getResources().getStringArray(R.array.items);
		for (int i = 0; i < array.length; i++) {
			mData.add(array[i]);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
