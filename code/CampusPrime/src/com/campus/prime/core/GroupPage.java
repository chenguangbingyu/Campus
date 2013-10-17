package com.campus.prime.core;

import java.util.List;

public class GroupPage extends PageBase{
	private List<GroupItem> results;
	
	public void setResults(List<GroupItem> results){
		this.results = results;
	}

	@Override
	public List<GroupItem> getResults() {
		// TODO Auto-generated method stub
		return results;
	}
	@Override
	public String toString() {
		String r = "";
		for(Object result:results){
			r += ((GroupItem) result).toString();
		}
		return r; 
	}

}
