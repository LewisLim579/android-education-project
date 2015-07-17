package com.example.samples1dialog;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	String[] items = {"item1","item2","item3","item4","item5","item6"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn_alert);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(android.R.drawable.ic_dialog_alert);
				builder.setTitle("Alert Dialog Test");
				builder.setMessage("This is alert dialog....");
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "Yes Clicked", Toast.LENGTH_SHORT).show();
					}
				});
				builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "Cancel Clicked", Toast.LENGTH_SHORT).show();
					}
				});
				
				builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "No Clicked", Toast.LENGTH_SHORT).show();
					}
				});
//				builder.setCancelable(false);
				builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
					
					@Override
					public void onCancel(DialogInterface dialog) {
						
					}
				});
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
        
        
        btn = (Button)findViewById(R.id.btn_list);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setTitle("List Dialog");
				builder.setItems(items, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "select item : " + items[which], Toast.LENGTH_SHORT).show();
					}
				});
				builder.create().show();
			}
		});
        
        btn = (Button)findViewById(R.id.btn_single);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(android.R.drawable.ic_dialog_info)
						.setTitle("Single Choice")
						.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Toast.makeText(MainActivity.this, "choice item position : " + which, Toast.LENGTH_SHORT).show();
								choicePoistion = which;
								dialog.dismiss();
							}
						}).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if (choicePoistion != -1) {
									Toast.makeText(MainActivity.this, "selected item : " + items[choicePoistion], Toast.LENGTH_SHORT).show();
								}
							}
						}).create().show();
			}
		});
        
        btn = (Button)findViewById(R.id.btn_multiple);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setTitle("Multiple");
				final boolean[] selectlist = new boolean[items.length];
				selectlist[1] = true;
				selectlist[3] = true;
				builder.setMultiChoiceItems(items, selectlist, new DialogInterface.OnMultiChoiceClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						selectlist[which] = isChecked;
					}
				});
				builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < selectlist.length;i++) {
							if (selectlist[i]) {
								sb.append(items[i]).append(",");
							}
						}
						Toast.makeText(MainActivity.this, "select items : " + sb.toString(), Toast.LENGTH_SHORT).show();
					}
				});
				builder.create().show();
			}
		});
        
        btn = (Button)findViewById(R.id.btn_progress_indeterminate);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProgressDialog dialog = new ProgressDialog(MainActivity.this);
				dialog.setIcon(android.R.drawable.ic_dialog_map);
				dialog.setTitle("Progress...");
				dialog.setMessage("Download...");
				dialog.show();
			}
		});
        
        btn = (Button)findViewById(R.id.btn_progress);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProgressDialog dialog = new ProgressDialog(MainActivity.this);
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setIcon(android.R.drawable.ic_dialog_email);;
				dialog.setTitle("Sync Email...");
				dialog.setMessage("Email sync....");
				dialog.setMax(2536);
				dialog.show();
				dialog.setProgress(1321);
			}
		});
    }
    
    int choicePoistion = 0;

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
