package com.example.samples1googlemap;

import java.util.Date;

public class POIData {
	String title;
	String description;
	String created = new Date().toString();
	
	@Override
	public String toString() {
		return title;
	}
}
