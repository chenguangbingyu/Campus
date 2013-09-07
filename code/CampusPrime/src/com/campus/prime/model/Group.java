package com.campus.prime.model;

import android.util.SparseArray;

public class Group extends ModelBase{
	
	private int groupId;
	
	private String groupName;
	
	//Group 与 user一对一的关系
	private int userId;
	
	
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public boolean saveToDB() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SparseArray<ModelBase> readFromDB() {
		// TODO Auto-generated method stub
		return null;
	}

}
