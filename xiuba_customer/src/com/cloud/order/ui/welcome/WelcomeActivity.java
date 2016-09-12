package com.cloud.order.ui.welcome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import cn.jpush.android.api.JPushInterface;

import com.cloud.order.R;
import com.cloud.order.ui.BaseActivity;
import com.cloud.order.ui.user.LoginActivity;
import com.smart.library.manager.AppManager;

/**
 * @author xutingqun
 */
@SuppressLint("ResourceAsColor")
public class WelcomeActivity extends BaseActivity {

	private final int READY = 1;
	private int mMis = 2;// ÃëÊý

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_welcome);
	//	SMSSDK.initSDK(this, "f2d02f25db39", "73d09662238745a34eb13c4e2dc84a1f");
		new Thread(mRun).start();
	}

	private Runnable mRun = new Runnable() {

		@Override
		public void run() {
			try {
				Thread.sleep(1000 * mMis);
				handler.sendEmptyMessage(READY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case READY:
				startActivity(new Intent(WelcomeActivity.this,
						LoginActivity.class));
				overridePendingTransition(R.anim.fade, R.anim.hold);
				AppManager.getAppManager().finishActivity(WelcomeActivity.this);
				break;
			}
		}
	};


}
