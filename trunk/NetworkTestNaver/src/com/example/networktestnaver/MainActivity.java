package com.example.networktestnaver;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.networktestnaver.NetworkManager.OnResultListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends ActionBarActivity {

	ListView listView;
//	ArrayAdapter<MovieItem> mAdapter;
	MyAdapter mAdapter;
	EditText inputView;
	PullToRefreshListView refreshView;
	Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		refreshView = (PullToRefreshListView)findViewById(R.id.listView1);
//		mAdapter = new ArrayAdapter<MovieItem>(this, android.R.layout.simple_list_item_1);
		listView = refreshView.getRefreshableView();
		refreshView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String keyword = mAdapter.getKeyword();
				if (keyword != null && !keyword.equals("")) {
					int start = mAdapter.getStart();
					if (start != -1) {
						NetworkManager.getInstance().getNaverMovie(MainActivity.this, keyword, start, 10, new OnResultListener<NaverMovies>() {

							@Override
							public void onSuccess(NaverMovies result) {
								mAdapter.addAll(result.items);
								MainActivity.this.refreshView.onRefreshComplete();
							}

							@Override
							public void onFail(int code) {
								
							}
							
						});
					}
				}
//				mHandler.postDelayed(new Runnable() {
//					
//					@Override
//					public void run() {
//						MainActivity.this.refreshView.onRefreshComplete();
//					}
//				}, 2000);
			}
		});
		refreshView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				
			}
		});
		mAdapter = new MyAdapter();
		listView.setAdapter(mAdapter);
		inputView = (EditText)findViewById(R.id.edit_input);
		Button btn = (Button)findViewById(R.id.btn_search);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String keyword = inputView.getText().toString();
				if (keyword != null && !keyword.equals("")) {
					NetworkManager.getInstance().getNaverMovie(MainActivity.this, keyword, 1, 10, new OnResultListener<NaverMovies>() {
						
						@Override
						public void onSuccess(NaverMovies result) {
//							for (MovieItem m : result.items) {
//								mAdapter.add(m);
//							}
							mAdapter.addAll(result.items);
							mAdapter.setKeyword(keyword);
							mAdapter.setTotal(result.total);
						}
						
						@Override
						public void onFail(int code) {
							Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
						}
					});
				}
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
