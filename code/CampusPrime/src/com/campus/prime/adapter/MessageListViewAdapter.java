package com.campus.prime.adapter;

import java.util.List;

import com.campus.prime.R;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.model.Message;

import RemoteImage.ImageTools;
import RemoteImage.ImageTools.ImageToolsDelegate;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

public class MessageListViewAdapter extends SingleTypeAdapter<Message>{

	private ImageTools imageTools;
	
	private ImageToolsDelegate imageToolsDelegate;
	
	
	
	public MessageListViewAdapter setImageToolsDelegate(ImageToolsDelegate imageToolsDelegate){
		this.imageToolsDelegate = imageToolsDelegate;
		return this;
	}

	

	public MessageListViewAdapter(Context context,final List<Message> messages, int layoutResourceId) {
		super(LayoutInflater.from(context), R.layout.messages_listitem);
		// TODO Auto-generated constructor stub
		setItems(messages);
		this.context = context;
	}

	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.message_listitem_avater,R.id.message_listitem_commentCount,
				R.id.message_listitem_content,R.id.message_listitem_date,R.id.message_listitem_username};
		
	}

	@Override
	protected void update(int position, Message item) {
		// TODO Auto-generated method stub
		imageTools = new ImageTools().setDelegate(imageToolsDelegate);
		if(this.context == null){
			Log.d(AppConstant.DEBUG_TAG,"context is null");
		}else if(imageView(0) == null){
			Log.d(AppConstant.DEBUG_TAG,"imageView is null");
		}
		imageTools.getImage(this.context, AppConstant.IMAGE_URL, imageView(0));
		
		setText(1, item.getCommentCount() + "");
		setText(2,item.getContent());
		setText(3,item.getDateTime().toString());
		setText(4,item.getUserId());
	}
	
	
}
