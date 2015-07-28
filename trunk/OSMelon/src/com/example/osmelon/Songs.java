package com.example.osmelon;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;

public class Songs implements JSONParsing {
	@SerializedName("song")
	ArrayList<Song> songlist;

	@Override
	public void parseJSON(JSONObject jobject) {
		JSONArray array = jobject.optJSONArray("song");
		if (array != null) {
			songlist = new ArrayList<Song>();
			for (int i = 0; i < array.length(); i++) {
				try {
					JSONObject obj = array.getJSONObject(i);
					Song s = new Song();
					s.parseJSON(obj);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
