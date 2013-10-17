package com.campus.prime.core;

import java.util.List;

public class CommentPage extends PageBase{
	private List<MessageItem> results;
	
	public void setResults(List<MessageItem> results){
		this.results = results;
	}

	@Override
	public List<?> getResults() {
		// TODO Auto-generated method stub
		return results;
	}
	
}
