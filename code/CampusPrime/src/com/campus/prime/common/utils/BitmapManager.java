package com.campus.prime.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;

import Network.Network;
import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * 异步线程加载头像工具类
 * bindAvatar(ImageView imageView, int userId)
 * @author absurd
 *
 */
public class BitmapManager {
	//缓存图片的cache  cache是在内存中
	private static HashMap<String,SoftReference<Bitmap>> cache;
	//
	private static ExecutorService pool;
	
	private static Map<ImageView, String> imageViews;
	
	private Bitmap defaultBmp;
	
	
	static{
		cache = new HashMap<String,SoftReference<Bitmap>>();
		pool = Executors.newFixedThreadPool(5);//线程池
		imageViews = new HashMap<ImageView,String>();
		
	}
	
	public BitmapManager() {
		// TODO Auto-generated constructor stub
	}
	
	public BitmapManager(Bitmap defaultBmp){ 
		this.defaultBmp = defaultBmp;  
	}
	
	/**
	 * 设置默认图片
	 * @param bmp
	 */
	public void setDefaultBmp(Bitmap bmp){
		defaultBmp = bmp;
	}
	/**
	 * 在imageView中加载图片--可制定图片的高和宽
	 * @param url
	 * @param imageView
	 * @param defaultBmp
	 * @param width
	 * @param height
	 */
	public void loadBitmap(String url, ImageView imageView, Bitmap defaultBmp, int width, int height){
		imageViews.put(imageView, url);
		
		Bitmap bitmap = getBitmapFromCache(url);
		//如果cache中已经有该图片，直接加载该图片
		if(bitmap != null){
			imageView.setImageBitmap(bitmap);
		}else{
			//检查SD卡缓存
			String filename = FileUtils.getFileName(url);
			String filepath = imageView.getContext().getFilesDir() + File.separator + filename;
			
			File file = new File(filepath);
			if(file.exists()){
				Bitmap bmp = ImageUtils.getBitmap(imageView.getContext(),filename);
				imageView.setImageBitmap(bmp);
			}else{
				//如果没有  从网络中获取
				imageView.setImageBitmap(defaultBmp);
				queueJob(url,imageView,width,height);
			}
		}
	}
	
	/**
	 * 从网络中加载图片
	 * @param url
	 * @param imageView
	 * @param width
	 * @param height
	 */
	private void queueJob(final String url,final ImageView imageView, final int width, final int height){
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				String tag = imageViews.get(imageView);
				if(tag != null && tag.equals(url)){
					if(msg.obj != null){
						imageView.setImageBitmap((Bitmap)msg.obj);
						//向SD卡写入缓存
						try{
							ImageUtils.saveImage(imageView.getContext(),FileUtils.getFileName(url),(Bitmap)msg.obj);
							
						}catch(IOException e){
							e.printStackTrace();
						}
						
					}
				}
			}
		};
		
		//线程池  最多5个想成同时执行
		pool.execute(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message message = new Message();
				message.obj = downloadBitmap(url,width,height);
				handler.sendMessage(message);
			}
		});
	}
	
	
	private Bitmap downloadBitmap(String url,int width, int height){
		Bitmap bitmap = null;
		try{
			//http请求 下载图片
			
			if(width > 0 && height > 0){
				//制定显示图片的大小
				bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
			}
			cache.put(url,new SoftReference<Bitmap>(bitmap));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return bitmap;
	}
	
	/**
	 * 从cache中获取图片
	 * @param url
	 * @return
	 */
	private Bitmap getBitmapFromCache(String url){
		Bitmap bitmap = null;
		if(cache.containsKey(url)){
			bitmap = cache.get(url).get();
		}
		return bitmap;
	}
	
}
