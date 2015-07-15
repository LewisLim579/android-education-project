package com.example.samples1multitypelist;

import java.util.Date;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;


public class MainActivity extends ActionBarActivity {

	EditText inputView;
	RadioGroup group;
	ListView listView;
	ChattingAdapter mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputView = (EditText)findViewById(R.id.edit_input);
        group = (RadioGroup)findViewById(R.id.radioGroup1);
        listView = (ListView)findViewById(R.id.listView1);
        mAdapter = new ChattingAdapter();
        listView.setAdapter(mAdapter);
        Button btn = (Button)findViewById(R.id.btn_go);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String message = inputView.getText().toString();
				if (message != null && !message.equals("")) {
					switch(group.getCheckedRadioButtonId()) {
					case R.id.radio_send :
						RightData rdata = new RightData();
						rdata.message = message;
						mAdapter.add(rdata);
						break;
					case R.id.radio_receive :
						LeftData ldata = new LeftData();
						ldata.iconId = R.drawable.ic_launcher;
						ldata.message = message;
						mAdapter.add(ldata);
						break;
					case R.id.radio_date :
						DateData ddata = new DateData();
						ddata.date = new Date().toString();
						mAdapter.add(ddata);
						break;
					}
					inputView.setText("");
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
