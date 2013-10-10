package com.campus.prime.utils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * json解析工具类
 * @author absurd
 *
 */
public class JsonUtil {
	private static Gson gson = null;
	
	static {
		if(gson == null){
			gson = new Gson();
		}
	}
	//私有构造函数
	private JsonUtil(){
		
	}
	
	
	/**
	 * jsonStr转换为bean
	 * @param jsonStr
	 * @param type
	 * @return
	 */
	public static <V> V fromJson(String jsonStr,Type type){
		return gson.fromJson(jsonStr, type);
	
	}
	
	/**
	 * object 转换为json
	 * @return
	 */
	public static String toJson(final Object object){
			return gson.toJson(object);
	}

		
	/**
	 * 将json格式转换成list对象
	 * @param jsonStr
	 * @return
	 */
	public static List<?> jsonToList(String jsonStr){
		List<?> objList = null;
		if(gson != null){
			Type type = new TypeToken<List<?>>(){}.getType();
			objList = gson.fromJson(jsonStr,type);
		}
		return objList;
	}
	
		
}
