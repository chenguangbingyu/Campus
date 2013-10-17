package com.campus.prime.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campus.prime.R;
import com.campus.prime.bean.GroupItem;
import com.campus.prime.utils.BitmapManager;

public class GroupsDropDownListAdapter extends SingleTypeAdapter<GroupItem>{
	

	private final LayoutInflater mInflater;
	private final BitmapManager mBitmapManager;
	
	
	public GroupsDropDownListAdapter(Context context) {
		super(context, R.layout.org_item);
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		mBitmapManager = BitmapManager.getInstance();
		setOrgs(null);
	}
	
	/**
	 * set list items
	 * @return
	 */
	public GroupsDropDownListAdapter setOrgs(final List<GroupItem> groups){
		GroupItem square = new GroupItem();
		square.setAvatar("http://bcs.duapp.com//campus-media%2Fmedia%2Ftest.jpg?sign=MBO%3A3de4772c4c4d00162c355b7f0d803f41%3Ad3kF0Ruy3VYtTwa1AJzSPXedmSA%3D");
		square.setName("square");
		if(groups != null){
			groups.add(0, square);
			setItems(groups);
		}else{
			List<GroupItem> temp = new ArrayList<GroupItem>();
			temp.add(square);
			setItems(temp);
		}
		return this;
	}
	
	/**
	 * get selected item's group Id
	 * @param selectedId
	 * @return
	 */
	public int getGroupId(int selectedId){
		if(selectedId == 0)
			return -1;
		else
			return getItem(selectedId - 1).getId();
	}
	
	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.tv_org_name,R.id.iv_avatar};
	}
		
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null)
			convertView = initialize(mInflater.inflate(R.layout.org_dropdown_item,null));
		update(position,convertView,getItem(position));
		return convertView;
	}
	
	
	@Override
	protected void update(int position, GroupItem item) {
		// TODO Auto-generated method stub
		setText(0,item.getName());
		mBitmapManager.loadBitmap(item.getAvatar(),imageView(1),null,0,0);
	}
}
