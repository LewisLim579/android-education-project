package com.example.samples1dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginDialogFragment extends DialogFragment {
	EditText idView, pwView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_dialog, container, false);
		idView = (EditText)v.findViewById(R.id.editText1);
		pwView = (EditText)v.findViewById(R.id.editText2);
		Button btn = (Button)v.findViewById(R.id.btn_ok);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String id = idView.getText().toString();
				String pw = pwView.getText().toString();
				Toast.makeText(getActivity(), "id,pw =  " + id + "," + pw, Toast.LENGTH_SHORT ).show();
				dismiss();
			}
		});
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		getDialog().setTitle("Login Dialog");
		LayoutParams lp = getDialog().getWindow().getAttributes();
		lp.gravity = Gravity.LEFT | Gravity.TOP;
		lp.x = 100;
		lp.y = 200;
		getDialog().getWindow().setAttributes(lp);
		getDialog().getWindow().setLayout(400, WindowManager.LayoutParams.WRAP_CONTENT);
	}
}
