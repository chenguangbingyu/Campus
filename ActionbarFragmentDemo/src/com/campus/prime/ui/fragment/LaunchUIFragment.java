package com.campus.prime.ui.fragment;

import com.campus.prime.constant.AppConstant;
import com.campus.prime.ui.widget.LinkView;
import com.campus.prime.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LaunchUIFragment extends Fragment{
	
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		Log.d(AppConstant.DEBUG_TAG,"fragment on create view");
		
		
		View rootView = inflater.inflate(R.layout.fragment_selection_launch, container,false);
		/**
		LinkView linkView = (LinkView) rootView.findViewById(R.id.link);
		if(linkView != null){
			linkView.setLinkText("wfdsf");
		}else{
			Log.d(AppConstant.DEBUG_TAG,"linkView is null");
		}
		**/
		return rootView;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.d(AppConstant.DEBUG_TAG,"fragment on attach");
		
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.d(AppConstant.DEBUG_TAG,"fragment on activity created");
		LinkView linkView = (LinkView)getActivity().findViewById(R.id.link);
		linkView.setLinkText("test test");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(AppConstant.DEBUG_TAG,"fragment on craete");
	}
}
