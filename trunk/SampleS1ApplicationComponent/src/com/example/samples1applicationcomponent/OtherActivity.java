package com.example.samples1applicationcomponent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OtherActivity extends ActionBarActivity {

	TextView messageView;
	public static final String EXTRA_KEYWORD = "keyword";
	public static final String RESULT_PARAM = "result";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		messageView = (TextView)findViewById(R.id.text_message);
		Intent intent = getIntent();
		String input = intent.getStringExtra(EXTRA_KEYWORD);
		messageView.setText(input);
		Button btn = (Button)findViewById(R.id.btn_finish);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String message = "echo : " + messageView.getText().toString();
				Intent data = new Intent();
				data.putExtra(RESULT_PARAM, message);
				setResult(RESULT_OK, data);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.other, menu);
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
