package com.campus.prime.model;


import java.util.Calendar;
import java.util.Date;

import com.campus.prime.database.UserDB;

import Database.DAOHelper;
import android.content.ContentValues;
import android.util.SparseArray;



public class User extends ModelBase{
	private int userId;
	
	private String userName;
	
	private String loginName;
	
	private String password;
 

	private int userType;   
	
	private int sex;
	
	private Date birth;
	
	private String email;
	
	private String phone;

	private String avatar;

	private String token;
	
	
	
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		int yearNow = calendar.get(Calendar.YEAR);
		calendar.setTime(birth);
		int yearBirth = calendar.get(Calendar.YEAR);
		return (yearNow - yearBirth);
		
	}
	
	public String getSexStr(){
		if(sex == 0){
			return "男";
		}else if(sex == 1){
			return "女";
		}else{
			return "E.T";
		}
	}
	
	public void setSexStr(String sex){
		if (sex == "��"){
			this.sex = 0;
		}else if(sex == "Ů"){
			this.sex = 1;
		}else{
			this.sex = 2;
		}
	}
	
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}
	

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}



	@Override
	public boolean saveToDB() {
		// TODO Auto-generated method stub
		UserDB db = (UserDB)DAOHelper.getInstance().getTable(UserDB.TABLE_NAME);
		db.open();
		ContentValues values = new ContentValues();
		values.put(UserDB.COLUMN_ID,this.userId);
		values.put(UserDB.COLUMN_USERNAME, this.userName);
		values.put(UserDB.COLUMN_AGE,this.birth.toString());
		values.put(UserDB.COLUMN_SEX,this.sex);
		values.put(UserDB.COLUMN_LOGINNAME,this.loginName);
		values.put(UserDB.COLUMN_AVATAR, this.avatar);
		values.put(UserDB.COLUMN_PASSWORD, this.password);
		values.put(UserDB.COLUMN_PHONE, this.phone);
		values.put(UserDB.COLUMN_TOKEN,this.token);
		values.put(UserDB.COLUMN_EMAIL, this.email);
		db.saveToDB(values);
		db.close();
		return true;
		
	}
	

	@Override
	public SparseArray<ModelBase> readFromDB() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
