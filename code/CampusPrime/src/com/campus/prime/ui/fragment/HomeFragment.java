package com.campus.prime.ui.fragment;

import android.os.Bundle;

public class HomeFragment extends MessagePageFragment{
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		setEmptyText("No Message");
	}
	
	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewStateRestored(null);
	}
	
}
