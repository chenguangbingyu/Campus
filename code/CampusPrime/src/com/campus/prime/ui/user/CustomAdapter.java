package com.campus.prime.ui.user;

import java.util.List;

import com.campus.prime.ui.view.ThemeTextView;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter
{
	
	
	public void setListener(OnItemClickListener listener){
		this.onclickListener = listener;
	}	
	public interface OnItemClickListener{
		public void onClick(String string);
	}
	private OnItemClickListener onclickListener;
	
	Context context;
	List <String> data;
	int list_item_layout;
	int list_item;
	
	
	
	
	
	
	public CustomAdapter(Context context,int list_item_layout,int list_item,List <String> data)
	{
		this.context = context;
		this.data = data;
		this.list_item_layout = list_item_layout;
		this.list_item=list_item;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(list_item_layout, null);
		}
		final ThemeTextView textView = (ThemeTextView) convertView.findViewById(list_item);
		textView.setText(data.get(position));
		textView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String text = textView.getText().toString();
				onclickListener.onClick(text);
				
			}});
		return convertView;
	}
	


}
