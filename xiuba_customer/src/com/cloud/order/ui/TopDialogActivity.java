package com.cloud.order.ui;

import java.util.List;

import com.cloud.order.constant.Constant;
import com.cloud.order.db.DataStorage;
import com.cloud.order.ui.user.LoginActivity;
import com.google.gson.Gson;
import com.smart.library.encrypt.MD5;
import com.smart.library.okhttp.OkHttpClientManager;
import com.smart.library.util.IntentUtil;
import com.smart.library.util.ToastUtil;
import com.smart.library.view.AlertDialog;
import com.squareup.okhttp.Request;


import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.util.Log;

/**
 * ��������Activity�ϵ�Dialog,���� 1. ��ʾ�汾���� 2. �û�������ʾ 3. ���û�������ʾ
 * 
 * @author lixinquan
 * 
 */
public class TopDialogActivity extends BaseActivity {

//	LoginDataBean loginDataBean;
	int dialogType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		loginDataBean = DataStorage.getLoginData(this);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String pushType = bundle.getString("pushType");
			if (pushType.equals(Constant.PUSH_OFFLINE_NEED_DIALOG)) {
				dialogType = Constant.DIALOG_OFFLINE;
			}
			showDialog(dialogType, bundle);
		}
	}

	public static void goActivity(Context context, String pushType,
			String message) {
		Intent intent = new Intent(context, TopDialogActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("pushType", pushType);
		intent.putExtra("message", message);
		context.startActivity(intent);

	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle bundle) {
		Dialog dialog = null;
		switch (id) {
		case Constant.DIALOG_NEW_VERSION:
			/*
			 * dialog = showDialog(bean.getTitle(), null, bean.getDescribe(),
			 * true, "�ݲ�����", null, "���ϸ���", new DialogInterface.OnClickListener()
			 * {
			 * 
			 * @Override public void onClick(DialogInterface dialog, int which)
			 * { } }, new OnDismissListener() {
			 * 
			 * @Override public void onDismiss(DialogInterface dialog) {
			 * finish(); } });
			 */
			break;
		case Constant.DIALOG_OFFLINE:
			DataStorage.setData(this, "isLogin", "0");// 0δ��¼��1��¼
			String message = bundle == null ? "" : bundle.getString("message");
			dialog = showDialog("����֪ͨ", message, null, false, "�˳�",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							IntentUtil.mStartActivity(TopDialogActivity.this,
									LoginActivity.class);
						}
					}, "���µ�¼", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
//							login();
						}
					}, null);
			break;
		}
		return dialog;
	}

	/**
	 * ��ʾ�Ի���
	 * 
	 * @param title
	 * @param message
	 * @param items
	 * @param isCancelable
	 * @param grayButtonText
	 * @param grayBtnListener
	 * @param greenBtnText
	 * @param greenBtnListener
	 * @param onDismissListener
	 * @return
	 */
	private Dialog showDialog(String title, String message, List<String> items,
			boolean isCancelable, String grayButtonText,
			OnClickListener grayBtnListener, String greenBtnText,
			OnClickListener greenBtnListener,
			OnDismissListener onDismissListener) {
		AlertDialog dialog = new AlertDialog(this);
		dialog.setTitle(title);
		if (message != null) {
			dialog.setMessage(message);
		}
		if (items != null) {
			dialog.setItems(items);
		}
		dialog.setCancelable(isCancelable);
		if (grayButtonText != null) {
			dialog.setGrayButtonListener(grayButtonText, grayBtnListener);
		}
		dialog.setGreenButtonListener(greenBtnText, greenBtnListener);
		if (onDismissListener != null) {
			dialog.setOnDismissListener(onDismissListener);
		}

		return dialog;
	}

//	LoginDataBean loginBean;

//	public void login() {
//		OkHttpClientManager
//				.postAsyn(UserAPI.LoginUrl(),
//						new OkHttpClientManager.ResultCallback<LoginBean>() {
//
//							@Override
//							public void onResponse(LoginBean result) {
//								Log.e("result", result + "");
//								DialogUtil.closeRoundProcessDialog();
//								if (result.getSuccess().equals("true")) {
//									ToastUtil.showToast(TopDialogActivity.this,
//											R.string.toast_login_success);
//									loginBean = result.getData();
//									Gson gson = new Gson();
//									String jsonStr = gson.toJson(loginBean);
//									DataStorage.setData(TopDialogActivity.this,
//											"loginData", jsonStr);// �洢��¼��Ϣ
//									DataStorage.setData(TopDialogActivity.this,
//											"isLogin", "1");// ����Ϊ�ѵ�¼
//									IntentUtil.mStartActivity(
//											TopDialogActivity.this,
//											MainActivity.class);
//								} else {
//									ToastUtil.showToast(TopDialogActivity.this,
//											result.getMessage());
//								}
//
//							}
//
//							@Override
//							public void onError(Request request, Exception e) {
//								DialogUtil.closeRoundProcessDialog();
//								Log.e("result", e + "");
//								ToastUtil.showToast(TopDialogActivity.this, e
//										+ "");
//							}
//						}, new OkHttpClientManager.Param[] {
//								new OkHttpClientManager.Param("phoneNum",
//										loginDataBean.getPhoneNum()),
//								new OkHttpClientManager.Param("password",
//										loginDataBean.getPassword()),
//								new OkHttpClientManager.Param("registrationId",
//										DataStorage.getData(this, "regId")),
//								new OkHttpClientManager.Param("diviceType",
//										"Android") });
//
//	}

}