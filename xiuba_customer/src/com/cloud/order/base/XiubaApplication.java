package com.cloud.order.base;

import cn.jpush.android.api.JPushInterface;

import com.smart.library.SmartApplication;


public class XiubaApplication extends SmartApplication{
	private static final String TAG = "JPush";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
//		SMSSDK.initSDK(this, Constant.APPKEY, Constant.APPSECRET, false);
		//极光初始化
	/*	 JPushInterface.setDebugMode(false); 	// 设置开启日志,发布时请关闭日志
         JPushInterface.init(this);     		// 初始化 JPush*/
	}

}
