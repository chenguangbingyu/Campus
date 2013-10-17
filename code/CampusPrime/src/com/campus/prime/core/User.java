package com.campus.prime.core;

import java.util.Date;

public class User {
	private int id;
	
	private String nick_name;
	
	private String real_name;
		
	private char sex;
	
	private Date birthday;
	
	private char user_type;
	
	private String avatar;
	
	private String description;

	private Academy academy;
	
	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public char getUser_type() {
		return user_type;
	}

	public void setUser_type(char user_type) {
		this.user_type = user_type;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}


	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", nick_name=" + nick_name
				+ ", real_name=" + real_name + ", sex=" + sex + ", birthday="
				+ birthday + ", user_type=" + user_type + ", avatar=" + avatar
				+ ", description=" + description + ", academy=" + academy + "]";
	}
	
	

}
