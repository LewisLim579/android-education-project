package com.example.samples2compoundwidget;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	InfoView infoView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoView = (InfoView)findViewById(R.id.infoView1);
        InfoData data = new InfoData();
        data.iconId = R.drawable.gallery_photo_1;
//        Drawable d = getResources().getDrawable(R.drawable.gallery_photo_1);
        data.title = "compoundWidget";
        data.subtitle = "infoview";
        data.description = "infoview.... infoview....infoview....infoview....";
        infoView.setInfoData(data);
        
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				infoView.setTitleColor(Color.RED);
			}
		});
        
        infoView.setOnImageClickListener(new InfoView.OnImageClickListener() {
			
			@Override
			public void onImageClick(InfoView view, InfoData data) {
				Toast.makeText(MainActivity.this, data.title + " image clicked", Toast.LENGTH_SHORT).show();
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
