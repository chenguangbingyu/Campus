package com.example.actionbarfragmentdemo.model;

public class Message {
	private String title;
	private String content;
	
	public Message(String title,String content) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message [title=" + title + ", content=" + content + "]";
	}
	
	
}
