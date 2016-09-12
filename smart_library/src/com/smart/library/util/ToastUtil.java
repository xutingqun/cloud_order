package com.smart.library.util;

import com.smart.library.R;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil {
	private static Toast mToast;
	private static Handler mHandler = new Handler();
	private static int deleyTime = 1500;// Ĭ����ʾʱ��
	private static int toastY = 370;// Ĭ��toast Yλ��
	private static int xOffsets = 0, yOffsets = 0;// toast λ��
	public static int TOAST_HEIGHT_TOP = 10;
	private static Runnable r = new Runnable() {
		public void run() {
			mToast.cancel();
			mToast = null;
		}
	};
	static TextView mToastText;

	/**
	 * �Զ���toast ��ʽ
	 * 
	 * @param mToast
	 */
	private static void setToast(Context mContext, String text) {
		// mToast = Toast.makeText(mContext, null, Toast.LENGTH_SHORT);

		mToast = new Toast(mContext);
		mToast.setDuration(Toast.LENGTH_SHORT);
		View view = mToast.getView().inflate(mContext, R.layout.item_toast,
				null);
		mToastText = (TextView) view.findViewById(R.id.toast_text);
		mToast.setView(view);
		mToast.setGravity(Gravity.CENTER, getxOffset(), getyOffset());
		mToastText.setText(text);
	}

	/**
	 * �Զ�����ʾʱ�����ʾ��ʽ
	 * 
	 * @param mContext
	 * @param text
	 * @param duration
	 */
	public static void showToast(Context mContext, String text, int duration) {
		mHandler.removeCallbacks(r);
		if (mToast != null) {
			// setToast(mContext,text);
			mToastText.setText(text);
		} else {
			setToast(mContext, text);
		}
		mHandler.postDelayed(r, duration);
		mToast.show();
	}

	/**
	 * ��toastλ�ã���ʾ��ʽ
	 * 
	 * @param mContext
	 * @param yOfferSet
	 * @param text
	 */
	public static void showToast(Context mContext, int yOfferSet, String text) {
		setyOffset(yOfferSet);
		showToast(mContext, text, deleyTime);
		setyOffset(toastY);
	}

	/**
	 * ��toastλ�ú�ʱ�䣬��ʾ��ʽ
	 * 
	 * @param mContext
	 * @param yOfferSet
	 * @param text
	 */
	public static void showToast(Context mContext, int yOfferSet, int time,
			String text) {
		setyOffset(yOfferSet);
		showToast(mContext, text, time);
		setyOffset(toastY);
	}

	/**
	 * ��Ĭ����ʾʱ�����ʾ��ʽ
	 * 
	 * @param mContext
	 * @param text
	 */
	public static void showToast(Context mContext, String text) {
		showToast(mContext, text, deleyTime);
	}

	/**
	 * �ı���������resĿ¼�µ���ʾ��ʽ
	 * 
	 * @param mContext
	 * @param resId
	 * @param duration
	 */
	public static void showToast(Context mContext, int resId, int duration) {
		showToast(mContext, mContext.getResources().getString(resId), duration);
	}

	/**
	 * �ı���������resĿ¼���Ҵ�Ĭ����ʾʱ�����ʾ��ʽ
	 * 
	 * @param mContext
	 * @param resId
	 */
	public static void showToast(Context mContext, int resId) {
		showToast(mContext, mContext.getResources().getString(resId), deleyTime);
	}

	public static int getxOffset() {
		return xOffsets;
	}

	public static void setxOffset(int xOffset) {
		xOffsets = xOffset;
	}

	public static int getyOffset() {
		return yOffsets;
	}

	public static void setyOffset(int yOffset) {
		yOffsets = yOffset;
	}

	public static void toastCancle() {
		if (mToast != null) {
			mHandler.removeCallbacks(r);
			mToast.cancel();
			mToast = null;
		}

	}
}