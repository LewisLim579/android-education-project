package com.example.osmelon;

import org.json.JSONObject;

public class Song implements JSONParsing {
	int songId;
	String songName;
	int albumId;
	String albumName;
	int currentRank;
	@Override
	public void parseJSON(JSONObject jobject) {
		songId = jobject.optInt("songId", 0);
		songName = jobject.optString("songName");
	}
	
	@Override
	public String toString() {
		return "["+currentRank+"]"+songName + "(" + albumName + ")";
	}
}
