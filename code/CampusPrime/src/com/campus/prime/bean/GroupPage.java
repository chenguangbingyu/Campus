package com.campus.prime.bean;

import java.util.List;

public class GroupPage extends PageBase{
	private List<GroupItem> results;
	
	public void setResults(List<GroupItem> results){
		this.results = results;
	}

	@Override
	public List<?> getResults() {
		// TODO Auto-generated method stub
		return results;
	}
	
}
