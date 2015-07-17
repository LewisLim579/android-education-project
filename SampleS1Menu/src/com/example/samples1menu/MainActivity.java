package com.example.samples1menu;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	TextView nameView;
	EditText inputView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameView = (TextView)findViewById(R.id.text_name);
        inputView = (EditText)findViewById(R.id.edit_input);
        
        registerForContextMenu(nameView);
        registerForContextMenu(inputView);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	switch(v.getId()) {
    	case R.id.text_name :
        	getMenuInflater().inflate(R.menu.text_menu, menu);
        	return;
    	case R.id.edit_input :
        	getMenuInflater().inflate(R.menu.edit_menu, menu);
        	return;    		
    	}
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.menu_text_item1 :
    		Toast.makeText(this, "text 1 menu", Toast.LENGTH_SHORT).show();
    		return true;
    	case R.id.menu_text_item2 :
    		Toast.makeText(this, "text 2 menu", Toast.LENGTH_SHORT).show();
    		return true;
     	}
    	return false;
    }

    EditText keywordView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.menu_item1);
        View v = MenuItemCompat.getActionView(item);
        if (v != null) {
        	keywordView = (EditText)v.findViewById(R.id.edit_keyword);
        	Button btn = (Button)v.findViewById(R.id.btn_search);
        	btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String text = keywordView.getText().toString();
					Toast.makeText(MainActivity.this, "keyword : " + text, Toast.LENGTH_SHORT).show();
				}
			});
        }
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
        } else if (id == R.id.menu_item1) {
        	Toast.makeText(this, "Menu Item1 click", Toast.LENGTH_SHORT).show();
        	return true;
        } else if (id == R.id.menu_sub_1) {
        	Toast.makeText(this, "Menu sub 1 click", Toast.LENGTH_SHORT).show();
        	return true;
        } else if (id == R.id.menu_sub_2) {
        	Toast.makeText(this, "Menu sub 2 click", Toast.LENGTH_SHORT).show();
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
