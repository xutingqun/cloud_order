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
 * �����鹤����
 */
public class NetUtils {
    private static ConnectivityManager mConnManager;
    private static NetworkInfo mNetworkInfo;
    public static final int WAP = -5;
    public static final int OK = 1;
    public static final int DISABLE = -1;
    public static final int FAIL = -2;
    /** δ�����ƶ������WLAN */
    public static final int NOINFO = -3;
    public static final int EXCEPTION = -4;

    /**
     * ��⵱ǰ�Ƿ��п�������
     *
     * @param context
     * @return 1 ���� -1 ������ 0wap���� -2���Ӳ�����վʧ�� -3��������Ϣ -4�쳣
     *
     */
    public static int checkNetConnection(Context context) {
        try {
            mConnManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            mNetworkInfo = mConnManager.getActiveNetworkInfo();

            if (mNetworkInfo == null) {// ����Ƿ��п���������Ϣ
                return NOINFO;
            }
            if (!mNetworkInfo.isConnected()) {
                return DISABLE;// ����û������
            }
            // ����Ƿ�����CMWAP����,���ж��Ƿ�Ϊ��
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
     * �ж��Ƿ������
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

    /** ����������ͣ����أ�""��"wifi"��"3g"��"net"��"wap"��"other" */
    public static String getNetWorkType(Context context) {
        try {
            mConnManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            mNetworkInfo = mConnManager.getActiveNetworkInfo();
            if (mNetworkInfo == null || !mNetworkInfo.isConnected()) {// ����Ƿ��п���������Ϣ//
                // ����û������
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
     * ������״̬�£��ṩ�û�ѡ���Ƿ�����������ã������ĶԻ����ǲ���back��
     * @param activity
     * @param netState
     * @param willFinish �����ť���Ƿ�رյ�ǰactivity
     */
    public static void turnIntoNetSetting(final Activity activity, final int netState, final boolean willFinish) {
        try {
            String showMeString = "�޿�������";
            if (netState == WAP) {
                showMeString = "��֧��wap������뷽ʽ,�����ý����(APN)Ϊnet��ʽ";
            } else if (netState == FAIL) {
                showMeString = "��������ʧ��,��������";
            } else if (netState == NOINFO) {
                showMeString = "δ�����ƶ������WLAN";
            } else if (netState == EXCEPTION) {
                showMeString = "�����������쳣";
            }
			AlertDialog.Builder b = new AlertDialog.Builder(activity).setTitle("��ʾ").setMessage(showMeString+",�Ƿ������������");
			b.setCancelable(false);
			b.setPositiveButton("����", new DialogInterface.OnClickListener() {
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
			b.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

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
     * ������״̬�£��ṩ�û�ѡ���Ƿ������������
     *
     * @param activity
     */
    public static void turnIntoNetSetting(final Activity activity, final int netState) {
        turnIntoNetSetting(activity, netState, false);
    }

    /**
     * ������״̬�£��ṩ�û�ѡ���Ƿ������������
     *
     * @param activity
     */
    public static void turnIntoNetSetting(final Activity activity) {
        turnIntoNetSetting(activity, checkNetConnection(activity), false);
    }

    /** ������������ѡ�����ػ���Դ�С,wifi:100K,3g:32K,������8k�������磺0 */
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
            // ������
            size = 0;
        }
        return size;
    }
}