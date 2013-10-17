package com.campus.prime.ui.user;

import java.util.List;

import android.content.Context;

import com.campus.prime.R;
import com.campus.prime.core.GroupItem;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.utils.BitmapManager;

public class GroupListViewAdapter extends SingleTypeAdapter<GroupItem>{
	BitmapManager mBitmapManager = BitmapManager.getInstance();
	
	public GroupListViewAdapter(Context context,List<GroupItem> groups){
		super(context,R.layout.user_item);
		setItems(groups);
	}


	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.iv_avatar,R.id.tv_login};
	}

	@Override
	protected void update(int position, GroupItem item) {
		// TODO Auto-generated method stub
		mBitmapManager.loadBitmap(item.getAvatar(), imageView(0), null, 0, 0);
		setText(1,item.getName());
		
	}
	
	
}

