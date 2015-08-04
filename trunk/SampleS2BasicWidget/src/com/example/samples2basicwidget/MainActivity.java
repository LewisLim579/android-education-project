package com.example.samples2basicwidget;

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
	CheckBox alarmCheckView;
	RadioGroup group;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageView = (TextView)findViewById(R.id.textView1);
        alarmCheckView = (CheckBox)findViewById(R.id.checkBox1);
        group = (RadioGroup)findViewById(R.id.radioGroup1);
        
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String message = getResources().getString(R.string.text_message);
				messageView.setText(Html.fromHtml(message));
			}
		});
        
        alarmCheckView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isForced) return;
				Toast.makeText(MainActivity.this, "alarm changed : " + isChecked, Toast.LENGTH_SHORT).show();
			}
		});
        
        group.check(R.id.radio_f);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
		    	switch(checkedId) {
		    	case R.id.radio_m :
		    		Toast.makeText(MainActivity.this, "select m", Toast.LENGTH_SHORT).show();
		    		break;
		    	case R.id.radio_f :
		    		Toast.makeText(MainActivity.this, "select f", Toast.LENGTH_SHORT).show();
		    		break;
		    	}
			}
		});
    }
    
    boolean isForced = false;
    
    public void onButtonClick(View v) {
//    	Toast.makeText(this, "onButtonClick", Toast.LENGTH_SHORT).show();
    	if (alarmCheckView.isChecked()) {
    		Toast.makeText(this, "alarm true", Toast.LENGTH_SHORT).show();
    		isForced = true;
    		alarmCheckView.setChecked(false);
    		isForced = false;
    	} else {
    		Toast.makeText(this, "alarm false", Toast.LENGTH_SHORT).show();
    		isForced = true;
    		alarmCheckView.setChecked(true);
    		isForced = false;
    	}
    }
    
    public void onButtonRadio(View v) {
    	int id = group.getCheckedRadioButtonId();
    	switch(id) {
    	case R.id.radio_m :
    		Toast.makeText(this, "select m", Toast.LENGTH_SHORT).show();
    		break;
    	case R.id.radio_f :
    		Toast.makeText(this, "select f", Toast.LENGTH_SHORT).show();
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
