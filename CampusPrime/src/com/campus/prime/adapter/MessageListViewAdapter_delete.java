package com.campus.prime.adapter;

import java.util.List;

import com.campus.prime.constant.AppConstant;
import com.campus.prime.model.Message;
import com.campus.prime.R;

import RemoteImage.ImageTools;
import RemoteImage.ImageTools.ImageToolsDelegate;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageListViewAdapter_delete extends BaseAdapter{

	private Context context;//运行上下文
	private LayoutInflater listContainer;//视图容器
	private List<Message>listItems;//数据集合
	private int itemViewResource;
	
	
	private ImageToolsDelegate imageToolsDelegate;
	
	static class ListItemView{
		public ImageView avater;
		public TextView username;
		public TextView content;
		public TextView date;
		public TextView commentCount;
	}
	
	public MessageListViewAdapter_delete(Context context,List<Message> data, int resource){
		this.context = context;
		this.listContainer = LayoutInflater.from(context);
		this.itemViewResource = resource;
		this.listItems = data;
		
	}
	
	public MessageListViewAdapter_delete setImageToolsDelegate(ImageToolsDelegate delegate){
		this.imageToolsDelegate = delegate;
		return this;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.d(AppConstant.DEBUG_TAG,listItems.size() + "");
		return listItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		 ListItemView listItemView= null;
		 
		 if(arg1 == null){
			arg1 = listContainer.inflate(this.itemViewResource,null);
			
			listItemView= new ListItemView();
			listItemView.avater = (ImageView)arg1.findViewById(R.id.message_listitem_avater);
			listItemView.username = (TextView)arg1.findViewById(R.id.message_listitem_username);
			listItemView.date = (TextView)arg1.findViewById(R.id.message_listitem_date);
			listItemView.commentCount = (TextView)arg1.findViewById(R.id.message_listitem_commentCount);
			listItemView.content = (TextView)arg1.findViewById(R.id.message_listitem_content);
			arg1.setTag(listItemView);
			
		 }else{
			 listItemView = (ListItemView)arg1.getTag();
		 }
		 
		 Message message = listItems.get(arg0);
		 ImageTools imageTools = new ImageTools().setDelegate(imageToolsDelegate);
		 imageTools.getImage(context, AppConstant.IMAGE_URL,listItemView.avater );
		 listItemView.username.setText(message.getUserId());
		 listItemView.content.setText(message.getContent());
		 listItemView.date.setText(message.getDateTime().toString());
		 listItemView.commentCount.setText(message.getCommentCount() + "");
		 return arg1;
		
	}

}
