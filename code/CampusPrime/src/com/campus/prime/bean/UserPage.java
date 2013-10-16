package com.campus.prime.bean;

import java.util.List;

public class UserPage extends PageBase{
	
	private List<UserProfileItem> results;

	public void setResults(List<UserProfileItem> results){
		this.results = results;
	}
	
	
	@Override
	public List<UserProfileItem> getResults() {
		// TODO Auto-generated method stub
		return results;
	}
}
