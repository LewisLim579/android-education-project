package com.example.samples1fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TabOneFragment extends Fragment {

	TextView messageView;
	EditText inputView;
	int mCount;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		if (b != null) {
			mCount = b.getInt("count", 0);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tab1, container, false);
		messageView = (TextView)view.findViewById(R.id.text_message);
		messageView.setText("count : " + mCount);
		inputView = (EditText)view.findViewById(R.id.edit_input);
		Button btn = (Button)view.findViewById(R.id.btn_send);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				messageView.setText(inputView.getText().toString());
			}
		});
		return view;
	}
}
