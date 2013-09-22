package com.campus.prime.model;


/**
 * 消息列表 message item
 * @author absurd
 *
 */
public class MessageItem {
	private int id;
	
	private String content;
	
	private String location;
	
	private String media;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	@Override
	public String toString() {
		return "MessageItem [id=" + id + ", content=" + content + ", location="
				+ location + ", media=" + media + "]";
	}
	
	
	
	
}
