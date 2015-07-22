package com.example.samples1mediaplayer;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends ActionBarActivity {

	MediaPlayer mPlayer;
	enum PlayState {
		IDLE,
		INITIALIZED,
		PREPARED,
		STARTED,
		PAUSED,
		STOPPED,
		ERROR,
		END
	}
	PlayState mState;
	
	SeekBar progressView;
	SeekBar volumeView;
	AudioManager mAudioManager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayer = new MediaPlayer();
        mState = PlayState.IDLE;
        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.winter_blues);
        try {
			mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			mState = PlayState.INITIALIZED;
			mPlayer.prepare();
			mState = PlayState.PREPARED;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Button btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == PlayState.INITIALIZED || mState == PlayState.STOPPED) {
					try {
						mPlayer.prepare();
						mState = PlayState.PREPARED;
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (mState == PlayState.PREPARED || mState == PlayState.PAUSED) {
					mPlayer.seekTo(progressView.getProgress());
					mPlayer.start();
					mState = PlayState.STARTED;
					mHandler.post(progressRunnable);
				}
			}
		});
        
        btn = (Button)findViewById(R.id.btn_pause);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == PlayState.STARTED) {
					mPlayer.pause();
					mState = PlayState.PAUSED;
				}
			}
		});
        
        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == PlayState.PREPARED || mState == PlayState.STARTED || mState == PlayState.PAUSED) {
					mPlayer.stop();
					mState = PlayState.STOPPED;
				}
			}
		});
        
        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				mState = PlayState.ERROR;
				return false;
			}
		});
        
        mPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mState = PlayState.PAUSED;
			}
		});
        
        progressView = (SeekBar)findViewById(R.id.seek_progress);
        progressView.setMax(mPlayer.getDuration());
        
        progressView.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
        	int progress = -1;
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if (progress != -1) {
					if (mState == PlayState.STARTED) {
						mPlayer.seekTo(progress);
					}
				}
				isSeeking = false;
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				progress = -1;
				isSeeking = true;
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					this.progress = progress;
				}
			}
		});
        
        volumeView = (SeekBar)findViewById(R.id.seek_volume);
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeView.setMax(max);
        volumeView.setProgress(current);
        volumeView.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
				}
			}
		});
        
        CheckBox checkView = (CheckBox)findViewById(R.id.check_mute);
        checkView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
//					mPlayer.setVolume(0f, 0f);
					mHandler.removeCallbacks(volumeUp);
					mHandler.post(volumeDown);
				} else {
//					mPlayer.setVolume(1f, 1f);
					mHandler.removeCallbacks(volumeDown);
					mHandler.post(volumeUp);
				}
			}
		});
    }
    
    float volume = 1.0f;
    Runnable volumeDown = new Runnable() {
		
		@Override
		public void run() {
			volume = volume - 0.1f;
			if (volume > 0) {
				mPlayer.setVolume(volume, volume);
				mHandler.postDelayed(this, INTERVAL);
			} else {
				volume = 0;
				mPlayer.setVolume(0, 0);
			}
		}
	};
	
	Runnable volumeUp = new Runnable() {
		
		@Override
		public void run() {
			volume = volume + 0.1f;
			if (volume < 1) {
				mPlayer.setVolume(volume, volume);
				mHandler.postDelayed(this, INTERVAL);
			} else {
				volume = 1;
				mPlayer.setVolume(1, 1);
			}
		}
	};
    
    
    boolean isSeeking = false;
    private static final int INTERVAL = 100;
    Handler mHandler = new Handler(Looper.getMainLooper());
    
    Runnable progressRunnable = new Runnable() {
		
		@Override
		public void run() {
			if (mState == PlayState.STARTED) {
				if (!isSeeking) {
					int progress = mPlayer.getCurrentPosition();
					progressView.setProgress(progress);
				}
				mHandler.postDelayed(this, INTERVAL);
			}
		}
	};
    
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if (mPlayer != null) {
    		mPlayer.release();
    		mState = PlayState.END;
    		mPlayer = null;
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
