package com.smart.library.manager;

import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.util.Log;

public class AppManager {

	private static Stack<Activity> activityStack;
	private static AppManager instance;
	
	private AppManager() {
	}
	
	/**
	 * ��һʵ��
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	/**
	 * ���Activity����ջ
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * ������ǰActivity����ջ�����һ��ѹ��ģ�
	 */
	public void finishActivity() {
		try {
			Activity activity = activityStack.lastElement();
			finishActivity(activity);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

	/**
	 * ����ָ����Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}
	/**
	 * ��ѯĳ�������Ƿ����
	 */
	public static boolean isActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
			return true;
			}
		}
		return false;
	}
	/**
	 * ����ָ��������Activity
	 */
	public void finishActivity(Class<?> cls) {
		Log.e("e",activityStack.size()+"  =============");
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * ��������Activity
	 */
    public void finishAllActivity() {
	for (int i = 0, size = activityStack.size(); i < size; i++) {
	    if (null != activityStack.get(i)) {
		activityStack.get(i).finish();
	    }
	}
	activityStack.clear();
    }

	/**
	 * �˳�Ӧ�ó���
	 */
    public void AppExit(Context context) {
	try {
	    finishAllActivity();
	    ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    activityMgr.restartPackage(context.getPackageName());
	    System.exit(0);
	} catch (Exception e) {
	}
    }
	
	
	/**
	 * ��ⰲװ
	 */
    public static boolean checkApp(Context c, String packageName,int versionCode) {
	// ��⵱ǰ������Ӧ���Ƿ�װ,��⵱ǰ�����ĵ�ǰ�汾��Ӧ���Ƿ�װ
	PackageInfo packageInfo = null;
	try {
	    packageInfo = c.getPackageManager().getPackageInfo(packageName, 0);
	    if (packageInfo.versionCode == versionCode) {
		return true;
	    }
	} catch (NameNotFoundException e) {
	    return false;
	}
	return false;
    }
	/**
	 * ���������µ�MainActivity����·��
	 */
	public static String getMainClass(Context c,String packageName){
	    if(!packageName.equals("")){
	    	String mainClassName="";
	    	
	    	Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
		resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		resolveIntent.setPackage(packageName);
		List<ResolveInfo> activitys = c.getPackageManager().queryIntentActivities(resolveIntent, 0);
		ResolveInfo ri = activitys.iterator().next();
		if (ri != null) {
		    mainClassName = ri.activityInfo.name;
		}
		return mainClassName;
	    }
	    return "";
	}
	
	
}
