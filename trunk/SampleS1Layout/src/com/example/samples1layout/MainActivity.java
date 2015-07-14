package com.example.samples1layout;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	View content1,content2,content3;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);
        content1 = findViewById(R.id.layout_content1);
        content2 = findViewById(R.id.layout_content2);
        content3 = findViewById(R.id.layout_content3);
        Button btn = (Button)findViewById(R.id.btn_tab1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				content1.setVisibility(View.VISIBLE);
				content2.setVisibility(View.GONE);
				content3.setVisibility(View.GONE);
			}
		});
        
        btn = (Button)findViewById(R.id.btn_tab3);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				content3.setVisibility(View.VISIBLE);
				content1.setVisibility(View.GONE);
				content2.setVisibility(View.GONE);
			}
		});

        btn = (Button)findViewById(R.id.btn_tab2);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				content2.setVisibility(View.VISIBLE);
				content1.setVisibility(View.GONE);
				content3.setVisibility(View.GONE);
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
