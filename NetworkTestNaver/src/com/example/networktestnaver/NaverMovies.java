package com.example.networktestnaver;

import java.util.ArrayList;

import com.begentgroup.xmlparser.SerializedName;

public class NaverMovies {
	String title;
	int total;
	int start;
	int display;
	@SerializedName("item")
	ArrayList<MovieItem> items;
}
