package com.campus.prime.ui.group;

import java.util.List;

import android.content.Context;

import com.campus.prime.R;
import com.campus.prime.core.UserItem;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.utils.BitmapManager;

public class UserListViewAdapter extends SingleTypeAdapter<UserItem>{
	
	BitmapManager mBitmapManager = BitmapManager.getInstance();
	
	public UserListViewAdapter(Context context,List<UserItem> users){
		super(context,R.layout.user_item);
		setItems(users);
	}
	
	
	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.user_avactar,R.id.user_name,R.id.user_school,R.id.user_description};
	}


	@Override
	protected void update(int position, UserItem item) {
		// TODO Auto-generated method stub
		mBitmapManager.loadBitmap(item.getAvatar(),imageView(0),null,0,0);
		setText(1,item.getNick_name());
		setText(2,"user_school");
		setText(3,"user_description");
	}
	
}
