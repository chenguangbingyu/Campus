package com.campus.prime.core;

public class UserItem extends ItemBase{
	private int id;
	
	private String nick_name;
	
	private String avatar;
	
	private char user_type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public char getUser_type() {
		return user_type;
	}

	public void setUser_type(char user_type) {
		this.user_type = user_type;
	}

	@Override
	public String toString() {
		return "UserProfileItem [id=" + id + ", nick_name=" + nick_name
				+ ", avatar=" + avatar + ", user_type=" + user_type + "]";
	}
	
	
	
}
