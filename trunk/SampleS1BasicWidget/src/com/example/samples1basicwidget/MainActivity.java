package com.example.samples1basicwidget;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	TextView messageView;
	CheckBox itemView;
	RadioGroup group;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageView = (TextView)findViewById(R.id.text_message);
        itemView = (CheckBox)findViewById(R.id.check_item);
        group = (RadioGroup)findViewById(R.id.radioGroup1);
        
        itemView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!isForceChanged) {
			    	if (isChecked) {
			        	Toast.makeText(MainActivity.this, "Item checked changed", Toast.LENGTH_SHORT).show();        
			    	} else {
			        	Toast.makeText(MainActivity.this, "Item unchecked changed", Toast.LENGTH_SHORT).show();
			    	}
				}
			}
		});
        
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
			}
		});
        
        Button btn = (Button)findViewById(R.id.btn_set_text);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Resources res = getResources();
				String message = res.getString(R.string.hi_world);
				messageView.setText(Html.fromHtml(message));
				
			}
		});
    }
    
    boolean isForceChanged = false;
    
    public void onButtonClick(View v) {
    	boolean isChecked = itemView.isChecked();
    	if (isChecked) {
        	Toast.makeText(this, "Item checked", Toast.LENGTH_SHORT).show();        
    	} else {
        	Toast.makeText(this, "Item unchecked", Toast.LENGTH_SHORT).show();
    	}
    	isForceChanged = true;
    	itemView.setChecked(!isChecked);
    	isForceChanged = false;
    }
    
    public void onRadioClick(View v) {
    	int id = group.getCheckedRadioButtonId();
    	switch(id) {
    	case R.id.radio_item1 :
    		Toast.makeText(this, "item 1 select", Toast.LENGTH_SHORT).show();
    		break;
    	case R.id.radio_item2 :
    		Toast.makeText(this, "item 2 select", Toast.LENGTH_SHORT).show();
    		break;
    	case R.id.radio_item3 :
    		Toast.makeText(this, "item 3 select", Toast.LENGTH_SHORT).show();
    		break;    		
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
