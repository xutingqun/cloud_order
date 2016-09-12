package com.cloud.order.db;

import android.content.Context;

import com.smart.library.util.JsonUtils;
import com.smart.library.util.SharedPreferencesUtils;

/**
 * 服务器获取数据的存储读取操作方法类
 * 
 * @author yangwl
 * 
 */
public class DataStorage {

	/**
	 * 储存信息到本地
	 * 
	 * @param context
	 * @param key
	 * @param json
	 */
	public static void setData(Context context, String key, String json) {
		SharedPreferencesUtils.setParam(context, key, json);
	}

	/**
	 * 获取本地存储的信息
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getData(Context context, String key) {
		String data = (String) SharedPreferencesUtils
				.getParam(context, key, "");
		return data;
	}

//	/**
//	 * 获取登录用户的信息
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public static LoginDataBean getLoginData(Context context) {
//		LoginDataBean loginDataBean  = new LoginDataBean();
//		String loginData = (String) SharedPreferencesUtils.getParam(context,
//				"loginData", "");
//		if (loginData != null) {
//			 loginDataBean = (LoginDataBean) JsonUtils.toBean(
//					loginData, LoginDataBean.class);
//		}
//		return loginDataBean;
//	}

	/**
	 * 获取用户是否已登录
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isLogin(Context context) {
		boolean isLogin;
		if (getData(context, "isLogin").equals("0")) {// 0未登录，1登录
			isLogin = false;
		} else {
			isLogin = true;
		}

		return isLogin;
	}

}
