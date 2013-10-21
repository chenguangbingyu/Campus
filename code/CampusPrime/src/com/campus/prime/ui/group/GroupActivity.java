package com.campus.prime.ui.group;

import com.campus.prime.R;
import com.campus.prime.ui.TabPagerActivity;
import com.campus.prime.ui.indicator.PageIndicator;
import com.campus.prime.ui.indicator.TitlePageIndicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

/**
 * 群组详细资料的activity
 * 包含三个Fragment GroupBasicFragment，GroupmembersFragment,GroupRecentFragment
 * @author absurd
 *
 */
public class GroupActivity extends TabPagerActivity<GroupPagerAdapter>{
	
	private int groupId;
	
	/**
	 * get groupId
	 * @return
	 */
	public int getGroupId(){
		return groupId;
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		Intent intent = getIntent();
		groupId = intent.getIntExtra("groupId", -1);
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	@Override
	protected GroupPagerAdapter createAdapter() {
		// TODO Auto-generated method stub
		return new GroupPagerAdapter(getSupportFragmentManager());
	}
	
	
}
