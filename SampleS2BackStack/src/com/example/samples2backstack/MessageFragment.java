package com.example.samples2backstack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MessageFragment extends Fragment {

	public static MessageFragment newInstance(String message) {
		MessageFragment f = new MessageFragment();
		Bundle b = new Bundle();
		b.putString(EXTRA_MESSAGE, message);
		f.setArguments(b);
		return f;
	}
	
	public static final String EXTRA_MESSAGE =  "message";
	String message;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		if (b != null) {
			message = b.getString(EXTRA_MESSAGE);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message, container, false);
		TextView tv = (TextView)view.findViewById(R.id.text_message);
		if (message != null) {
			tv.setText(message);
		}
		return view;
	}
}
