package com.example.samples1multitypelist;

public class RightData implements ChattingMessage {

	public String message;
	
	@Override
	public int getType() {
		return TYPE_SEND;
	}

}
