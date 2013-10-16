package com.campus.prime.adapter;

import java.util.ArrayList;
import java.util.List;

import com.campus.prime.R;
import com.campus.prime.bean.UserProfile;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.utils.BitmapManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeDropdownListAdapter extends SingleTypeAdapter<Object>{
	
	private final LayoutInflater mInflater;
	private final BitmapManager mBitmapManager;
	
	
	private static final int ACTION_PROFILE = 0;
	private static final int ACTION_GROUPS = 1;
	private static final int NON_ORG_ITEMS = 2;
	
	
	
	private int selected;
	
	
	private int getOrgCount(){
		return getCount() - NON_ORG_ITEMS;
	}
	
	private boolean isOrgPosition(final int position){
		return position < getOrgCount();
	}
	
	private int getAction(final int position){
		return position - getOrgCount();
	}
	
	
	private HomeDropdownListAdapter setOrgs(final UserProfile user){
		List<Object> all = new ArrayList<Object>(NON_ORG_ITEMS + 1);
		all.add(user);
		all.add(new Object());
		all.add(new Object());
		setItems(all);
		notifyDataSetChanged();
		return this;
	}
	
	
	
	public HomeDropdownListAdapter(final Context context,final UserProfile user){
		super(context,R.layout.org_item);
		
		mInflater = LayoutInflater.from(context);
		setOrgs(user);
		mBitmapManager = BitmapManager.getInstance();
	}

	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[] {R.id.tv_org_name,R.id.iv_avatar};
	}

	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			convertView = initialize(mInflater.inflate(R.layout.org_dropdown_item, null));
			
		}
		update(position,convertView,getItem(position));
		return convertView;
	}
	
	private void setActionIcon(ImageView imageView,int drawable){
		imageView.setImageResource(drawable);
		imageView.setTag(R.id.iv_avatar,null);
	}
	
	public HomeDropdownListAdapter setSelected(int seelcted){
		this.selected = selected;
		return this;
	}
	
	public int getSelected(){
		return selected;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(!isOrgPosition(position)){
			position = selected;
		}
		return super.getView(position, convertView, parent);
	}
	
	@Override
	protected void update(int position, Object item) {
		// TODO Auto-generated method stub
		switch(getAction(position)){
		case ACTION_PROFILE:
				setText(0,"profile");
				setActionIcon(imageView(1),R.drawable.ic_action_profile);
				break;
		case ACTION_GROUPS:
			setText(0,"groups");
			setActionIcon(imageView(1), R.drawable.ic_action_groups);
			break;
		default:
			//User user = (User)item;
			setText(0,"Absurd");
			mBitmapManager.loadBitmap(AppConstant.IMAGE_URL, imageView(1), null, 0, 0);
		}
	}
}
