package com.smart.library.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.smart.library.SmartApplication;



public class DeviceUtils {
	private static final String TAG = DeviceUtils.class.getSimpleName();
	
	public static int getScreenWidth() {
		WindowManager manager = (WindowManager)SmartApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        if (dm.widthPixels > dm.heightPixels) {
            return dm.heightPixels;
        } else {
            return dm.widthPixels;
        }
	}
	
	public static final int getScreenHeight() {
		WindowManager manager = (WindowManager)SmartApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        if (dm.widthPixels > dm.heightPixels) {
            return dm.widthPixels;
        } else {
            return dm.heightPixels;
        }
	}
	
	/**
	 * 获取手机品牌
	 * 
	 * @return
	 */
	public static String getDeviceBrand() {
		return Build.BRAND;
	}

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public static String getDeviceMode() {
		return Build.MODEL;
	}

	/**
	 * 获取手机版本号
	 * 
	 * @return
	 */
	public static String getDeviceVersionoRelease() {
		return Build.VERSION.RELEASE;
	}
	
    public static String getDeviceId() {
    	return Secure.getString(SmartApplication.getInstance().getContentResolver(), Secure.ANDROID_ID); 
    }

    /***
     *  获得手机mac值
     * @return 手机mac值，不一定能获得到值，有可能返回null
     */
    public static String getMacValue() {

        String macValue = null;

        // WifiManager获得方式在开机后没有打开过wifi，就无法获得mac
        WifiManager wifi = (WifiManager) SmartApplication.getInstance().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        macValue = info.getMacAddress();

        if (TextUtils.isEmpty(macValue)) {
            try {
                String str = "";
                // 该方式在MX、root的小米上均无法获得，在联想S720上可以
                Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
                InputStreamReader ir = new InputStreamReader(pp.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);

                for (; null != str;) {
                    str = input.readLine();
                    if (str != null) {
                        macValue = str.trim();// 去空格
                        break;
                    }
                }
           
            } catch (IOException ex) {
                // 赋予默认值
            }
        }
        return macValue;
    }

    /***
     *  获得手机设备的imei值
     * @return
     */
    public static String getImeiValue() {
        return ((TelephonyManager) SmartApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }
    
    public static int getSIMState() {
    	TelephonyManager tm = (TelephonyManager) SmartApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);       
//        String deviceid = tm.getDeviceId();
//        String tel = tm.getLine1Number();
//        String  imei = tm.getSimSerialNumber();
//        String imsi = tm.getSubscriberId();
        return tm.getSimState();
    }
    
    /**
     * 判断SDK版本是否超过指定版本号
     *
     * @param sdkNum
     * @return true 大于指定版本号  false 反之，小于等于指定版本号
     */
    public static boolean isSDKVersionMoreThanSpecifiedNum(int sdkNum) {
        return android.os.Build.VERSION.SDK_INT > sdkNum ? true : false;
    }
    
    /**
	 * 将dip、dp、sp等转换为px对应的数值
	 * @param dip
	 * @return
	 */
	public static float dipToPx(float dip) {
		SmartApplication app = SmartApplication.getInstance();
		if(app == null) return dip;
		DisplayMetrics dm = app.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, dm);
	}
	
	/**
	 * px转为dip
	 * @param px
	 * @return
	 */
	public static int pxToDip(int px) {
		DisplayMetrics dm = SmartApplication.getInstance().getResources().getDisplayMetrics();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, dm);
	}
	
	/**
     * 获取系统title高度
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {

        }
        return sbar;
    }
    
    /**
     * 获取ActionBar的ID
     * @param context
     * @return
     */
    public static int getActionBarId(){
    	Class<?> c = null;
    	Object obj = null;
    	Field field = null;
    	try {
    		c = Class.forName("com.android.internal.R$id");
    		obj = c.newInstance();
    		field = c.getField("action_bar_container");
    		int x = Integer.parseInt(field.get(obj).toString());
    		return x;
    	} catch (Exception e) {
    	
    	}
    	return 0;
    }
    
    /**
	 * 获取手机导航栏的高度
	 * @param context
	 * @return
	 */
	public static int getNavigationBarHeight(Context context) {
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height",
				"dimen", "android");
		// 获取NavigationBar的高度
		int height = resources.getDimensionPixelSize(resourceId);
		return height;
	}
    
//	@SuppressLint("NewApi")
//	public static boolean checkDeviceHasNavigationBar(Context context) {
//		// 通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
//		boolean hasMenuKey = ViewConfiguration.get(context)
//				.hasPermanentMenuKey();
//		boolean hasBackKey = KeyCharacterMap
//				.deviceHasKey(KeyEvent.KEYCODE_BACK);
//		if (!hasMenuKey && !hasBackKey) {
//			// 这个设备有一个导航栏
//			return true;
//		}
//		return false;
//	}
	
	public static boolean checkDeviceHasNavigationBar(Context context) {
		boolean hasNavigationBar = false;
		Resources rs = context.getResources();
		int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
		if (id > 0) hasNavigationBar = rs.getBoolean(id);
		try {
			Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
			Method m = systemPropertiesClass.getMethod("get", String.class);
			String navBarOverride = (String) m.invoke(systemPropertiesClass,
					"qemu.hw.mainkeys");
			if ("1".equals(navBarOverride)) {
				hasNavigationBar = false;
			} else if ("0".equals(navBarOverride)) {
				hasNavigationBar = true;
			}
		} catch (Exception e) {
			
		}
		return hasNavigationBar;
	}
	
	/**
	 * 判断是否有实体按键
	 * @param context
	 * @return true 实体按键，false 虚拟按键
	 */
/*	public static boolean hasPermanentMenuKey(Context context) {
		if(VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH) {
			return ViewConfiguration.get(context).hasPermanentMenuKey();
		} else {
			try {
				ViewConfiguration mconfig = ViewConfiguration.get(context);
				Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
				if (menuKeyField != null) {
					return menuKeyField.getBoolean(mconfig);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return false;
		}
	}*/
	
	/**
	 * 获取手机Imei码
	 * @param ac
	 * @return
	 */
	public static String getImei(Activity ac) {
		TelephonyManager tm = (TelephonyManager) ac
				.getSystemService(ac.TELEPHONY_SERVICE);
		String imei = tm.getDeviceId();// 设备唯一识别码
		return imei;
	}
}