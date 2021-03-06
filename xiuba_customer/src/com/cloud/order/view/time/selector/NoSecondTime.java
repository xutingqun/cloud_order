package com.cloud.order.view.time.selector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.cloud.order.R;
import com.cloud.order.utils.ColorUtil;
import com.cloud.order.view.time.NumericWheelAdapter;
import com.cloud.order.view.time.OnWheelScrollListener;
import com.cloud.order.view.time.WheelView;
import com.smart.library.util.TimeUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;


public class NoSecondTime {

	private LayoutInflater inflater = null;
	private RelativeLayout rlBg;
	private WheelView year;
	private WheelView month;
	private WheelView day;
	private WheelView hour;
	private WheelView mins;
	private WheelView time;

	private TextView tvNow;

	PopupWindow menuWindow;

	private Context mContext;


	public NoSecondTime(LayoutInflater inflater, Context mContext, TextView tvNow) {
		super();
		this.inflater = inflater;
		this.mContext = mContext;
		this.tvNow = tvNow;	 
	}

	public void showPopwindow(View view) {
		menuWindow = new PopupWindow(view, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
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
		final View view = inflater.inflate(R.layout.popwindow_task_time, null);
	
		rlBg = (RelativeLayout) view.findViewById(R.id.rl_task_popwindow_bg);
		
		rlBg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				menuWindow.dismiss();
			}
		});
		
		String taskTime = tvNow.getText().toString().trim();
		if(taskTime.equals("")){
			taskTime = TimeUtils.getSystemTimeNoSecond();
		}
		String mYear = taskTime.split("-")[0];
		int iYear = Integer.parseInt(mYear);
		String mMonth = taskTime.split("-")[1];
		int iMonth = Integer.parseInt(mMonth);
		String mDayAndHour = taskTime.split("-")[2];
		String mDay = mDayAndHour.split(" ")[0];
		int iDay= Integer.parseInt(mDay);
		String mHourAndMin = mDayAndHour.split(" ")[1];
		String mHour = mHourAndMin.split(":")[0];
		int iHour = Integer.parseInt(mHour);
		String mMin =  mHourAndMin.split(":")[1];
		int iMin = Integer.parseInt(mMin);
		
		
		System.out.println(mYear+"年-"+mMonth+"月-"+mDay+"日"+mHour+"时"+mMin+"分");
		year = (WheelView) view.findViewById(R.id.wt_task_popwindow_year);
		year.setAdapter(new NumericWheelAdapter(curYear, curYear));
		year.setLabel("年");
		year.setCyclic(false);
		year.addScrollingListener(scrollListener);

		month = (WheelView) view.findViewById(R.id.wt_task_popwindow_month);
		month.setAdapter(new NumericWheelAdapter(1, 12));
		month.setLabel("月");
		month.setCyclic(true);
		month.addScrollingListener(scrollListener);

		day = (WheelView) view.findViewById(R.id.wt_task_popwindow_day);
		initDay(curYear, curMonth);
		day.setLabel("日");
		day.setCyclic(true);

		/*
		 * year.setCurrentItem(curYear - 1950); month.setCurrentItem(curMonth -
		 * 1); day.setCurrentItem(curDate - 1);
		 */
		year.setCurrentItem(iYear - curYear);
		month.setCurrentItem(iMonth - 1);
		day.setCurrentItem(iDay - 1);

		hour = (WheelView) view.findViewById(R.id.wt_task_popwindow_hour);
		hour.setAdapter(new NumericWheelAdapter(1, 24));
		hour.setCyclic(false);
		hour.setLabel("时");
		hour.addScrollingListener(scrollListener);	
		hour.setCurrentItem(iHour-1);
		
		mins = (WheelView) view.findViewById(R.id.wt_task_popwindow_min);
		mins.setAdapter(new NumericWheelAdapter(0, 59));
		mins.setCyclic(false);
		mins.setLabel("分");
		mins.addScrollingListener(scrollListener);	
		mins.setCurrentItem(iMin);


		Button bt = (Button) view.findViewById(R.id.bt_popwindow_set);
		ColorUtil.SetButtonBg(bt,(Activity)mContext,R.color.transler,R.color.shallow_gray);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String mYear = TimeUtils.getSystemTimeNoSecond().split("-")[0];
				 int iYear = Integer.parseInt(mYear);
				int n_year = year.getCurrentItem() + iYear;
				
				int n_month = month.getCurrentItem() + 1;
				String m = ""+n_month;
				if(n_month<10){
				   m = "0"+n_month;
				}
				int n_day = day.getCurrentItem()+1;
				String d = ""+n_day;
				if(n_day<10){
					d = "0"+n_day;
				}
				int n_hour = hour.getCurrentItem()+1;
				String h = ""+n_hour;
				if(n_hour<10){
					h = "0"+n_hour;
				}
				int n_min = mins.getCurrentItem();
				String mi = ""+n_min;
				if(n_min<10){
					 mi = "0"+n_min;
				}
				
				NoSecondTime.this.tvNow.setText(n_year+"-"+m+"-"+d+" "+h+":"+mi);

				menuWindow.dismiss();
			}
		});
		Button cancel = (Button) view.findViewById(R.id.bt_popwindow_cancel);
		ColorUtil.SetButtonBg(cancel,(Activity)mContext,R.color.transler,R.color.shallow_gray);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				menuWindow.dismiss();
			}
		});

		return view;
	}

	OnWheelScrollListener scrollListener = new OnWheelScrollListener() {

		@Override
		public void onScrollingStarted(WheelView wheel) {

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
			// TODO Auto-generated method stub
			int n_year = year.getCurrentItem() + 1950;
			int n_month = month.getCurrentItem() + 1;
			initDay(n_year, n_month);
			int num = getDay(n_year, n_month);
			if (num < day.getCurrentItem() + 1) {
				day.setCurrentItem(num - 1);

			}

		}
	};

	private int getDay(int year, int month) {
		int day = 30;
		boolean flag = false;
		switch (year % 4) {
		case 0:
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 2:
			day = flag ? 29 : 28;
			break;
		default:
			day = 30;
			break;
		}
		return day;
	}


	private void initDay(int arg1, int arg2) {
		day.setAdapter(new NumericWheelAdapter(1, getDay(arg1, arg2), "%02d"));
	}


}
