package com.smart.library.view;

import com.smart.library.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleView extends LinearLayout {

	private LinearLayout backLayout;
	private LinearLayout moreLayout;
	private TextView backTextView;
	private TextView titleTextView;
	private TextView moreTextView;
	private ImageView backImageView;
	private ImageView titleImageView;
	private ImageView moreImageView;

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.ac_titleview, this);
		backLayout = (LinearLayout) findViewById(R.id.ll_titlev_back);
		moreLayout = (LinearLayout) findViewById(R.id.ll_titlev_more);
		backTextView = (TextView) findViewById(R.id.tv_titlev_back);
		titleTextView = (TextView) findViewById(R.id.tv_titlev_center);
		moreTextView = (TextView) findViewById(R.id.tv_titlev_more);
		backImageView = (ImageView) findViewById(R.id.iv_titlev_back);
		titleImageView = (ImageView) findViewById(R.id.iv_titlev_center);
		moreImageView = (ImageView) findViewById(R.id.iv_titlev_more);
	}

	/**
	 * 设置标题
	 * 
	 * @param text
	 */
	public void setTitleText(String text) {
		titleTextView.setText(text);
	}

	/**
	 * 设置更多名称
	 * 
	 * @param text
	 */
	public void setMoreText(String text) {
		moreTextView.setText(text);
	}

	public void setViewVisiable(int backImageVisiable, int backTextVisiable,
			int titleImageVisiable, int titleTextVisiable,
			int moreImageVisiable, int moreTextVisiable) {//0为不显示 1为显示

		if (backImageVisiable == 0) {
			backImageView.setVisibility(View.GONE);
		} else {
			backImageView.setVisibility(View.VISIBLE);
		}

		if (backTextVisiable == 0) {
			backTextView.setVisibility(View.GONE);
		} else {
			backTextView.setVisibility(View.VISIBLE);
		}

		if (titleImageVisiable == 0) {
			titleImageView.setVisibility(View.GONE);
		} else {
			titleImageView.setVisibility(View.VISIBLE);
		}

		if (titleTextVisiable == 0) {
			titleTextView.setVisibility(View.GONE);
		} else {
			titleTextView.setVisibility(View.VISIBLE);
		}

		if (moreImageVisiable == 0) {
			moreImageView.setVisibility(View.GONE);
		} else {
			moreImageView.setVisibility(View.VISIBLE);
		}

		if (moreTextVisiable == 0) {
			moreTextView.setVisibility(View.GONE);
		} else {
			moreTextView.setVisibility(View.VISIBLE);
		}
		
	}

	// 注册监听事件
	public void registerEvents(OnClickListener onclicklistener) {
		backLayout.setOnClickListener(onclicklistener);
		moreLayout.setOnClickListener(onclicklistener);
	}

}
