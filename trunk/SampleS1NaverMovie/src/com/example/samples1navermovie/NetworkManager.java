package com.example.samples1navermovie;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.Header;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.begentgroup.xmlparser.XMLParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class NetworkManager {
	private static NetworkManager instance;
	public static NetworkManager getInstance() {
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}
	
	AsyncHttpClient client;
	private NetworkManager() {
		client = new AsyncHttpClient();
		client.setCookieStore(new PersistentCookieStore(MyApplication.getContext()));
		client.setTimeout(30000);
	}
	
	public interface OnResultListener<T> {
		public void onSuccess(T result);
		public void onFail(int code);
	}
	
	private static final String SEARHC_URL = "http://openapi.naver.com/search";
	public void getNaverMovies(Context context, String keyword, final OnResultListener<NaverMovies> listener) {
		RequestParams params = new RequestParams();
		params.put("key", "55f1e342c5bce1cac340ebb6032c7d9a");
		params.put("query", keyword);
		params.put("display", 10);
		params.put("start", 1);
		params.put("target", "movie");
		
		client.get(context, SEARHC_URL, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				ByteArrayInputStream bais = new ByteArrayInputStream(responseBody);
				XMLParser parser = new XMLParser();
				NaverMovies movies = parser.fromXml(bais, "channel", NaverMovies.class);
				listener.onSuccess(movies);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});
		
		
	}
	
	
	
	Handler mHandler = new Handler(Looper.getMainLooper());
	
	public void getNaverMovies(final String keyword,final OnResultListener<NaverMovies> listener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					String urlString = String
							.format("http://openapi.naver.com/search?key=55f1e342c5bce1cac340ebb6032c7d9a&query=%s&display=10&start=1&target=movie",
									URLEncoder.encode(keyword, "utf-8"));
					URL url = new URL(urlString);
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					int code = conn.getResponseCode();
					if (code == HttpURLConnection.HTTP_OK) {
						InputStream is = conn.getInputStream();
						XMLParser parser = new XMLParser();
						final NaverMovies result = parser.fromXml(is, "channel", NaverMovies.class);
						mHandler.post(new Runnable() {
							
							@Override
							public void run() {
								listener.onSuccess(result);
							}
						});
						return;
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mHandler.post(new Runnable() {
					
					@Override
					public void run() {
						listener.onFail(0);
					}
				});
				
			}
		}).start();
	}
}
