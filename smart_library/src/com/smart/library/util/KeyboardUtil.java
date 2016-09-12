package com.smart.library.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyboardUtil {

	/**
	 * �ж�����̵�״̬
	 * @return String
	 * @throws
	 */
	public static boolean isKeyboardShow(Activity activity) {
		if (activity == null) return false;
		InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		return imm.isActive();//isOpen������true�����ʾ���뷨��
	}
	
	/**
	 * �ֶ����������
	 */
	public static void toggleKeyboard(Activity activity) {
		if (activity != null) {
			InputMethodManager manager = (InputMethodManager)activity.getBaseContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
			manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * �ֶ����������
	 */
	public static void showKeyboard(View view, Activity activity) {
		if (view != null && activity != null) {
			InputMethodManager manager = (InputMethodManager)activity.getBaseContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
			manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
		}
	}
	
	/**
	 * ���������
	 */
	public static void hideKeyboard(Activity activity) {
		if (activity == null) return;
		if (activity.getCurrentFocus() != null) {
			if (isKeyboardShow(activity)) {
				//���������
				InputMethodManager manager = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
				manager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
	}

	public static void hideKeyboard(View view) {
		InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	
	/**
	 * ��ʾ�����
	 * @param view 
	 * @param endText �Ƿ�Ҫ�������ʾ�����
	 */
	public static void showKeyboard(final EditText view, boolean endText) {
		final InputMethodManager manager = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if(endText) {
			view.setSelection(view.getText().length());
		}
		
		view.postDelayed(new Runnable() {
			@Override
			public void run() {
				view.requestFocus();
				manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
			}
		}, 300);
	}
}