package com.example.samples1multitypelist;

public class LeftData implements ChattingMessage {

	public int iconId;
	public String message;
	
	@Override
	public int getType() {
		return TYPE_RECEIVE;
	}

}
