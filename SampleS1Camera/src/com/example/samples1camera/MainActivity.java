package com.example.samples1camera;

import java.io.IOException;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity implements SurfaceHolder.Callback{

	Camera mCamera;
	int direction = Camera.CameraInfo.CAMERA_FACING_FRONT;
	SurfaceHolder mHolder;
	LinearLayout imageListView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageListView = (LinearLayout)findViewById(R.id.image_list);
        SurfaceView preview = (SurfaceView)findViewById(R.id.surfaceView1);
        preview.getHolder().addCallback(this);
        preview.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        openCamera();
        
        Button btn = (Button)findViewById(R.id.btn_change);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopPreview();
				openCamera();
				startPreview();
			}
		});
        
        btn = (Button)findViewById(R.id.btn_effect);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Camera.Parameters params = mCamera.getParameters();
				List<String> effectlist = params.getSupportedColorEffects();
				final String[] array = effectlist.toArray(new String[effectlist.size()]);
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Select ColorEffect");
				builder.setItems(array, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String effect = array[which];
						Camera.Parameters params = mCamera.getParameters();
						params.setColorEffect(effect);
						mCamera.setParameters(params);
					}
				});
				
				builder.create().show();
			}
		});
        
        btn = (Button)findViewById(R.id.btn_capture);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCamera.takePicture(shutter, null, jpeg);
			}
		});
    }
    Handler mHandler = new Handler();
    
    Camera.ShutterCallback shutter = new Camera.ShutterCallback() {
		
		@Override
		public void onShutter() {
			mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					startPreview();
				}
			}, 1000);
		}
	};
	
	Camera.PictureCallback jpeg = new Camera.PictureCallback() {
		
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = 4;
			Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
			ImageView iv = new ImageView(MainActivity.this);
			iv.setImageBitmap(bm);
			imageListView.addView(iv);
		}
	};
    
    private void openCamera() {
    	direction = (direction == CameraInfo.CAMERA_FACING_BACK) ? CameraInfo.CAMERA_FACING_FRONT : CameraInfo.CAMERA_FACING_BACK;
    	if (mCamera != null) {
    		mCamera.release();
    	}
    	mCamera = Camera.open(direction);
    	mCamera.setDisplayOrientation(90);
    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if (mCamera != null) {
    		mCamera.release();
    		mCamera = null;
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    private void startPreview() {
    	if (mCamera != null && mHolder != null) {
    		try {
				mCamera.setPreviewDisplay(mHolder);
				mCamera.startPreview();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    }
    
    private void stopPreview() {
    	if (mCamera != null) {
    		mCamera.stopPreview();
    	}
    }
    
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mHolder = holder;
		startPreview();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		stopPreview();
		mHolder = holder;
		startPreview();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mHolder = null;
		stopPreview();
	}
}
