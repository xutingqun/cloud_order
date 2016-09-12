package com.cloud.order.db;

import android.content.Context;

import com.smart.library.util.JsonUtils;
import com.smart.library.util.SharedPreferencesUtils;

/**
 * ��������ȡ���ݵĴ洢��ȡ����������
 * 
 * @author yangwl
 * 
 */
public class DataStorage {

	/**
	 * ������Ϣ������
	 * 
	 * @param context
	 * @param key
	 * @param json
	 */
	public static void setData(Context context, String key, String json) {
		SharedPreferencesUtils.setParam(context, key, json);
	}

	/**
	 * ��ȡ���ش洢����Ϣ
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
//	 * ��ȡ��¼�û�����Ϣ
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
	 * ��ȡ�û��Ƿ��ѵ�¼
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isLogin(Context context) {
		boolean isLogin;
		if (getData(context, "isLogin").equals("0")) {// 0δ��¼��1��¼
			isLogin = false;
		} else {
			isLogin = true;
		}

		return isLogin;
	}

}
