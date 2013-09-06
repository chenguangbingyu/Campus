package com.campus.prime.common;

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
	 * 将对象转换成json格式
	 * @param ts
	 * @return
	 */
	public static String objectToJson(Object ts){
		String jsonStr = null;
		if(gson != null){
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}
	
	/**
	 * 将json转换为bean对象
	 * @param jsonStr
	 * @param cl
	 * @return
	 */
	public static Object jsonToModel(String jsonStr,Class<?> cl){
		Object obj = null;
		if(gson != null){
			obj = gson.fromJson(jsonStr, cl);
		}
		return obj;
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
	
	/**
	 * 将json格式转换成List对象，并准确制定类型
	 * @param jsonStr
	 * @param type
	 * @return
	 */
	public static List<?> jsonToList(String jsonStr,Type type){
		List<?> objList = null;
		if(gson != null){
			objList = gson.fromJson(jsonStr, type);
		}
		return objList;
	}
	
	public static Map<?, ?> jsonToMap(String jsonStr){
		Map<?, ?> objMap = null;
		if(gson != null){
			Type type = new TypeToken<Map<?, ?>>(){}.getType();
			objMap = gson.fromJson(jsonStr,type);
		}
		return objMap;
	}
	
}
