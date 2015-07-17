package com.example.samples1actionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

	ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setTitle("ActionBar Test...");
//        actionBar.setIcon(R.drawable.ic_launcher);
//        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        View v = getLayoutInflater().inflate(R.layout.action_layout, null);
        actionBar.setCustomView(v, new ActionBar.LayoutParams(Gravity.CENTER));
//        actionBar.setCustomView(R.layout.action_layout);
//        View v = actionBar.getCustomView();
//        TextView tv = (TextView)v.findViewById(R.id.text_title);
//        tv.setText("Custom Title...");
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
        } else if (id == android.R.id.home) {
        	Toast.makeText(this, "homeasup click", Toast.LENGTH_SHORT).show();
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
