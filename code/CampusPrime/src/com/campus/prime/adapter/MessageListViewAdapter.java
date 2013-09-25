package com.campus.prime.adapter;

import java.util.List;

import com.campus.prime.R;
import com.campus.prime.bean.MessageItem;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.utils.BitmapManager;

import RemoteImage.ImageTools;
import RemoteImage.ImageTools.ImageToolsDelegate;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class MessageListViewAdapter extends SingleTypeAdapter<MessageItem>{
	
	
	private BitmapManager mBitmapManager;
	

	public MessageListViewAdapter(Context context,final List<MessageItem> messages, int layoutResourceId) {
		super(LayoutInflater.from(context), R.layout.messages_listitem);
		// TODO Auto-generated constructor stub
		setItems(messages);
		this.context = context;
		mBitmapManager = BitmapManager.getInstance();
	}

	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.message_listitem_avater,R.id.message_listitem_commentCount,
				R.id.message_listitem_content,R.id.message_listitem_date,R.id.message_listitem_username};
		
	}

		
	
	@Override
	protected void update(int position, MessageItem item) {
		// TODO Auto-generated method stub
		mBitmapManager.loadBitmap(AppConstant.IMAGE_URL, imageView(0), null, 0, 0);
		setText(1, item.getId() + "");
		setText(2,item.getContent());
		setText(3,item.getLocation());
		setText(4,item.getMedia());
	}
	
	
}
