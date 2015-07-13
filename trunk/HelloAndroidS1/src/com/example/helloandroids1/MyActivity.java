package com.example.helloandroids1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends ActionBarActivity {

	TextView messageView;
	EditText inputView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageView = (TextView)findViewById(R.id.text_message);
        inputView = (EditText)findViewById(R.id.edit_input);
        Button btn = (Button)findViewById(R.id.btn_send);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				messageView.setText(inputView.getText().toString());
			}
		});
        
        
        btn = (Button)findViewById(R.id.btn_toast);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MyActivity.this, "toast dialog...", Toast.LENGTH_SHORT).show();
			}
		});
        
        btn = (Button)findViewById(R.id.btn_google);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
				startActivity(i);
			}
		});
        
        btn = (Button)findViewById(R.id.btn_dial);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-2257-3585"));
				startActivity(i);
			}
		});
        
        btn = (Button)findViewById(R.id.btn_photo);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setType("image/jpeg");
				startActivity(i);
			}
		});
        
        btn = (Button)findViewById(R.id.btn_other);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MyActivity.this, OtherActivity.class);
				startActivity(i);
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
