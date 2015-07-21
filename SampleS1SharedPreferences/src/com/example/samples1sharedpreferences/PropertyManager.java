package com.example.samples1sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PropertyManager {
	private static PropertyManager instance;
	public static PropertyManager getInstance() {
		if (instance == null) {
			instance = new PropertyManager();
		}
		return instance;
	}
	
	private static final String PREFS_NAME = "settings";
	SharedPreferences mPrefs;
	SharedPreferences.Editor mEditor;
	
	private PropertyManager() {
		mPrefs = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		mEditor = mPrefs.edit();
	}
	
	private static final String FIELD_USER_ID = "userid";
	public void setUserId(String userid) {
		mEditor.putString(FIELD_USER_ID, userid);
		mEditor.commit();
	}
	
	public String getUserId() {
		return mPrefs.getString(FIELD_USER_ID, "");
	}
	
	private static final String FIELD_PASSWORD = "password";
	public void setPassword(String password) {
		mEditor.putString(FIELD_PASSWORD, password);
		mEditor.commit();
	}
	
	public String getPassword() {
		return mPrefs.getString(FIELD_PASSWORD, "");
	}
	
	
}
