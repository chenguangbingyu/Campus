package com.campus.prime.bean;

import java.util.List;

public class Message {
	private int id;
	
	private UserProfileItem user;
	
	private GroupItem group;
	
	private String created;
	
	private String content;
	
	private String media;
	
	private String location;
	
	private int comment_count;
	
	private int praise_count;
	
	private int collect_count;
	
	private int forward_count;
	
	private List<GroupItem> groups;
	
	private int forward;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserProfileItem getUser() {
		return user;
	}

	public void setUser(UserProfileItem user) {
		this.user = user;
	}

	public GroupItem getGroup() {
		return group;
	}

	public void setGroup(GroupItem group) {
		this.group = group;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getComment_count() {
		return comment_count;
	}

	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

	public int getPraise_count() {
		return praise_count;
	}

	public void setPraise_count(int praise_count) {
		this.praise_count = praise_count;
	}

	public int getCollect_count() {
		return collect_count;
	}

	public void setCollect_count(int collect_count) {
		this.collect_count = collect_count;
	}

	public int getForward_count() {
		return forward_count;
	}

	public void setForward_count(int forward_count) {
		this.forward_count = forward_count;
	}

	public List<GroupItem> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupItem> groups) {
		this.groups = groups;
	}

	public int getForward() {
		return forward;
	}

	public void setForward(int forward) {
		this.forward = forward;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", user=" + user + ", group=" + group
				+ ", created=" + created + ", content=" + content + ", media="
				+ media + ", location=" + location + ", comment_count="
				+ comment_count + ", praise_count=" + praise_count
				+ ", collect_count=" + collect_count + ", forward_count="
				+ forward_count + ", groups=" + groups + ", forward=" + forward
				+ "]";
	}

	
}
