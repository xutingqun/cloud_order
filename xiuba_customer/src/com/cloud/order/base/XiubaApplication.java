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
		//�����ʼ��
	/*	 JPushInterface.setDebugMode(false); 	// ���ÿ�����־,����ʱ��ر���־
         JPushInterface.init(this);     		// ��ʼ�� JPush*/
	}

}
