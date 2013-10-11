package com.campus.prime.bean;

import java.util.List;

public class MessagePage extends PageBase{
	private List<MessageItem> results;
	
	public void setResults(List<MessageItem> results){
		this.results = results;
	}
	
	@Override
	public List<MessageItem> getResults() {
		// TODO Auto-generated method stub
		return results;
	}
}
