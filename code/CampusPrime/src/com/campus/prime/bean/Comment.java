package com.campus.prime.bean;



public class Comment{
	private int id;
	
	private String created;
	
	private String content;
	
	private UserProfileItem user;
	
	private int message;
	
	private UserProfileItem reply;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserProfileItem getUser() {
		return user;
	}

	public void setUser(UserProfileItem user) {
		this.user = user;
	}

	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public UserProfileItem getReply() {
		return reply;
	}

	public void setReply(UserProfileItem reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", created=" + created + ", content="
				+ content + ", user=" + user + ", message=" + message
				+ ", reply=" + reply + "]";
	}

	
	
}
