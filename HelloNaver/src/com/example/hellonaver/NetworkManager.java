package com.example.hellonaver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.Header;

import android.content.Context;
import android.os.AsyncTask;

import com.begentgroup.xmlparser.XMLParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class NetworkManager {
	private static NetworkManager instance;
	AsyncHttpClient client;
	public static NetworkManager getInstance() {
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}
	private NetworkManager() {
		KeyStore trustStore;
		try {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);
			MySSLSocketFactory socketFactory = new MySSLSocketFactory(trustStore);
			socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			client = new AsyncHttpClient();
			client.setSSLSocketFactory(socketFactory);
			client.setCookieStore(new PersistentCookieStore(MyApplication.getContext()));
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public interface OnResultListener<T> {
		public void onSuccess(T result);
		public void onFail(int code);
	}

	public void cancelAll(Context context) {
		client.cancelRequests(context, true);
	}
	
	private static final String SERVER = "htpp://openapi.naver.com";
	private static final String SEARCH_URL = SERVER + "/search";
	public void getNaverMovie(Context context, String keyword, final OnResultListener<NaverMovies> listener) {
		RequestParams params = new RequestParams();
		params.put("key", "55f1e342c5bce1cac340ebb6032c7d9a");
		params.put("display", "10");
		params.put("start", "1");
		params.put("target", "movie");
		params.put("query", keyword);
		client.get(context, SEARCH_URL, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				XMLParser parser = new XMLParser();
				NaverMovies result = parser.fromXml(new ByteArrayInputStream(responseBody), "channel", NaverMovies.class);
				listener.onSuccess(result);
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});		
	}
	
	public void getNaverMovie(final OnResultListener<NaverMovies> listener) {
		new AsyncTask<Void, Integer, NaverMovies>() {
			@Override
			protected NaverMovies doInBackground(Void... params) {
				try {
					URL url = new URL("http://openapi.naver.com/search?key=55f1e342c5bce1cac340ebb6032c7d9a&display=10&start=1&target=movie&query=" + URLEncoder.encode("�궗�옉", "utf8"));
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					int responseCode = conn.getResponseCode();
					if (responseCode == HttpURLConnection.HTTP_OK) {
						XMLParser parser = new XMLParser();
						NaverMovies result = parser.fromXml(conn.getInputStream(), "channel", NaverMovies.class);
						return result;
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return null;
			}
			
			@Override
			protected void onPostExecute(NaverMovies result) {
				super.onPostExecute(result);
				if (result != null) {
					listener.onSuccess(result);
				} else {
					listener.onFail(0);
				}
			}
		}.execute();
	}
	
	
}
