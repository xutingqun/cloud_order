package com.smart.library.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;

import com.smart.library.SmartApplication;


public final class AppUtils {

	/**
	 * ��ȡӦ����Ϣ
	 * 
	 * @return
	 */
	public static PackageInfo getApkPackageInfo() {
		PackageInfo packageInfo;
		try {
			packageInfo = SmartApplication
					.getInstance()
					.getPackageManager()
					.getPackageInfo(
							SmartApplication.getInstance().getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			packageInfo = null;
		}
		return packageInfo;
	}

	/***
	 * ��ȡversionCode
	 * 
	 * @return ����versionCode�������Ӧ����ȡ�������򷵻�0
	 */
	public static int getApkVersionCode() {

		PackageInfo info = AppUtils.getApkPackageInfo();

		if (info == null) {
			return 0;
		} else {
			return info.versionCode;
		}

	}

	/***
	 * ��ȡversionName
	 * 
	 * @return
	 */
	public static String getApkVersionName() {
		PackageInfo info = AppUtils.getApkPackageInfo();

		if (info == null) {
			return "";
		} else {
			return info.versionName;
		}
	}
	
	/***
     *  ��ȡapk����
     * @return
     */
    public static String getApkPackageName() {
    	Context context = SmartApplication.getInstance();
    	return context.getPackageName();
    }
    
	/**
     * �ж�App�Ƿ���ǰ̨����
     * 
     * @param context
     * @return
     */
	public static boolean isRunningForeground() {
		Context context = SmartApplication.getInstance();
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String currentPackageName = cn.getPackageName();
		if(TextUtils.isEmpty(currentPackageName)) return false;
		
		if (currentPackageName.equals(getApkPackageName())) {
			return true;
		}
		return false;
	}
	
	/**
	 * �ж�Activity �Ƿ�������������ʾ
	 * @param clazz
	 * @return
	 */
	public static boolean isTopActivity(Class<? extends Activity> clazz) {
		Context context = SmartApplication.getInstance();
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String clazzName = cn.getClassName();
		if(TextUtils.isEmpty(clazzName)) return false;
		
		if (clazzName.equals(clazz.getName())) {
			return true;
		}
		return false;
	}
	
	public static String getMetaData(Context context, String name) {
		ApplicationInfo appInfo = null;
		try {
			appInfo = context.getPackageManager().getApplicationInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (appInfo != null) {
			return appInfo.metaData.getString(name);
		}
		return null;
	}
	
	public static void startActivity(Context context, Class<? extends Activity> clazz) {
		context.startActivity(new Intent(context, clazz));
	}
}