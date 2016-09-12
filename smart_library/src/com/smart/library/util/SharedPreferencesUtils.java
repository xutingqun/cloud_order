package com.smart.library.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences��һ�������࣬����setParam���ܱ���String, Integer, Boolean, Float, Long���͵Ĳ���
 * ͬ������getParam���ܻ�ȡ���������ֻ����������
 *
 */
public class SharedPreferencesUtils {
	/**
	 * �������ֻ�������ļ���
	 */
	private static final String FILE_NAME = "share_date_xmsmart";
	
	/**
	 * �������ݵķ�����������Ҫ�õ��������ݵľ������ͣ�Ȼ��������͵��ò�ͬ�ı��淽��
	 * @param context
	 * @param key
	 * @param object 
	 */
	public static void setParam(Context context , String key, Object object){
		try {
			String type = object.getClass().getSimpleName();
			SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			
			if("String".equals(type)){
				editor.putString(key, (String)object);
			}
			else if("Integer".equals(type)){
				editor.putInt(key, (Integer)object);
			}
			else if("Boolean".equals(type)){
				editor.putBoolean(key, (Boolean)object);
			}
			else if("Float".equals(type)){
				editor.putFloat(type, (Float)object);
			}
			else if("Long".equals(type)){
				editor.putLong(type, (Long)object);
			}
			
			editor.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	/**
	 * �õ��������ݵķ��������Ǹ���Ĭ��ֵ�õ���������ݵľ������ͣ�Ȼ���������ڵķ�����ȡֵ
	 * @param context
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object getParam(Context context , String key, Object defaultObject){
		try {
			String type = defaultObject.getClass().getSimpleName();
			SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
			
			if("String".equals(type)){
				return sp.getString(key, (String)defaultObject);
			}
			else if("Integer".equals(type)){
				return sp.getInt(key, (Integer)defaultObject);
			}
			else if("Boolean".equals(type)){
				return sp.getBoolean(key, (Boolean)defaultObject);
			}
			else if("Float".equals(type)){
				return sp.getFloat(type, (Float)defaultObject);
			}
			else if("Long".equals(type)){
				return sp.getLong(type, (Long)defaultObject);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static void delDataByKey(Context context,String key){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(key);
		editor.commit();
	}
}
