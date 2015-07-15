package com.example.samples1expandablelist;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseExpandableListAdapter {

	List<GroupData> items = new ArrayList<GroupData>();
	
	public void add(String groupName, String childName) {
		GroupData group = null;
		for (GroupData g : items) {
			if (g.groupName.equals(groupName)) {
				group = g;
				
				break;
			}
		}
		if (group == null) {
			group = new GroupData();
			group.groupName = groupName;
			items.add(group);
		}
		
		if (childName != null) {
			ChildData child = new ChildData();
			child.childName = childName;
			group.children.add(child);
		}
		
		notifyDataSetChanged();
	}
	
	
	@Override
	public int getGroupCount() {		
		return items.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return items.get(groupPosition).children.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return items.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return items.get(groupPosition).children.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		long id = ((long)groupPosition)<<32 | 0xFFFFFFFF;
		return id;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		long id = ((long)groupPosition)<<32 | childPosition;		
		return id;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView tv;
		if (convertView == null) {
			tv = new TextView(parent.getContext());
		} else {
			tv = (TextView)convertView;
		}
		tv.setText(items.get(groupPosition).groupName);
		return tv;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView tv;
		if (convertView == null) {
			tv = new TextView(parent.getContext());
		} else {
			tv = (TextView)convertView;
		}
		tv.setText(items.get(groupPosition).children.get(childPosition).childName);
		return tv;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
