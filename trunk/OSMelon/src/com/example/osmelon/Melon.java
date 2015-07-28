package com.example.osmelon;

import org.json.JSONObject;

public class Melon implements JSONParsing {
	int menuId;
	int count;
	int page;
	int totalPages;
	Songs songs;
	
	@Override
	public void parseJSON(JSONObject jobject) {
		menuId = jobject.optInt("menuId", 0);
		// ...
		JSONObject songsobject = jobject.optJSONObject("songs");
		if (songsobject != null) {
			songs = new Songs();
			songs.parseJSON(songsobject);
		}
	}
	
}
