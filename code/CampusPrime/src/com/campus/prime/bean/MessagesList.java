package com.campus.prime.bean;

import java.util.List;

public class MessagesList extends PageBase{
	private List<MessageItem> results;

	public List<MessageItem> getResults() {
		return results;
	}

	public void setResults(List<MessageItem> results) {
		this.results = results;
	}
}
