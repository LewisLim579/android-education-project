package com.example.samples2expandablelistview;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.TextView;

public class GroupView extends FrameLayout {

	public GroupView(Context context) {
		super(context);
		init();
	}
	
	TextView nameView;
	private void init() {
		inflate(getContext(), R.layout.view_group, this);
		nameView = (TextView)findViewById(R.id.text_message);
	}
	
	public void setGroupData(GroupData data) {
		nameView.setText(data.groupName);
	}
	
	public void setExpandable(boolean expandable) {
		if (expandable) {
			setBackgroundColor(Color.YELLOW);
		} else {
			setBackgroundColor(Color.TRANSPARENT);
		}
	}

}
