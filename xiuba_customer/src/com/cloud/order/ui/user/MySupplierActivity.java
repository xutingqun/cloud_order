package com.cloud.order.ui.user;

import java.util.ArrayList;
import java.util.List;

import com.cloud.order.R;
import com.cloud.order.api.BaseAPI;
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
import com.smart.library.util.ImageUtils;
import com.smart.library.util.IntentUtil;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 消息中心 Created by qjh on 14/8/18.
 */
@SuppressLint("ResourceAsColor")
public class MySupplierActivity extends BaseActivity implements
		RecyclerTouchListener.RecyclerTouchListenerHelper {
	private OnActivityTouchListener touchListener;
	private RecyclerTouchListener onTouchListener;
	// private ImageView image_empty;
	RecyclerView mRecyclerView;
	boolean isNoData;
	MySupplierAdapter mAdapter;
	private boolean flag;// 取消 ，编辑
	private List<MySupplierBean> list;
	EmptyLayout emptyLayout;
	TextView tvAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_my_supplier);

		initTopView();

		isNoData = getIntent().getExtras().getBoolean("isNoData");
		tvAdd = (TextView) findViewById(R.id.tv_add_my_supplier);
		tvAdd.setOnClickListener(this);
		emptyLayout = (EmptyLayout) findViewById(R.id.empty_my_supplier);
		emptyLayout.setVisibility(View.VISIBLE);
		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		onTouchListener = new RecyclerTouchListener(this, mRecyclerView);
		onTouchListener
				.setSwipeOptionViews(R.id.add, R.id.edit, R.id.change)
				.setSwipeable(
						R.id.rowFG,
						R.id.rowBG,
						new RecyclerTouchListener.OnSwipeOptionsClickListener() {
							@Override
							public void onSwipeOptionClicked(int viewID,
									int position) {

								// 侧滑 删除

							}
						});

		mRecyclerView.addOnItemTouchListener(onTouchListener);
		if (isNoData) {
			emptyLayout.setNoDataContent("暂时没有供应商，快去添加吧~");
			emptyLayout.setErrorType(EmptyLayout.NODATA);

			emptyLayout.setVisibility(View.VISIBLE);
		} else {
			getSupplier();
		}
		emptyLayout.setOnLayoutClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getSupplier();
			}
		});
	}

	public void getSupplier() {
		OkHttpClientManager.getAsyn(
				BaseAPI.getSupplierOne(DeviceUtils.getImei(this)),
				new OkHttpClientManager.ResultCallback<MySupplierBean>() {

					@Override
					public void onResponse(MySupplierBean result) {
						Log.e("result", result.toString());

						if (result.getSuccess().equals("1")) {
							emptyLayout.setVisibility(View.GONE);
							mAdapter = new MySupplierAdapter(
									MySupplierActivity.this, result.getResult());
							mRecyclerView.setAdapter(mAdapter);
						} else {
							ToastUtil.showToast(MySupplierActivity.this,
									result.getMessage());

						}

					}

					@Override
					public void onError(Request request, Exception e) {
						DialogUtil.closeRoundProcessDialog();
						Log.e("result", e + "");
						ToastUtil.showToast(MySupplierActivity.this, e + "");
					}
				});

	}

	/**
	 * 设置头部标题栏
	 */
	private void initTopView() {
		TopUtil.setViewVisiable(this, 1, 0, 0, 1, 0, 0);
		TopUtil.setCenterText(this, "我的供应商");
		TopUtil.setLeftImage(this, R.drawable.back_btn);
		TopUtil.setViewOnClick(this);

	}

	@Override
	public void setOnActivityTouchListener(OnActivityTouchListener listener) {
		this.touchListener = listener;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (touchListener != null)
			touchListener.getTouchCoordinates(ev);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mRecyclerView.removeOnItemTouchListener(onTouchListener);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ll_titlev_more:

			if (!flag) {
				// TopUtil.setRightText(this, "取消");
				flag = true;
			} else {
				// TopUtil.setRightText(this, "编辑");
				flag = false;
			}
			break;
		case R.id.ll_titlev_back:
			finish();
			break;
		case R.id.tv_add_my_supplier:
			IntentUtil.mStartActivity(MySupplierActivity.this,
					AddSupplierActivity.class);
			break;
		default:
			break;
		}

	}

	private class MySupplierAdapter extends
			RecyclerView.Adapter<MySupplierAdapter.MainViewHolder> {
		LayoutInflater inflater;
		List<MySupplierData> modelList;

		public MySupplierAdapter(Context context, List<MySupplierData> list) {
			inflater = LayoutInflater.from(context);
			modelList = new ArrayList<MySupplierData>(list);
		}

		@Override
		public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = inflater.inflate(R.layout.recycler_row, parent, false);
			return new MainViewHolder(view);
		}

		@Override
		public void onBindViewHolder(MainViewHolder holder, int position) {
			holder.bindData(modelList.get(position));
		}

		@Override
		public int getItemCount() {
			return modelList.size();
		}

		class MainViewHolder extends RecyclerView.ViewHolder {

			TextView name, phone, address;
			ImageView ivIcon;

			public MainViewHolder(View itemView) {
				super(itemView);
				ivIcon = (ImageView)itemView.findViewById(R.id.iv_my_supplier);
				name = (TextView) itemView
						.findViewById(R.id.tv_my_suppler_name);
				phone = (TextView) itemView
						.findViewById(R.id.tv_my_suppler_phone);
				address = (TextView) itemView
						.findViewById(R.id.tv_my_suppler_address);
			}

			public void bindData(MySupplierData supplierBean) {
				ImageUtils.setImage(supplierBean.getFImageUrl(), ivIcon, R.drawable.iv_login_defalut_icon, R.drawable.iv_login_defalut_icon, R.drawable.iv_login_defalut_icon);
				name.setText(supplierBean.getFName());
				phone.setText(supplierBean.getFPhone());
				address.setText(supplierBean.getFAddress());
			}
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		getSupplier();
	}
}
