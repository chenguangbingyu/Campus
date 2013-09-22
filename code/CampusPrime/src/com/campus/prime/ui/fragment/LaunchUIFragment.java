package com.campus.prime.ui.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.campus.prime.R;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.utils.BitmapManager;

public class LaunchUIFragment extends Fragment {
	
	private Button photoButton;
	private Button galleryButton;

	private static final int FLAG_ACTION_IAMGE_CAPTURE = 0;
	private static final int FLAG_IMAGE_CAPTURE_CROP = 1;
	private static final int FLAG_IMAGE_GALLERY = 2;
	
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
		photoButton = (Button)getActivity().findViewById(R.id.photo_button);
		galleryButton = (Button)getActivity().findViewById(R.id.gallery_button);
		photoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//拍照获取图片
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				//intent.putExtra(MediaStore.EXTRA_OUTPUT, uri); 将图片存入本地存储
				startActivityForResult(intent, FLAG_ACTION_IAMGE_CAPTURE);
			}
		});
		
		galleryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//从相册中挑选图片
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent,FLAG_IMAGE_GALLERY);

			}
		});
		
		bitmapManager = BitmapManager.getInstance();
		
	}
	
	
	

}
