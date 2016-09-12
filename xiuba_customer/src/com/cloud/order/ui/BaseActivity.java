package com.cloud.order.ui;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.smart.library.manager.AppManager;
import com.smart.library.manager.SystemBarTintManager;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class BaseActivity extends FragmentActivity implements
		OnPageChangeListener, OnClickListener, OnItemClickListener {

	public String[] topTitleLogo;
	public String[] topTitleStaff;
	public String[] topTitleSecond;

	public Intent mIntent = null;
	public long downTime; // �ϴΰ��˳���ʱ��
	public boolean isBack = false; // �Ƿ��ܹ��˳�
	public static Boolean flag = false;
	private static String TAG = "broad";
	private static IntentFilter mFilter = null;
	// private int barColor = R.color.appbarbg;// Ĭ����ɫ
	private String url;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		AppManager.getAppManager().addActivity(this);
		// setSwipeBack();
		getNetData();
	}

	private void getNetData() {
		// TODO Auto-generated method stub

	}

	public void BackSharingClick(View view) {
		AppManager.getAppManager().finishActivity();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		AppManager.getAppManager().finishActivity(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AppManager.getAppManager().finishActivity(this);
		}
		return super.onKeyDown(keyCode, event);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	private int barColor = 0;// Ĭ����ɫ

	public int getBarColor() {
		return barColor;
	}

	public void setBarColor(int barColor) {
		this.barColor = barColor;
	}

	/**
	 * ���ô���ɫ��״̬��
	 */
	public void setContentView(int layout) {
		View rootView = View.inflate(this, layout, null);
		rootView.setFitsSystemWindows(true);
		setContentView(rootView);
		if (barColor == 0) {

		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				setTranslucentStatus(true);
			}

			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			tintManager.setStatusBarTintEnabled(true);
			tintManager.setStatusBarTintResource(barColor);
		}
		// setSharingBtn();
		// ����һ����ɫ��ϵͳ��
		// tintManager.setTintColor(Color.parseColor(strColor));
	}

	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

}
