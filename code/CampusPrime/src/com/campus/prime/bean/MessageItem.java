package com.campus.prime.bean;

import java.util.Date;


/**
 * 消息列表 message item
 * @author absurd
 *
 */
public class MessageItem extends ItemBase{

	private int id;
	
	private UserProfileItem user;

	private String content;
	
	private String location;
	
	private String media;

	private Date created;
	
	private String group;
	
		
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

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
	
	public UserProfileItem getUser() {
		return user;
	}

	public void setUser(UserProfileItem user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "MessageItem [id=" + id + ", content=" + content + ", location="
				+ location + ", media=" + media + "]";
	}
	
	
	
	
}
