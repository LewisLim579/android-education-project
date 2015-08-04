package com.example.samples2multitypelist;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SendView extends FrameLayout {

	public SendView(Context context) {
		super(context);
		init();
	}
	
	TextView messageView;
	
	private void init() {
		inflate(getContext(), R.layout.view_send, this);
		messageView = (TextView)findViewById(R.id.text_message);
		
	}
	
	public void setSendData(SendData data) {
		messageView.setText(data.message);
	}

}
