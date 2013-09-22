package com.campus.prime.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.http.HttpMethodName;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.request.GenerateUrlRequest;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;

public class BCSUtils {
	//BCS请求常量
	private static final String ACCESS_KEY = "3de4772c4c4d00162c355b7f0d803f41";
	private static final String SECURE_KEY = "A96c98e72bbcfd46e2f94095039af09e";
	
	private static final String HOST = "bcs.duapp.com";
	private static final String BUCKET = "campus-media";
	private static BaiduBCS baiduBCS = new BaiduBCS(new BCSCredentials(ACCESS_KEY, SECURE_KEY),HOST);
	
	private static final Log log = LogFactory.getLog(BCSUtils.class);
	
	/**
	 * 上传文件到BCS
	 * @param object  BCS中文件的上传路径 
	 * @param content 上传文件的字节数组
	 * @throws IOException
	 */
	private static void upload(String object,byte[] content) throws IOException{
		ObjectMetadata metadata = new ObjectMetadata();
		
		metadata.setContentEncoding("utf-8");
		metadata.setContentLength(content.length);
		
		PutObjectRequest request = new PutObjectRequest(BUCKET, object,new ByteArrayInputStream(content), metadata);
		
		boolean isExist = baiduBCS.doesObjectExist(BUCKET, object);
		if(isExist){
			System.out.println("file exits");
		}else{
			baiduBCS.putObject(request);
		}
		
	}
	
	/**
	 * 从文件中上传文件到BCS
	 * 
	 * @param srcFile 本地文件名称
	 * @param object BCS中文件的存储路径
	 * @return boolean 返回是否上传成功
	 */
	private static boolean PutObjectByFile(File srcFile,String object){
		PutObjectRequest request = new PutObjectRequest(BUCKET,object,srcFile);
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType("application/x-jpg");
		request.setMetadata(metadata);
		
		BaiduBCSResponse<ObjectMetadata> response = baiduBCS.putObject(request);
		metadata = response.getResult();
		//百度文档太烂了！！！！
		return true;
	}
	
	/**
	 * 从输入流中上床文件到BCS
	 * @param content
	 * @param length
	 * @param object
	 * @return boolean
	 */
	private static boolean PutObjectByInputStream(InputStream content,String object,long length){	
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType("application/x-jpg");
		metadata.setContentLength(length);
		PutObjectRequest request =  new PutObjectRequest(BUCKET, object, content,metadata);
		
		ObjectMetadata result = baiduBCS.putObject(request).getResult();
		
		log.info(result.toString());
		return true;
	}
	
	
	
	/**
	 * 获取从BCS中GET请求资源的Url地址
	 * @param object
	 * @return
	 */
	private static String generateUrl(String object){
		GenerateUrlRequest generateUrlRequest = new GenerateUrlRequest(HttpMethodName.GET,BUCKET,object);
		///加密
		return baiduBCS.generateUrl(generateUrlRequest);
	}
	
	
	
	
	
	
	
	
	
}
