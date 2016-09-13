package com.cloud.order.ui.user;

import java.util.ArrayList;
import java.util.List;

import com.cloud.order.R;
import com.cloud.order.api.BaseAPI;
import com.cloud.order.api.UserAPI;
import com.cloud.order.bean.MySupplierBean;
import com.cloud.order.bean.MySupplierBean.MySupplierData;
import com.cloud.order.bean.SupplierBean;
import com.cloud.order.db.DataStorage;
import com.cloud.order.ui.BaseActivity;
import com.cloud.order.utils.DialogUtil;
import com.cloud.order.utils.TopUtil;
import com.cloud.order.view.OnActivityTouchListener;
import com.cloud.order.view.RecyclerTouchListener;
import com.cloud.order.view.RecyclerTouchListener.RecyclerTouchListenerHelper;
import com.smart.library.okhttp.OkHttpClientManager;
import com.smart.library.util.DeviceUtils;
import com.smart.library.util.ToastUtil;
import com.smart.library.view.EmptyLayout;
import com.squareup.okhttp.Request;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class AddSupplierActivity extends BaseActivity {

	EditText editName;
	TextView tvSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_add_my_supplier);

		initTopView();
		editName = (EditText) findViewById(R.id.edit_add_my_supplier_num);
		tvSubmit = (TextView) findViewById(R.id.tv_add_my_supplier_sure);

		tvSubmit.setOnClickListener(this);
	}

	public void addSupplier() {
		OkHttpClientManager.getAsyn(UserAPI.addSupplierTwo(
				DeviceUtils.getImei(this), editName.getText().toString()),
				new OkHttpClientManager.ResultCallback<MySupplierBean>() {

					@Override
					public void onResponse(MySupplierBean result) {
						Log.e("result", result.toString());
						DialogUtil.showRoundProcessDialog(
								AddSupplierActivity.this, "正在提交数据。。。");
						if (result.getSuccess().equals("1")) {
							ToastUtil.showToast(AddSupplierActivity.this,
									"添加成功");
							finish();
						} else {
							ToastUtil.showToast(AddSupplierActivity.this,
									result.getMessage());

						}

					}

					@Override
					public void onError(Request request, Exception e) {
						DialogUtil.closeRoundProcessDialog();
						Log.e("result", e + "");
						ToastUtil.showToast(AddSupplierActivity.this, e + "");
					}
				});

	}

	/**
	 * 设置头部标题栏
	 */
	private void initTopView() {
		TopUtil.setViewVisiable(this, 1, 0, 0, 1, 0, 0);
		TopUtil.setCenterText(this, "添加供应商");
		TopUtil.setLeftImage(this, R.drawable.btn_back);
		TopUtil.setViewOnClick(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.tv_add_my_supplier_sure:
			if (editName.getText().toString().equals("")) {
				ToastUtil.showToast(AddSupplierActivity.this, "请填写正确的供应商名称");
			} else {
				addSupplier();
			}

			break;
		case R.id.ll_titlev_back:
			finish();
			break;
		default:
			break;
		}

	}

}
