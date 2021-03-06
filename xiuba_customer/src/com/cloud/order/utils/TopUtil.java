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
	 * 设置头部标题栏背景颜色
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
	 * 设置头部标题栏部件的显示和隐藏
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
			int moreImageVisiable, int moreTextVisiable) {// 0为不显示 1为显示

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
	 * 设置标题栏左边按钮图片
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
	 * 设置标题栏中间标题图片
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
	 * 设置标题栏中间标题
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setCenterText(Activity activity, String str) {
		TextView titleTv = (TextView) activity.findViewById(R.id.tv_top_center);
		titleTv.setText(str);
	}

	/**
	 * 设置标题栏颜色
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setCenterTextColor(Activity activity, int color) {
		TextView titleTv = (TextView) activity.findViewById(R.id.tv_top_center);
		titleTv.setTextColor(color);
	}

	/**
	 * 设置右边标题
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setRightText(Activity activity, String str) {
		TextView moreTv = (TextView) activity.findViewById(R.id.tv_top_more);
		moreTv.setText(str);
	}
	/**
	 * 设置右边标题
	 * 
	 * @param activity
	 * @param imageId
	 */
	public static void setRightTextColor(Activity activity, int color) {
		TextView moreTv = (TextView) activity.findViewById(R.id.tv_top_more);
		moreTv.setTextColor(color);
	}
	/**
	 * 设置标题栏右边按钮图片
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
	 * 设置点击监听
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
