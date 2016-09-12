package com.cloud.order.jpush;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.cloud.order.constant.Constant;
import com.cloud.order.db.DataStorage;
import com.smart.library.util.IntentUtil;


import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * �Զ��������
 * 
 * ������������ Receiver���� 1) Ĭ���û���������� 2) ���ղ����Զ�����Ϣ
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";
	String pushType;

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		pushType = Constant.PUSH_NORMAL;
		if (JPushInterface.EXTRA_EXTRA != null) {
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);// ��ȡ�����ֶΣ���һ��json����
			try {
				JSONObject json = new JSONObject(extras);
				pushType = json.getString("type");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
			String regId = bundle
					.getString(JPushInterface.EXTRA_REGISTRATION_ID);
			DataStorage.setData(context, "regId", regId);
			DataStorage.setData(context, "isLogin", "0");// 0δ��¼��1��¼
			Log.d(TAG, "[MyReceiver] ����Registration Id : " + regId);

		} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
				.getAction())) {
			Log.d(TAG,
					"[MyReceiver] ���յ������������Զ�����Ϣ: "
							+ bundle.getString(JPushInterface.EXTRA_MESSAGE));
			processCustomMessage(context, bundle);

		} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
				.getAction())) {
			Log.d(TAG, "[MyReceiver] ���յ�����������֪ͨ");
			int notifactionId = bundle
					.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
			String message = bundle.getString(JPushInterface.EXTRA_ALERT);
//			if (pushType.equals(Constant.PUSH_OFFLINE_NEED_DIALOG)) {
//				TopDialogActivity.goActivity(context, pushType, message);
//			}
//
//		} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
//				.getAction())) {
//			Log.d(TAG, "[MyReceiver] �û��������֪ͨ");
//			if (pushType.equals(Constant.PUSH_MESSAGE)) {
//				Intent i = new Intent(context, MessageCenterActivity.class);
//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				context.startActivity(i);
//			} else if (pushType.equals(Constant.PUSH_NORMAL)) {
//				Intent i = new Intent(context, MainActivity.class);
//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				context.startActivity(i);
//			}
		} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent
				.getAction())) {
			Log.d(TAG,
					"[MyReceiver] �û��յ���RICH PUSH CALLBACK: "
							+ bundle.getString(JPushInterface.EXTRA_EXTRA));
			// ��������� JPushInterface.EXTRA_EXTRA �����ݴ�����룬������µ�Activity��
			// ��һ����ҳ��..

		} else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent
				.getAction())) {
			boolean connected = intent.getBooleanExtra(
					JPushInterface.EXTRA_CONNECTION_CHANGE, false);
			Log.w(TAG, "[MyReceiver]" + intent.getAction()
					+ " connected state change to " + connected);
		} else {
			Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
		}
	}



	// send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
		// if (MainActivity.isForeground) {
		String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
		// Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
		// msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
		// if (!ExampleUtil.isEmpty(extras)) {
		try {
			JSONObject extraJson = new JSONObject(extras);
			if (null != extraJson && extraJson.length() > 0) {
				// msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
			}
		} catch (JSONException e) {

		}

	}
	// context.sendBroadcast(msgIntent);
	// }
	// }
}
