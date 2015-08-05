package com.example.samples2fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tab1Fragment extends Fragment {

	TextView messageView;
	EditText inputView;
	
	public interface MessageReceiver {
		public void setMessage(String message);
	}
	
	MessageReceiver callback;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof MessageReceiver) {
			callback = (MessageReceiver)activity;
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tab1, container, false);
		messageView = (TextView)view.findViewById(R.id.textView2);
		inputView = (EditText)view.findViewById(R.id.editText1);
		Button btn = (Button)view.findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String message = inputView.getText().toString();
				messageView.setText(message);
//				((MainActivity)getActivity()).setMessage(message);
				if (callback != null) {
					callback.setMessage(message);
				}
			}
		});
		return view;
	}
	
	public String getMessage() {
		return "Hi! Fragment";
	}
}
