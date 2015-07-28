package com.example.osnavermovie;

import java.util.ArrayList;

import com.begentgroup.xmlparser.SerializedName;

public class Movies {
	String title;
	String link;
	int total;
	int start;
	int display;
	@SerializedName("item")
	ArrayList<MovieItem> items;
}
