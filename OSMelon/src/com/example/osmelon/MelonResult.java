package com.example.osmelon;

import org.json.JSONObject;

public class MelonResult implements JSONParsing {
	Melon melon;

	@Override
	public void parseJSON(JSONObject jobject) {
		if (jobject != null) {
			melon = new Melon();
			JSONObject obj = jobject.optJSONObject("melon");
			melon.parseJSON(obj);
		}
	}
}
