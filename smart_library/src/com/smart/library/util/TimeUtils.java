package com.smart.library.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.text.TextUtils;

public class TimeUtils {
	/** 年月日时分秒格式 */
	public static final String TIME_FORMAT_HAODAI_YMDHMS = "yyyyMMddHHmmss";
	/** 年-月-日 时:分的格式 */
	public static final String TIME_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
	/** 年-月-日 的格式(年份有4位) */
	public static final String TIME_FORMAT_YMD = "yyyy-MM-dd";
	/** 年-月-日 的格式(年份只有2位) */
	public static final String TIME_FORMAT_yMD = "yy-MM-dd";
	/** 月-日 时:分的格式 */
	public static final String TIME_FORMAT_MDHM = "MM-dd HH:mm";
	/** 时:分 格式 */
	public static final String TIME_FORMAT_HM = "HH:mm";
	/** 年月日 时:分格式 */
	public static final String TIME_FORMAT_YMD_HM = "yyyy年MM月dd日 HH:mm";

	/** 1分钟 */
	private static final int ONE_MIUNUTE = 60;
	/** 1小时 */
	private static final int ONE_HOUR = ONE_MIUNUTE * 60;
	/** 24小时 */
	private static final int ONE_DAY = ONE_HOUR * 24;

	public static int getCurrentTimeStamp() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	//获取当前时间
	public static String getSystemTimeDetail() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间

		String str = formatter.format(curDate);
		return str;

	}
	//获取当前时间
		public static String getSystemTimeNoSecond() {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间

			String str = formatter.format(curDate);
			return str;

		}
	/**
	 * @describe:根据timeFormat获取当前的时间
	 * @return 当前时间字符串
	 */
	public static String getCurrentTime(String timeFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.CHINA);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return sdf.format(curDate);
	}

	/**
	 * 将时间戳转为字符串
	 * 
	 * @param timeStamp
	 * @param timeFormat
	 * @return
	 */
	public static String getStrTime(String timeStamp, String timeFormat) {
		if (TextUtils.isEmpty(timeStamp) || TextUtils.isEmpty(timeFormat)) {
			return null;
		}
		return getStrTime(Long.valueOf(timeStamp), timeFormat);
	}

	/**
	 * 将时间戳转为字符串
	 * 
	 * @param timeStamp
	 * @param timeFormat
	 * @return
	 */
	public static String getStrTime(long timeStamp, String timeFormat) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.CHINA);
		// 例如：cc_time=1291778220
		re_StrTime = sdf.format(new Date(timeStamp * 1000L));
		return re_StrTime;
	}

	/**
	 * 消息 中心事件显示规则 返回年-月-日 时:分的格式
	 */
	public static final String getSmartMessaTimeRules(long currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT_YMDHM);
		long time = currentTime * 1000;
		Date date = new Date(time);
		return formatter.format(date);
	}

	/**
	 * 返回时间戳所在的小时
	 * 
	 * @param time
	 *            时间戳，单位毫秒
	 * @return
	 */
	public static int getHourOfDay(long time) {
		Date date = new Date(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	/**
	 * 获取数据类型的 时、分、秒数据
	 * 
	 * @param timestamp
	 * @return [0]时、[1]分、[2]秒
	 */
	public static final int[] getHourMinuteSecond(int timestamp) {
		if (timestamp > 0) {
			int hour = timestamp / ONE_HOUR;
			int minute = timestamp % ONE_HOUR / ONE_MIUNUTE;
			int second = timestamp % ONE_HOUR % ONE_MIUNUTE;
			int[] i = new int[3];
			i[0] = hour;
			i[1] = minute;
			i[2] = second;
			return i;
		}
		return null;
	}

	/**
	 * @param timestamp
	 * @return
	 */
	public static final Map<String, Long> getBuyOrSellListTime(long timestamp) {
		if (timestamp > 0) {
			long hour = timestamp / ONE_HOUR;
			long minute = timestamp % ONE_HOUR / ONE_MIUNUTE;
			Map<String, Long> times = new HashMap<String, Long>();
			times.put("hour", hour);
			times.put("minute", minute);
			return times;
		}
		return null;
	}
}