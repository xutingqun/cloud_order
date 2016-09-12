package com.cloud.order.view.time.selector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.cloud.order.R;
import com.cloud.order.view.time.ArrayWheelAdapter;
import com.cloud.order.view.time.OnWheelScrollListener;
import com.cloud.order.view.time.WheelView;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;


public class Popwindow {

	private LayoutInflater inflater = null;
	private WheelView month;
	private WheelView day;
	private WheelView hour;
	private WheelView mins;
	private WheelView time;


	PopupWindow menuWindow;

	private Context mContext;
	private static final String[] m = { "立即上门","今天","明天","后天","大后天" };

	public Popwindow(LayoutInflater inflater, Context mContext) {
		super();
		this.inflater = inflater;
		this.mContext = mContext;
	}

	public void showPopwindow(View view) {
		menuWindow = new PopupWindow(view, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		menuWindow.setFocusable(true);
		menuWindow.setOutsideTouchable(true); // 点击外部关闭。
		menuWindow.setAnimationStyle(R.style.popwin_anim_style);	
		menuWindow.setBackgroundDrawable(new ColorDrawable());		
		menuWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		menuWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				menuWindow = null;
			}
		});
		
	}
	

	public View getDataPick() {
		

		Calendar c = Calendar.getInstance();
		final int curYear = c.get(Calendar.YEAR);
		int curMonth = c.get(Calendar.MONTH) + 1;
		int curDate = c.get(Calendar.DATE);
		int curHour = c.get(Calendar.HOUR);
		
		final View view = inflater.inflate(R.layout.it_time_selector, null);

		
		month = (WheelView) view.findViewById(R.id.wt_popwindow_month);
		month.setAdapter(new ArrayWheelAdapter(m, 5));
		month.setCyclic(false);
		month.setCurrentItem(0);

		
		String[] ss = getTime(8);
		time = (WheelView) view.findViewById(R.id.wt_popwindow_time);
		time.setAdapter(new ArrayWheelAdapter(ss, 24));
		time.setCyclic(false);
		time.setCurrentItem(0);
	
		return view;
	}

	OnWheelScrollListener scrollListener = new OnWheelScrollListener() {

		@Override
		public void onScrollingStarted(WheelView wheel) {

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
			// TODO Auto-generated method stub
					
		}
	};
	//8:30~20:30
	private String[] getTime(int num){
		int size = num -8;
		String[] s = new String[(13-size)*3];
		for (int i = 0; i <(13-size)*3; i=i+3) {
			s[i] = (num)+":00";
			s[i+1] = (num)+":20";
			s[i+2] = (num)+":40";	
			
			num = num +1;
		}
		
		return s;	
	}

	

}
