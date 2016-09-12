package com.cloud.order.ui.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.Header;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.cloud.order.R;
import com.cloud.order.ui.BaseActivity;
import com.cloud.order.view.RollHeaderView;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.slidingmenu.lib.SlidingMenu;
import com.smart.library.okhttp.OkHttpClientManager;
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
	private RollHeaderView banner;
	private String business;
	private String strbanner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setBarColor(R.color.orange);

		setContentView(R.layout.ac_mainn);
		
	
		
		initView();


	}

	private void initView() {

		
		
		banner = (RollHeaderView) findViewById(R.id.banner);
		banner.setOnHeaderViewClickListener(new RollHeaderView.HeaderViewClickListener() {
			@Override
			public void HeaderViewClick(int position) {
//				Toast.makeText(MainActivity.this, "锟斤拷锟斤拷锟斤拷 : " + position,
//						Toast.LENGTH_SHORT).show();
			}
		});
		
		

		getSpStorage();
	

	}

	/**
	 * 锟斤拷取锟斤拷锟芥储锟斤拷锟斤拷
	 * */
	private void getSpStorage() {

		// 锟斤拷取业锟斤拷锟斤拷锟酵存储锟斤拷锟斤拷
		business = (String) SharedPreferencesUtils.getParam(this,
				LoginActivity.class.getSimpleName() + "business", "");

		if (!business.equals("")) {
//			gsonbusiness(business);
			Log.e("------", "锟接憋拷锟截讹拷取");
		}
		// 锟斤拷取 锟街诧拷锟芥储锟斤拷锟斤拷
		strbanner = (String) SharedPreferencesUtils.getParam(this,
				LoginActivity.class.getSimpleName() + "banner", "");

		if (!strbanner.equals("")) {
			// gsonbanner(strbanner);
			Log.e("------", "锟接憋拷锟截讹拷取");
		}
	}

