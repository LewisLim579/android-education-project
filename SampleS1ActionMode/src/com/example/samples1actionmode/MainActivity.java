package com.example.samples1actionmode;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn_action_mode);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startSupportActionMode(new ActionMode.Callback() {

					@Override
					public boolean onCreateActionMode(ActionMode mode, Menu menu) {
						getMenuInflater().inflate(R.menu.action_mode_menu, menu);
						return true;
					}

					@Override
					public boolean onPrepareActionMode(ActionMode mode,
							Menu menu) {
						return false;
					}

					@Override
					public boolean onActionItemClicked(ActionMode mode,
							MenuItem item) {
						if (item.getItemId() == R.id.menu_1) {
							Toast.makeText(MainActivity.this, "menu 1 click", Toast.LENGTH_SHORT).show();
							mode.finish();
							return true;
						}
						return false;
					}

					@Override
					public void onDestroyActionMode(ActionMode mode) {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
		});
        
        
        btn = (Button)findViewById(R.id.btn_popup);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PopupMenu popup = new PopupMenu(MainActivity.this, v);
				popup.inflate(R.menu.popup_menu);
				popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						if (item.getItemId() == R.id.menu_p1) {
							Toast.makeText(MainActivity.this, "popup item 1 click", Toast.LENGTH_SHORT).show();
							return true;
						}
						return false;
					}
				});
				popup.show();
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
