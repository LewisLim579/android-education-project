package com.example.hellomelon;

public class Song {
	int songId;
	String songName;
	String albumName;
	int currentRank;
	@Override
	public String toString() {
		return songName + "(" + albumName + ")";
	}
}
