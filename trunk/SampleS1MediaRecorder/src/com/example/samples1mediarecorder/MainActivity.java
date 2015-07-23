package com.example.samples1mediarecorder;

import java.io.File;
import java.io.IOException;

import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioEncoder;
import android.media.MediaRecorder.AudioSource;
import android.media.MediaRecorder.OutputFormat;
import android.media.MediaRecorder.VideoEncoder;
import android.media.MediaRecorder.VideoSource;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Video;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements SurfaceHolder.Callback{

	SurfaceHolder mHolder;
	
	MediaRecorder mRecorder;
	boolean isRecorder;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surfaceView1);
        surfaceView.getHolder().addCallback(this);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        Button btn = (Button)findViewById(R.id.btn_record);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				record();
			}
		});
        
        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stop();
			}
		});
    }
    
    File outputFile;
    
    private void record() {
    	if (mRecorder == null) {
    		mRecorder = new MediaRecorder();
    		mRecorder.setAudioSource(AudioSource.MIC);
    		mRecorder.setVideoSource(VideoSource.CAMERA);
    		
    		mRecorder.setOutputFormat(OutputFormat.MPEG_4);
    		
    		mRecorder.setAudioEncoder(AudioEncoder.AMR_NB);
    		mRecorder.setVideoEncoder(VideoEncoder.MPEG_4_SP);
    		
    		mRecorder.setVideoSize(320, 240);

    		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
    		if (!dir.exists()) {
    			dir.mkdirs();
    		}
    		outputFile = new File(dir, "my_video_"+System.currentTimeMillis() + ".mpeg");
    		mRecorder.setOutputFile(outputFile.getAbsolutePath());
    		
    		if (mHolder != null) {
    			mRecorder.setPreviewDisplay(mHolder.getSurface());
    		}
    		
    		try {
				mRecorder.prepare();
				mRecorder.start();
				return;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		mRecorder.release();
    		mRecorder = null;
    	}
    }

    private void stop() {
    	if (mRecorder != null) {
    		mRecorder.stop();
    		mRecorder.release();
    		mRecorder = null;
    		
    		saveToDB();
    	}
    }
    
    private void saveToDB() {
    	
    	ContentValues values = new ContentValues();
    	values.put(Video.Media.TITLE, "test my video");
    	values.put(Video.Media.DISPLAY_NAME, "my video");
    	values.put(Video.Media.DESCRIPTION, "my video test...");
    	values.put(Video.Media.DATA, outputFile.getAbsolutePath());
    	values.put(Video.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
    	values.put(Video.Media.MIME_TYPE, "video/mpeg");
    	values.put(Video.Media.WIDTH, 320);
    	values.put(Video.Media.HEIGHT, 240);
    	
    	Uri uri = getContentResolver().insert(Video.Media.EXTERNAL_CONTENT_URI, values);
    	
    	sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
    	
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

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mHolder = holder;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		mHolder = holder;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mHolder = null;
	}
}
