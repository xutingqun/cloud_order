package com.smart.library.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * ������ת����
 * 
 * @author xutingqun
 * 
 */
public final class IntentUtil {

	/**
	 * ���ظ�������ת������֮��Ҫ��ϲ��ð�ť���ٵ��
	 * 
	 * @param ac
	 * @param mClass
	 */
	public static void mStartActivity(Activity ac, Class<?> mClass) {
		// if (AppManager.isOnlyOneActivity(ac, mClass)) {
		ac.startActivity(new Intent(ac, mClass));
		// }
	}
	/**
	 * ���ظ�������ת������֮��Ҫ��ϲ��ð�ť���ٵ��
	 * 
	 * @param context
	 * @param mClass
	 */
	public static void mStartActivity(Context ac, Class<?> mClass) {
		// if (AppManager.isOnlyOneActivity(ac, mClass)) {
		ac.startActivity(new Intent(ac, mClass));
		// }
	}
	/**
	 * ��Bundle������ת
	 * 
	 * @param ac
	 * @param mClass
	 */
	public static void mStartActivityWithBundle(Activity ac, Class<?> mClass,
			Bundle bundle) {
		// if (AppManager.isOnlyOneActivity(ac, mClass)) {
		Intent intent = new Intent();
		intent.setClass(ac, mClass); // ��������Ŀ��
		Bundle mbundle = bundle; // ����Bundle����
		// bundle.putString("contentId", midContentId[position - 1] + "");
		// // װ������
		intent.putExtras(mbundle); // ��Bundle����Intent����
		ac.startActivity(intent); // ��ʼ�л�
		// ac.startActivity(new Intent(ac,mClass));
		// }
	}
}
