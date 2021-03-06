package com.cloud.order.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class ColorUtil {

	public static ColorStateList setMyColor(Context context, int colorid) {
		Resources resource = (Resources) context.getResources();
		ColorStateList myColor = (ColorStateList) resource
				.getColorStateList(colorid);
		return myColor;
	}

	/**
	 * 设置点击效果
	 * @param view 要设置的View
	 * @param mContext 
	 * @param upId 
	 * @param downId
	 */
	public static void SetButtonBg(View view,Activity mContext,final int upId,final int downId) {
		
		view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(downId);

				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.setBackgroundResource(upId);

				}
				return false;
			}
		});

	}

}
