package com.smart.library.util;

import java.util.logging.Logger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * @author hyh
 * 网络检查工具类
 */
public class NetUtils {
    private static ConnectivityManager mConnManager;
    private static NetworkInfo mNetworkInfo;
    public static final int WAP = -5;
    public static final int OK = 1;
    public static final int DISABLE = -1;
    public static final int FAIL = -2;
    /** 未开启移动网络或WLAN */
    public static final int NOINFO = -3;
    public static final int EXCEPTION = -4;

    /**
     * 检测当前是否有可用网络
     *
     * @param context
     * @return 1 可用 -1 不可用 0wap网络 -2连接测试网站失败 -3无网络信息 -4异常
     *
     */
    public static int checkNetConnection(Context context) {
        try {
            mConnManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            mNetworkInfo = mConnManager.getActiveNetworkInfo();

            if (mNetworkInfo == null) {// 检测是否有可用网络信息
                return NOINFO;
            }
            if (!mNetworkInfo.isConnected()) {
                return DISABLE;// 网络没有连接
            }
            // 检测是否是中CMWAP网络,先判断是否为空
            if (!TextUtils.isEmpty(mNetworkInfo.getExtraInfo())
                    && mNetworkInfo.getExtraInfo().toLowerCase().indexOf("wap") > 0) {
                return WAP;
            }
            return OK;
        } catch (Exception e) {
            return EXCEPTION;
        }
    }

    /**
     * 判断是否可联网
     * @param context
     * @return
     */
    public static boolean canNetworking(Context context){
        int netState = checkNetConnection(context);
        if(netState == OK || netState == WAP){
            return true;
        }
        return false;
    }

    /** 检测网络类型，返回：""、"wifi"、"3g"、"net"、"wap"、"other" */
    public static String getNetWorkType(Context context) {
        try {
            mConnManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            mNetworkInfo = mConnManager.getActiveNetworkInfo();
            if (mNetworkInfo == null || !mNetworkInfo.isConnected()) {// 检测是否有可用网络信息//
                // 网络没有连接
                return "";
            }
            if (mNetworkInfo.getTypeName().toLowerCase().contains("wifi")) {
                return "wifi";
            } else {
                if (mNetworkInfo.getExtraInfo() != null) {
                    String apn = mNetworkInfo.getExtraInfo().toLowerCase();
                    if (apn.contains("3g")) {
                        return "3g";
                    } else if (apn.contains("net")) {
                        return "net";
                    } else if (apn.contains("wap")) {
                        return "wap";
                    } else {
                        return apn;
                    }
                } else {
                    return "other";
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static boolean isWIFI(Context context) {
        String netWorkType = getNetWorkType(context);
        if ("wifi".equals(netWorkType)) {
            return true;
        }
        return false;
    }

    /**
     * 无网络状态下，提供用户选择是否进行网络设置，弹出的对话框是不可back的
     * @param activity
     * @param netState
     * @param willFinish 点击按钮后是否关闭当前activity
     */
    public static void turnIntoNetSetting(final Activity activity, final int netState, final boolean willFinish) {
        try {
            String showMeString = "无可用网络";
            if (netState == WAP) {
                showMeString = "不支持wap网络接入方式,请设置接入点(APN)为net方式";
            } else if (netState == FAIL) {
                showMeString = "网络连接失败,请检测网络";
            } else if (netState == NOINFO) {
                showMeString = "未开启移动网络或WLAN";
            } else if (netState == EXCEPTION) {
                showMeString = "检测网络出现异常";
            }
			AlertDialog.Builder b = new AlertDialog.Builder(activity).setTitle("提示").setMessage(showMeString+",是否进行网络设置");
			b.setCancelable(false);
			b.setPositiveButton("设置", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					if (willFinish) {
						activity.finish();
					}
					if (netState == WAP) {
						activity.startActivityForResult(new Intent(Settings.ACTION_APN_SETTINGS), 0);
					} else {
						if (android.os.Build.VERSION.SDK_INT <= 10) {
							activity.startActivityForResult(new Intent(Settings.ACTION_WIRELESS_SETTINGS), 0);
						} else {
							activity.startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
						}
					}
				}
			});
			b.setNegativeButton("取消", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (willFinish) {
						activity.finish();
					}
				}
			}).show();
        } catch (Exception e) {

        }
    }

    /**
     * 无网络状态下，提供用户选择是否进行网络设置
     *
     * @param activity
     */
    public static void turnIntoNetSetting(final Activity activity, final int netState) {
        turnIntoNetSetting(activity, netState, false);
    }

    /**
     * 无网络状态下，提供用户选择是否进行网络设置
     *
     * @param activity
     */
    public static void turnIntoNetSetting(final Activity activity) {
        turnIntoNetSetting(activity, checkNetConnection(activity), false);
    }

    /** 根据网络类型选择下载缓冲吃大小,wifi:100K,3g:32K,其他：8k，无网络：0 */
    public static int chooseBufferSize(Context context) {
        String netWorkType = getNetWorkType(context);
        int size = 8;
        if ("wifi".equals(netWorkType)) {
            size = 100;
        } else if ("3g".equals(netWorkType)) {
            size = 32;
        } else if ("net".equals(netWorkType)) {
            size = 8;
        } else if ("wap".equals("netWorkType")) {
            size = 8;
        } else if ("".equals(netWorkType)) {
            // 无网络
            size = 0;
        }
        return size;
    }
}