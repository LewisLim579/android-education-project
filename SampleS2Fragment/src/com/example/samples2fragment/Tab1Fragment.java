package com.example.samples2fragment;

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
				messageView.setText(inputView.getText().toString());
			}
		});
		return view;
	}
}