//	/**
//	 * 锟斤拷取 banner 锟斤拷锟� 图片
//	 * */
//	private void loadBannerData() {
//
//	
//		
//		OkHttpClientManager.getAsyn(BaseAPI.getadvertisements(),
//
//		new OkHttpClientManager.ResultCallback<String>() {
//			@Override
//			public void onError(Request request, Exception e) {
//				
//				ToastUtil.showToast(MainActivity.this, R.string.toast_connect_to_server_failed);
//				view_empty.setErrorType(EmptyLayout.NETWORK_ERROR);
//				
//			}
//
//			@Override
//			public void onResponse(String response) {
//				view_empty.setVisibility(View.GONE);
//				
//				
//				gsonbanner(response);
//
//			}
//		});
//
//	}
//
//	/**
//	 * 锟斤拷取 业锟斤拷锟斤拷锟斤拷锟叫憋拷
//	 * */
//	private void loadbusinessData() {
//		
////		 DialogUtil.showRoundProcessDialog(this, "锟斤拷锟斤拷锟斤拷...");
//		 
//		OkHttpClientManager.getAsyn(BaseAPI.getlistbusiness(),
//				new OkHttpClientManager.ResultCallback<String>() {
//					@Override
//					public void onError(Request request, Exception e) {
//						
////						 DialogUtil.closeRoundProcessDialog();
//						ToastUtil.showToast(MainActivity.this, R.string.toast_connect_to_server_failed);
//						
//					}
//
//					@Override
//					public void onResponse(String response) {
//						
////						 DialogUtil.closeRoundProcessDialog();
//						 gsonbusiness(response);
//						 
//					}
//				});
//
//	}
//
//	/**
//	 * 锟斤拷锟斤拷 业锟斤拷锟斤拷锟斤拷锟叫憋拷
//	 * */
//	private void gsonbusiness(String response) {
//
//		Gson gson = new Gson();
//		BusinessBean businessBean = gson.fromJson(response, BusinessBean.class);
//		if (businessBean.getSuccess()) {
//			if (!business.equals(response)) {
//				// 锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟芥储
//				SharedPreferencesUtils.setParam(LoginActivity.this,
//						LoginActivity.class.getSimpleName() + "business",
//						response);
//			}
//
//			data = businessBean
//					.getData();
//
//			mMainAdapter = new MainAdapter(data, LoginActivity.this, iconGv);
//			iconGv.setAdapter(mMainAdapter);
//
//			mMainAdapter.notifyDataSetChanged();
//
//		} else {
//			ToastUtil
//					.showToast(LoginActivity.this, R.string.toast_content_errpr);
//		}
//	}
//
//	/**
//	 * 锟斤拷锟斤拷 锟街诧拷
//	 * */
//	private void gsonbanner(String strban) {
//		Gson gson = new Gson();
//		BannerModel bannerModel = gson.fromJson(strban, BannerModel.class);
//		if (bannerModel.getSuccess()) {
//			
//			view_empty.setErrorType(EmptyLayout.HIDE_LAYOUT);
//
//			if (!strbanner.equals("strban")) {// 锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟芥储
//				SharedPreferencesUtils.setParam(LoginActivity.this,
//						LoginActivity.class.getSimpleName() + "banner", strban);
//			}
//
//			List<Data> data = bannerModel.getData();
//
//			List<String> imgUrlList = new ArrayList<String>();
//
//			for (int i = 0; i < data.size(); i++) {
//				String imagurl = BaseAPI.URL + data.get(i).getImagePath()
//						+ data.get(i).getImage();
//				imgUrlList.add(imagurl);
//			}
//			banner.setImgUrlData(imgUrlList);// banner锟斤拷锟斤拷图片锟斤拷示
//			
//		} else {
//			
//			view_empty.setErrorType(EmptyLayout.NODATA);
//			
//			ToastUtil
//					.showToast(LoginActivity.this, 
//							R.string.toast_content_errpr);
//			
//		}
//
//	}
//
//	/**
//	 * 锟斤拷锟斤拷头锟斤拷锟斤拷锟斤拷锟斤拷
//	 */
//	private void initTopView() {
//
//		TopUtil.setTopBackground(this, R.color.orange);
//		TopUtil.setViewVisiable(this, 1, 0, 1, 0, 1, 0);
//		TopUtil.setLeftImage(this, R.drawable.btn_top_ico);
//		TopUtil.setCenterImage(this, R.drawable.main_top_ico);
//		TopUtil.setRightImage(this, R.drawable.btn_top_set);
//
//	}
//
//	/**
//	 * 锟斤拷锟斤拷锟斤拷锟剿碉拷 8-16取锟斤拷锟斤拷
//	 */
//	private void initSlidingMenu() {
//
//		menu = new SlidingMenu(this);
//		menu.setMode(SlidingMenu.LEFT);
//		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//		menu.setShadowWidthRes(R.dimen.shadow_width);
//
//		if (Constant.ISLOGIN)
//			menu.setBehindOffset(getWindowManager().getDefaultDisplay()
//					.getWidth() / 4);
//		else
//			menu.setBehindOffset(getWindowManager().getDefaultDisplay()
//					.getWidth());
//
//		menu.setFadeDegree(0.5f);
//
//		// 锟窖伙拷锟斤拷锟剿碉拷锟斤拷咏锟斤拷锟斤拷械锟紸ctivity锟叫ｏ拷锟斤拷选值SLIDING_CONTENT 锟斤拷 SLIDING_WINDOW
//		if (Constant.ISLOGIN && menu != null) {
//			menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT); // 影锟斤拷banner锟斤拷锟斤拷
//		}
//
//		View view = LayoutInflater.from(this).inflate(R.layout.act_personnal_edit,
//				null);
//		menu.setMenu(view);
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.iv_top_more:
//			if (DataStorage.isLogin(this)) {
//				startActivity(new Intent(LoginActivity.this,
//						SettingsActivity.class));
//			} else {
//				startActivity(new Intent(LoginActivity.this, LoginActivity.class));
//			}
//			break;
//
//		default:
//			break;
//		}
//	}
//
//	@Override
//	public void BackSharingClick(View view) {
//		if (DataStorage.isLogin(this)) {
//			// if (Constant.ISLOGIN && menu != null) {// 锟斤拷
//			// menu.showMenu();
//			startActivity(new Intent(LoginActivity.this, PersonalActivity.class));
//		} else
//			startActivity(new Intent(LoginActivity.this, LoginActivity.class));
//	}
//
//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position,
//			long id) {
//		
//		
//		
//		if (DataStorage.isLogin(this)) {
//			
//			Bundle bundle = new Bundle();
//			bundle.putString("businessId", data.get(position).getId()+"");
//			IntentUtil.mStartActivityWithBundle(LoginActivity.this, 
//					OrderApplyActivity.class,bundle);
//			
//		} else startActivity(new Intent(LoginActivity.this, LoginActivity.class));
//		
//
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//
//		// if (Constant.ISLOGIN && menu != null) //锟斤拷
//		// menu.setBehindOffset(getWindowManager().getDefaultDisplay()
//		// .getWidth() / 4);
//	}

}
