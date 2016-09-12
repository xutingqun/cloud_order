package com.cloud.order.utils;

import com.cloud.order.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TopUtil {

	/**
	 * ����ͷ��������������ɫ
	 * 
	 * @param activity
	 * @param color
	 */
	public static void setTopBackground(Activity activity, int color) {
		LinearLayout TitlLl = (LinearLayout) activity
				.findViewById(R.id.ll_top_title);
		TitlLl.setBackgroundColor(activity.getResources().getColor(color));
	}

	/**
	 * ����ͷ����������������ʾ������
	 * 
	 * @param activity
	 * @param backImageVisiable
	 * @param backTextVisiable
	 * @param titleImageVisiable
	 * @param titleTextVisiable
	 * @param moreImageVisiable
	 * @param moreTextVisiable
	 */
	public static void setViewVisiable(Activity activity,
			int backImageVisiable, int backTextVisiable,
			int titleImageVisiable, int titleTextVisiable,
			int moreImageVisiable, int moreTextVisiable) {// 0Ϊ����ʾ 1Ϊ��ʾ

		TextView backTv = (TextView) activity.findViewById(R.id.tv_top_back);
		TextView titleTv = (TextView) activity.findViewById(R.id.tv_top_center);
		TextView moreTv = (TextView) activity.findViewById(R.id.tv_top_more);
		ImageView backIv = (ImageView) activity.findViewById(R.id.iv_top_back);
		ImageView titleIv = (ImageView) activity
				.findViewById(R.id.iv_top_center);
		ImageView moreIv = (ImageView) activity.findViewById(R.id.iv_top_more);

		if (backImageVisiable == 0) {
			backIv.setVisibility(View.GONE);
		} else {
			backIv.setVisibility(View.VISIBLE);
		}

		if (backTextVisiable == 0) {
			backTv.setVisibility(View.GONE);
		} else {
			backTv.setVisibility(View.VISIBLE);
		}

		if (titleImageVisiable == 0) {
			titleIv.setVisibility(View.GONE);
		} else {
			titleIv.setVisibility(View.VISIBLE);
		}

		if (titleTextVisiable == 0) {
			titleTv.setVisibility(View.GONE);
		} else {
			titleTv.setVisibility(View.VISIBLE);
		}

		if (moreImageVisiable == 0) {
			moreIv.setVisibility(View.GONE);
		} else {
			moreIv.setVisibility(View.VISIBLE);
		}

		if (moreTextVisiable == 0) {
			moreTv.setVisibility(View.GONE);
		} else {
			moreTv.setVisibility(View.VISIBLE);
		}

	}

	public static void setCenterImageMax(Activity activity, int imageId) {
		ImageView titleMaxIv = (ImageView) activity
				.findViewById(R.id.iv_top_center_max);
		titleMaxIv.setBackgroundResource(imageId);
		titleMaxIv.setVisibility(View.VISIBLE);
	}

	/**
	 * ���ñ�������߰�ťͼƬ
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setLeftImage(Activity activity, int imageId) {
		ImageView backIv = (ImageView) activity.findViewById(R.id.iv_top_back);
		backIv.setBackgroundResource(imageId);
		// backIv.setOnClickListener((OnClickListener) activity);
	}

	/**
	 * ���ñ������м����ͼƬ
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setCenterImage(Activity activity, int imageId) {
		ImageView titleIv = (ImageView) activity
				.findViewById(R.id.iv_top_center);
		titleIv.setBackgroundResource(imageId);
	}

	/**
	 * ���ñ������м����
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setCenterText(Activity activity, String str) {
		TextView titleTv = (TextView) activity.findViewById(R.id.tv_top_center);
		titleTv.setText(str);
	}

	/**
	 * ���ñ�������ɫ
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setCenterTextColor(Activity activity, int color) {
		TextView titleTv = (TextView) activity.findViewById(R.id.tv_top_center);
		titleTv.setTextColor(color);
	}

	/**
	 * �����ұ߱���
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setRightText(Activity activity, String str) {
		TextView moreTv = (TextView) activity.findViewById(R.id.tv_top_more);
		moreTv.setText(str);
	}
	/**
	 * �����ұ߱���
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setRightTextColor(Activity activity, int color) {
		TextView moreTv = (TextView) activity.findViewById(R.id.tv_top_more);
		moreTv.setTextColor(color);
	}
	/**
	 * ���ñ������ұ߰�ťͼƬ
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setRightImage(Activity activity, int imageId) {
		ImageView moreIv = (ImageView) activity.findViewById(R.id.iv_top_more);
		moreIv.setBackgroundResource(imageId);
		moreIv.setOnClickListener((OnClickListener) activity);
	}

	/**
	 * ���õ������
	 * 
	 * @param activity
	 */
	public static void setViewOnClick(Activity activity) {
		LinearLayout backLl = (LinearLayout) activity
				.findViewById(R.id.ll_titlev_back);
		LinearLayout moreLl = (LinearLayout) activity
				.findViewById(R.id.ll_titlev_more);

		backLl.setOnClickListener((OnClickListener) activity);
		moreLl.setOnClickListener((OnClickListener) activity);

	}

}