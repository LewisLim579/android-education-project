package com.example.samples1multitypelist;

public class DateData implements ChattingMessage {

	public String date;
	
	@Override
	public int getType() {
		return TYPE_DATE;
	}

}
