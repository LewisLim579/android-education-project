package com.example.networktestmelon;

public class Song {
	public int songId;
	public String songName;
	public String albumName;
	public int currentRank;
	@Override
	public String toString() {
		return songName + "\n\r" + albumName + "(" + currentRank +")";
	}
}
