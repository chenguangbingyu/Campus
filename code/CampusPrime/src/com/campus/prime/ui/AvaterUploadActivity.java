package com.campus.prime.ui;

import java.io.File;

import com.campus.prime.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AvaterUploadActivity extends Activity {
	
	private Button photoButton;
	private Button galleryButton;
	
	// 照相机标志
	private static final int FLAG_ACTION_IAMGE_CAPTURE = 0;
	// 裁剪图片标志
	private static final int FLAG_IMAGE_CAPTURE_CROP = 1;
	// 相册标志
	private static final int FLAG_IMAGE_GALLERY = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avater_upload);
		
		photoButton = (Button)findViewById(R.id.photo_button);
		galleryButton = (Button)findViewById(R.id.gallery_button);
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
	}
	
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		
		if(arg0 == FLAG_ACTION_IAMGE_CAPTURE){
			cropImageUri(arg2);
			return;
		}else if(arg0 == FLAG_IMAGE_CAPTURE_CROP){
			return;
		}else if(arg0 == FLAG_IMAGE_GALLERY){
			return;
		}
	}
	
	/**
	 * 裁剪photo
	 * @param data
	 */
	private void cropImageUri(Intent data){
		if(data == null){
			return;
		}
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(data.getData(), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1); //设置按长和宽的比例裁剪
        intent.putExtra("aspectY", 1);
        int sizeImageHead = getResources().getDimensionPixelSize(R.dimen.s_70);
        intent.putExtra("outputX", sizeImageHead); //设置输出的大小
        intent.putExtra("outputY", sizeImageHead);
        intent.putExtra("scale", true); //设置是否允许拉伸
        // 如果要在给定的uri中获取图片，则必须设置为false，如果设置为true，那么便不会在给定的uri中获取到裁剪的图片
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//设置输出格式
        intent.putExtra("noFaceDetection", true); // 无需人脸识别  默认不需要设置
        startActivityForResult(intent, FLAG_IMAGE_CAPTURE_CROP);
	}
	
	
	private void setAvatarImage(Intent intent){
		if(intent == null){
			return;
		}
		//从intent中获取图片
		final Bitmap avatar = (Bitmap)intent.getExtras().get("data");
		if(avatar == null){
			return;
		}
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.avater_upload, menu);
		return true;
	}

}
