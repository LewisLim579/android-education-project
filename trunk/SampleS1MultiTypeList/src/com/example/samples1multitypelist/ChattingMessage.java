package com.example.samples1multitypelist;

public interface ChattingMessage {
	public static final int TYPE_SEND = 1;
	public static final int TYPE_RECEIVE = 2;
	public static final int TYPE_DATE = 3;
	
	public int getType();
}
