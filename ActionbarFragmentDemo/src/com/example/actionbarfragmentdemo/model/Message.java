package com.example.actionbarfragmentdemo.model;

import java.util.Date;

import com.example.actionbarfragmentdemo.database.MessageDB;

import Database.DAOHelper;
import android.content.ContentValues;
import android.util.SparseArray;

public class Message extends ModelBase{
		
	private int messageId;
	//Messge内容
	private String content;
	//message类型
	private MessageType contentType;
	//发布message的user
	private String userId;
	//图片uri
	private String mediaUrl;
	//message发布日期
	private Date dateTime;
	
	private int commentCount;
	
	
	
	public enum MessageType{
		news,
		entertain 
	}


	public int getMessageId() {
		return messageId;
	}


	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public MessageType getContentType() {
		return contentType;
	}


	public void setContentType(MessageType contentType) {
		this.contentType = contentType;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getMediaUrl() {
		return mediaUrl;
	}


	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}


	public Date getDateTime() {
		return dateTime;
	}


	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}


	public int getCommentCount() {
		return commentCount;
	}


	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}


	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", content=" + content
				+ ", contentType=" + contentType + ", userId=" + userId
				+ ", mediaUrl=" + mediaUrl + ", dateTime=" + dateTime
				+ ", commentCount=" + commentCount + "]";
	}


	@Override
	public boolean saveToDB() {
		// TODO Auto-generated method stub
		MessageDB db = (MessageDB)DAOHelper.getInstance().getTable(MessageDB.TABLE);
			db.open();
			ContentValues values = new ContentValues();
			values.put(MessageDB.COLUMN_ID,this.messageId);
			values.put(MessageDB.COLUMN_CONTENT, this.content);
			values.put(MessageDB.COLUMN_CONTENTTYPE,this.contentType.toString());
			values.put(MessageDB.COLUMN_DATETIME,this.dateTime.toString());
			values.put(MessageDB.COLUMN_COMMENTCOUNT, this.commentCount);
			values.put(MessageDB.COLUMN_MEDIAURL,this.mediaUrl);
			values.put(MessageDB.COLUMN_USERID,this.userId);
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
