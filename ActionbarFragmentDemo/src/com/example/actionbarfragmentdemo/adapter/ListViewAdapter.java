package com.example.actionbarfragmentdemo.adapter;

import java.util.List;

import com.example.actionbarfragmentdemo.R;
import com.example.actionbarfragmentdemo.model.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{

	
	
	private Context context;//运行上下文
	private LayoutInflater listContainer;//视图容器
	private List<Message> listItems;//数据集合
	private int itemViewResource;
	
	
	static class ListItemView{
		public TextView title;
		public TextView content;
	}
	
	public ListViewAdapter(Context context,List<Message> data, int resource){
		this.context = context;
		this.listContainer = LayoutInflater.from(context);
		this.itemViewResource = resource;
		this.listItems = data;
	}
	
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
			listItemView.title = (TextView)arg1.findViewById(R.id.title);
			listItemView.content = (TextView)arg1.findViewById(R.id.content);
			
			arg1.setTag(listItemView);
			
			
		 }else{
			 listItemView = (ListItemView)arg1.getTag();
		 }
		 
		 Message message = listItems.get(arg0);
		 listItemView.title.setText(message.getMessageId());
		 listItemView.content.setText(message.getContent());
		 
		 return arg1;
		
	}

}
