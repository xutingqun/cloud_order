package com.smart.library.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * Json����
 * @author yangwl
 */

public class JsonUtils {
	
	/**
	 * ����
	 * �����JSON��ʽ
	 * @param <T>
	 * @param jsonData
	 * @param token eg:  new TypeToken<LinkedList<Model>>() {}
	 * @return
	 */
	public static  <T> LinkedList<T> toBeans(String resultStr,TypeToken<LinkedList<T>> token) {
		Gson gson = new Gson();
		return gson.fromJson(resultStr,token.getType());
	}
	
	/**
	 * �������JSON��ʽ
	 * 
	 * @param jsonData
	 * @return
	 */
	public static Object toBean(String jsonData, Class mBean) {
		Gson gson = new Gson();
		Object mBeans = gson.fromJson(jsonData, mBean);
		return mBeans;
	}
	
	/**
	 * ��JSON�ṹ������ת���������
	 * @param json 
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		if(clazz == null || TextUtils.isEmpty(json)) return null;
		try{
			return new Gson().fromJson(json, clazz);
		} catch (JsonParseException e) {
			Logger.e(e);
		}
		return null;
	}
	
	/**
	 * ��JSON�ṹ������ת���������
	 * @param json 
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json, Type type) {
		if(type == null || TextUtils.isEmpty(json)) return null;
		try{
			return new Gson().fromJson(json, type);
		} catch (JsonParseException e) {
			Logger.e(e);
		}
		return null;
	}

	/**
	 * ��JSON�ṹ������һ���ڵ������ת���������
	 * @param json
	 * @param clazz
	 * @param nodeName �ڵ�����
	 * @return 
	 */
	public static <T> T fromJson(String json, Class<T> clazz, String nodeName) {
		if(TextUtils.isEmpty(nodeName)) return fromJson(json, clazz);
		
		if(clazz == null || TextUtils.isEmpty(json)) return null;
		try {
			JSONObject jsobj = new JSONObject(json);
			String obj = jsobj.getString(nodeName);
			return new Gson().fromJson(obj, clazz);
		} catch (JSONException e) {
			Logger.e(e);
		} catch (JsonParseException e) {
			Logger.e(e);
		}
		return null;
	}
	
	/**
	 * ��JSON�ṹ������һ���ڵ������ת���������
	 * @param json
	 * @param clazz
	 * @param nodeName �ڵ�����
	 * @return 
	 */
	public static <T> T fromJson(String json, Type type, String nodeName) {
		if(TextUtils.isEmpty(nodeName)) return fromJson(json, type);
		
		if(type == null || TextUtils.isEmpty(json)) return null;
		try {
			JSONObject jsobj = new JSONObject(json);
			String obj = jsobj.getString(nodeName);
			return new Gson().fromJson(obj, type);
		} catch (JSONException e) {
			Logger.e(e);
		} catch (JsonParseException e) {
			Logger.e(e);
		}
		return null;
	}
	
	/**
	 * ��JSON�ṹ������ת��Ϊ��ļ���
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> fromArrayJson(String json, Class<T> clazz) {
		if(clazz == null || TextUtils.isEmpty(json)) return Collections.emptyList();
		try {
			JSONArray obj = new JSONArray(json);
			
			int size = obj.length();
			List<T> list = new ArrayList<T>(size);
			Gson gson = new Gson();
			for(int i = 0;i < size;i++) {
				String s = obj.optString(i);
				if(TextUtils.isEmpty(s)) continue;
				list.add(gson.fromJson(s, clazz));
			}
			return list;
		} catch (JsonParseException e) {
			Logger.e(e);
		} catch (JSONException e) {
			Logger.e(e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * ��JSON�ṹ������ת��Ϊ��ļ���
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> fromArrayJson(String json, Type type) {
		if(type == null || TextUtils.isEmpty(json)) return Collections.emptyList();
		try {
			JSONArray obj = new JSONArray(json);
			
			int size = obj.length();
			List<T> list = new ArrayList<T>(size);
			Gson gson = new Gson();
			for(int i = 0;i < size;i++) {
				String s = obj.optString(i);
				if(TextUtils.isEmpty(s)) continue;
				list.add((T) gson.fromJson(s, type));
			}
			return list;
		} catch (JsonParseException e) {
			Logger.e(e);
		} catch (JSONException e) {
			Logger.e(e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * ��JSON�ṹ�е����ݵ�һ���ڵ�ת��Ϊ��ļ���
	 * @param json
	 * @param clazz
	 * @param nodeName
	 * @return
	 */
	public static <T> List<T> fromArrayJson(String json, Class<T> clazz, String nodeName) {
		if(TextUtils.isEmpty(nodeName)) return fromArrayJson(json, clazz);
		
		if(clazz == null || TextUtils.isEmpty(json)) return Collections.emptyList();
		try {
			JSONObject jsobj = new JSONObject(json);
			JSONArray obj = jsobj.getJSONArray(nodeName);
			
			int size = obj.length();
			List<T> list = new ArrayList<T>(size);
			Gson gson = new Gson();
			for(int i = 0;i < size;i++) {
				String s = obj.optString(i);
				if(TextUtils.isEmpty(s)) continue;
				list.add(gson.fromJson(s, clazz));
			}
			return list;
		} catch (JSONException e) {
			Logger.e(e);
		} catch (JsonParseException e) {
			Logger.e(e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * ��JSON�ṹ�е����ݵ�һ���ڵ�ת��Ϊ��ļ���
	 * @param json
	 * @param clazz
	 * @param nodeName
	 * @return
	 */
	public static <T> List<T> fromArrayJson(String json, Type type, String nodeName) {
		if(TextUtils.isEmpty(nodeName)) return fromArrayJson(json, type);
		
		if(type == null || TextUtils.isEmpty(json)) return Collections.emptyList();
		try {
			JSONObject jsobj = new JSONObject(json);
			JSONArray obj = jsobj.getJSONArray(nodeName);
			
			int size = obj.length();
			List<T> list = new ArrayList<T>(size);
			Gson gson = new Gson();
			for(int i = 0;i < size;i++) {
				String s = obj.optString(i);
				if(TextUtils.isEmpty(s)) continue;
				list.add((T) gson.fromJson(s, type));
			}
			return list;
		} catch (JSONException e) {
			Logger.e(e);
		} catch (JsonParseException e) {
			Logger.e(e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * ����ṹת����JSON�ṹ���ַ���
	 * @param t
	 * @return
	 */
	public static <T> String toJson(T t) {
		if(t == null) return "";
		return new Gson().toJson(t);
	}
	
	/**
	 * ���ַ�����json��ʽת�����ַ�������
	 * @param array
	 * @return
	 */
	public static String[] fromStringArrayJson(JSONArray array) {
		int size = array == null ? 0 : array.length();
		String[] a = new String[size];
		for(int i = 0;i < size;i++) {
			a[i] = array.optString(i);
		}
		return a;
	}
	
	/**
	 * ��int��json��ʽת����int����
	 * @param array
	 * @return
	 */
	public static int[] fromIntArrayJson(JSONArray array) {
		int size = array == null ? 0 : array.length();
		int[] a = new int[size];
		for(int i = 0;i < size;i++) {
			a[i] = array.optInt(i);
		}
		return a;
	}

}
