package com.cloud.order.ui.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.Header;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.library.okhttp.OkHttpClientManager;

import com.cloud.order.R;
import com.cloud.order.api.BaseAPI;
import com.cloud.order.bean.SupplierBean;
import com.cloud.order.db.DataStorage;
import com.cloud.order.ui.BaseActivity;
import com.cloud.order.utils.DialogUtil;
import com.cloud.order.utils.TopUtil;
import com.cloud.order.view.RollHeaderView;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.slidingmenu.lib.SlidingMenu;
import com.smart.library.util.DeviceUtils;
import com.smart.library.util.HttpUtils;
import com.smart.library.util.IntentUtil;
import com.smart.library.util.JsonUtils;
import com.smart.library.util.SharedPreferencesUtils;
import com.smart.library.util.ToastUtil;
import com.smart.library.view.EmptyLayout;
import com.smart.library.view.NewGridView;

import com.squareup.okhttp.Request;

@SuppressLint("ResourceAsColor")
public class LoginActivity extends BaseActivity implements OnItemClickListener {

	private SlidingMenu menu;
	private String url;
	private NewGridView iconGv;
	private RollHeaderView banner;
	private String business;
	private String strbanner;
	EditText editAccount, editPass;
	TextView tvApplyInit, tvApplyTry, tvForget;
	LinearLayout llSubmit;
	LinearLayout rlMore;
	ImageView ivDeleteAccount, ivDeletePass, ivSeePass, ivRemberPass;
	boolean isSave = false, isLook = false, isNoData = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ac_login);

		initTopView();
		initView();

	}

	private void initView() {
		rlMore = (LinearLayout) findViewById(R.id.ll_titlev_more);
		editAccount = (EditText) findViewById(R.id.edit_login_account);
		editPass = (EditText) findViewById(R.id.edit_login_pass);
		ivDeleteAccount = (ImageView) findViewById(R.id.iv_login_clear_account);
		ivDeletePass = (ImageView) findViewById(R.id.iv_login_clear_pass);
		ivSeePass = (ImageView) findViewById(R.id.iv_login_see_pass);
		ivRemberPass = (ImageView) findViewById(R.id.iv_login_rember_pass);
		banner = (RollHeaderView) findViewById(R.id.roll_login_banner);
		tvApplyInit = (TextView) findViewById(R.id.tv_login_apply_init);
		tvApplyTry = (TextView) findViewById(R.id.tv_login_apply_try);
		tvForget = (TextView) findViewById(R.id.tv_login_forget_pass);
		llSubmit = (LinearLayout) findViewById(R.id.ll_login_submit);
		if (editAccount.getText().toString().equals("")) {
			ivDeleteAccount.setVisibility(View.GONE);
		} else {
			ivDeleteAccount.setVisibility(View.VISIBLE);
		}
		if (editAccount.getText().toString().equals("")) {
			ivDeletePass.setVisibility(View.GONE);
		} else {
			ivDeletePass.setVisibility(View.VISIBLE);
		}
		getSupplier();
		ivDeleteAccount.setOnClickListener(this);
		ivDeletePass.setOnClickListener(this);
		ivRemberPass.setOnClickListener(this);
		ivSeePass.setOnClickListener(this);
		tvApplyInit.setOnClickListener(this);
		tvApplyTry.setOnClickListener(this);
		tvForget.setOnClickListener(this);
		llSubmit.setOnClickListener(this);
		rlMore.setOnClickListener(this);

		editAccount.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.toString().length() > 0) {
					ivDeleteAccount.setVisibility(View.VISIBLE);
				} else {
					ivDeleteAccount.setVisibility(View.GONE);
				}
				if (s.toString().length() > 0
						&& !editPass.getText().toString().equals("")
						&& !isNoData) {
					llSubmit.setBackgroundResource(R.drawable.shape_line_blue_bg);
				} else {
					llSubmit.setBackgroundResource(R.drawable.shape_line_grey_bg);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		editPass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.toString().length() > 0) {
					ivDeletePass.setVisibility(View.VISIBLE);
				} else {
					ivDeletePass.setVisibility(View.GONE);
				}
				if (s.toString().length() > 0
						&& !editAccount.getText().toString().equals("")
						&& !isNoData) {
					llSubmit.setBackgroundResource(R.drawable.shape_line_blue_bg);
				} else {
					llSubmit.setBackgroundResource(R.drawable.shape_line_grey_bg);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		banner.setOnHeaderViewClickListener(new RollHeaderView.HeaderViewClickListener() {
			@Override
			public void HeaderViewClick(int position) {
				// Toast.makeText(MainActivity.this, "点击广告 : " + position,
				// Toast.LENGTH_SHORT).show();
			}
		});
		banner.setBackgroundResource(R.drawable.iv_login_defalut_icon);
	}

	List<String> imageList = new ArrayList<String>();

	public void getSupplier() {
		OkHttpClientManager.getAsyn(
				BaseAPI.getSupplierOne(DeviceUtils.getImei(this)),
				new OkHttpClientManager.ResultCallback<SupplierBean>() {

					@Override
					public void onResponse(SupplierBean result) {
						Log.e("result", result.toString());

						if (result.getSuccess().equals("1")) {
							if (result.getResult() == null) {
								isNoData = true;
								
								llSubmit.setBackgroundResource(R.drawable.shape_line_grey_bg);
								tvForget.setTextColor(getResources().getColor(
										R.color.default_shape_line_color));
							} else {
								isNoData = false;
								editAccount.setText(result.getResult().get(0)
										.getFUserName());
								if (!editAccount.getText().toString()
										.equals("")
										&& !editPass.getText().toString()
												.equals("")) {
									llSubmit.setBackgroundResource(R.drawable.shape_line_blue_bg);
								}
								if (!editAccount.getText().toString()
										.equals("")) {
									tvForget.setTextColor(getResources()
											.getColor(
													R.color.defalute_text_color));
								}
								for (int i = 0; i < result.getResult().size(); i++) {
									imageList.add(result.getResult().get(i)
											.getFImageUrl());
								}
								banner.setImgUrlData(imageList);
								DataStorage.setData(LoginActivity.this,
										"serviceAddress", result.getResult()
												.get(0).getFServerAddress());
							}

						} else {
							ToastUtil.showToast(LoginActivity.this,
									result.getMessage());
							getSupplier2();
						}

					}

					@Override
					public void onError(Request request, Exception e) {
						DialogUtil.closeRoundProcessDialog();
						Log.e("result", e + "");
						ToastUtil.showToast(LoginActivity.this, e + "");
					}
				});

	}

	public void getSupplier2() {
		OkHttpClientManager.getAsyn(
				BaseAPI.getSupplierTwo(DeviceUtils.getImei(this)),
				new OkHttpClientManager.ResultCallback<SupplierBean>() {

					@Override
					public void onResponse(SupplierBean result) {
						Log.e("result", result.toString());

						if (result.getSuccess().equals("1")) {

						} else {
							ToastUtil.showToast(LoginActivity.this,
									result.getMessage());
						}

					}

					@Override
					public void onError(Request request, Exception e) {
						DialogUtil.closeRoundProcessDialog();
						Log.e("result", e + "");
						ToastUtil.showToast(LoginActivity.this, e + "");
					}
				});

	}

	/**
	 * 设置头部标题栏
	 */
	private void initTopView() {

		TopUtil.setViewVisiable(this, 0, 0, 0, 1, 0, 1);
		TopUtil.setCenterText(this, "登录");
		TopUtil.setRightText(this, "我的供应商");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_titlev_more:
			Bundle bundle = new Bundle();
			bundle.putBoolean("isNoData", isNoData);
			IntentUtil.mStartActivityWithBundle(LoginActivity.this, MySupplierActivity.class, bundle);
			break;
		case R.id.iv_login_see_pass:
			if (isLook) {
				editPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD
						| InputType.TYPE_CLASS_TEXT);
				ivSeePass
						.setBackgroundResource(R.drawable.iv_login_no_see_password);
				isLook = false;
			} else {
				editPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
				ivSeePass
						.setBackgroundResource(R.drawable.iv_login_see_password);
				isLook = true;
			}
			editPass.setSelection(editPass.getText().length());
			break;
		case R.id.iv_login_clear_account:
			editAccount.setText("");
			break;
		case R.id.iv_login_clear_pass:
			editPass.setText("");
			break;
		case R.id.iv_login_rember_pass:
			if (isSave) {
				ivRemberPass
						.setBackgroundResource(R.drawable.iv_login_remenber_no_password);
				isSave = false;
			} else {
				ivRemberPass
						.setBackgroundResource(R.drawable.iv_login_remenber_password);
				isSave = true;
			}
			break;
		case R.id.tv_login_forget_pass:
			if (isNoData) {

			} else {

			}
			break;
		case R.id.tv_login_apply_try:
			break;
		case R.id.tv_login_apply_init:
			break;
		case R.id.ll_login_submit:
			if (isNoData) {

			} else {

			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		// if (Constant.ISLOGIN && menu != null) //左滑
		// menu.setBehindOffset(getWindowManager().getDefaultDisplay()
		// .getWidth() / 4);
	}

}
