package com.campus.prime.ui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;

import com.campus.prime.R;
import com.campus.prime.core.Message;
import com.campus.prime.utils.BitmapManager;

public class MessageListViewAdapter extends SingleTypeAdapter<Message>{
	
	private BitmapManager mBitmapManager;
	
	
	public MessageListViewAdapter(Context context,int layoutResourceId){
		super(LayoutInflater.from(context),layoutResourceId);
		this.context = context;
		mBitmapManager = BitmapManager.getInstance();
	}

	public MessageListViewAdapter(Context context,final List<Message> messages, int layoutResourceId) {
		this(context,layoutResourceId);
		setItems(messages);
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
		mBitmapManager.loadBitmap("/media/test.jpg", imageView(0), null, 0, 0);
		setText(1, item.getId() + "");
		setText(2,item.getContent());
		setText(3,item.getLocation());
		setText(4,item.getMedia());
	}
	
	
}
