package com.example.samples2expandablelistview;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class MyAdapter extends BaseExpandableListAdapter {
	List<GroupData> items = new ArrayList<GroupData>();
	
	public void put(String groupName, String childName) {
		
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
		
		return ((long)groupPosition)<<32 | 0xFFFFFFFF;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return ((long)groupPosition)<<32 | childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupView view;
		if (convertView != null) {
			view = (GroupView)convertView;
		} else {
			view = new GroupView(parent.getContext());
		}
		view.setGroupData(items.get(groupPosition));
		view.setExpandable(isExpanded);
		return view;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildView view;
		if (convertView != null) {
			view = (ChildView)convertView;
		} else {
			view = new ChildView(parent.getContext());
		}
		view.setChildData(items.get(groupPosition).children.get(childPosition));
		view.setLastView(isLastChild);
		return view;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
