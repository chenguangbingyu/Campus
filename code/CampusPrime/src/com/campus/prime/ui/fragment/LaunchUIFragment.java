package com.campus.prime.ui.fragment;

import RemoteImage.ImageTools.ImageToolsDelegate;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.campus.prime.R;
import com.campus.prime.common.utils.BitmapManager;
import com.campus.prime.constant.AppConstant;

public class LaunchUIFragment extends Fragment implements ImageToolsDelegate{
	
	
	private BitmapManager bitmapManager; 
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		Log.d(AppConstant.DEBUG_TAG,"fragment on create view");
		
		
		View rootView = inflater.inflate(R.layout.fragment_selection_launch, container,false);
	
		return rootView;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.d(AppConstant.DEBUG_TAG,"fragment on activity created");
		ImageView imageView = (ImageView)getActivity().findViewById(R.id.imageView);
		
		bitmapManager = BitmapManager.getInstance();
		bitmapManager.loadBitmap(AppConstant.IMAGE_URL, imageView, null, 0, 0);
	}
	

	@Override
	public void downlaodImageFailed() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void downloadImageSuccess() {
		// TODO Auto-generated method stub
		
	}
}
