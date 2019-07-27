package org.sansilab.leetcode.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonUtils {
	private static final Gson gson=new Gson();
	
	public static <T> T fromJson(String json, Class<T> type){
		return gson.fromJson(json, type);
	}
	
	public static String toJson(Object o){
		String json = gson.toJson(o);
		return json;
	}

	public static JsonArray parseArray(String str){
		return (JsonArray) new JsonParser().parse(str);
	}
}
