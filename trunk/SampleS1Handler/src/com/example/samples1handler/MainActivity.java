package com.example.samples1handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	ProgressBar progressView;
	TextView messageView;
	private static final int MESSAGE_PROGRESS_START = 1;
	private static final int MESSAGE_PROGRESS_UPDATE = 2;
	private static final int MESSAGE_PROGRESS_END = 3;
	
	Handler mHandler = new Handler(Looper.getMainLooper()) {
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case MESSAGE_PROGRESS_START :
				messageView.setText("progress start...");
				progressView.setMax(100);
				break;
			case MESSAGE_PROGRESS_UPDATE :
				int progress = msg.arg1;
				messageView.setText("progress : "+progress);
				progressView.setProgress(progress);
				break;
			case MESSAGE_PROGRESS_END :
				messageView.setText("progress done");
				break;
			}
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressView = (ProgressBar)findViewById(R.id.progressBar1);
        messageView = (TextView)findViewById(R.id.text_message);
        Button btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						int count = 0;
//						Message msg;
//						msg = mHandler.obtainMessage(MESSAGE_PROGRESS_START);
//						mHandler.sendMessage(msg);
						
						mHandler.post(new StartRunnable());
						for (int i = 0; i < 21; i++) {
							// update ui
							int progress = i * 5;
							
							mHandler.post(new UpdateRunnable(progress));
							
//							msg = mHandler.obtainMessage(MESSAGE_PROGRESS_UPDATE, progress, 0);
//							mHandler.sendMessage(msg);
							
//							messageView.setText("progress : " + progress);
//							progressView.setProgress(progress);
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						// update ui
//						messageView.setText("progress done");
//						msg = mHandler.obtainMessage(MESSAGE_PROGRESS_END);
//						mHandler.sendMessage(msg);
						mHandler.post(new EndRunnable());
					}
				}).start();
			}
		});
    }
    
    
    
    class StartRunnable implements Runnable {
    	@Override
    	public void run() {
			messageView.setText("progress start...");
			progressView.setMax(100);
    	}
    }

    class UpdateRunnable implements Runnable {
    	int progress;
    	public UpdateRunnable(int progress) {
    		this.progress = progress;
    	}
    	
    	@Override
    	public void run() {
			messageView.setText("progress : "+progress);
			progressView.setProgress(progress);
    		
    	}
    }
    
    class EndRunnable implements Runnable {
    	@Override
    	public void run() {
			messageView.setText("progress done");
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
