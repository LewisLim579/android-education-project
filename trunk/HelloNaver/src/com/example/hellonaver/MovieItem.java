package com.example.hellonaver;

public class MovieItem {
	String title;
	String link;
	String image;
	String director;
	
	@Override
	public String toString() {
		return title+"(" + director +")";
	}
}
