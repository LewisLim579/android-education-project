package com.example.networktestnaver;

public class MovieItem {
	String title;
	String link;
	String image;
	String actor;
	float userRating;
	
	@Override
	public String toString() {
		return title+"("+actor+")";
	}
}
