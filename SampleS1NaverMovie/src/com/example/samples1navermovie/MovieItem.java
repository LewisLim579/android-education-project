package com.example.samples1navermovie;

public class MovieItem {
	String title;
	String link;
	String image;
	String director;
	String actor;
	float userRating;
	
	@Override
	public String toString() {
		return title + "\n(" + director + ")";
	}
}
