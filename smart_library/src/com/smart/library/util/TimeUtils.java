package com.smart.library.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.text.TextUtils;

public class TimeUtils {
	/** ������ʱ�����ʽ */
	public static final String TIME_FORMAT_HAODAI_YMDHMS = "yyyyMMddHHmmss";
	/** ��-��-�� ʱ:�ֵĸ�ʽ */
	public static final String TIME_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
	/** ��-��-�� �ĸ�ʽ(�����4λ) */
	public static final String TIME_FORMAT_YMD = "yyyy-MM-dd";
	/** ��-��-�� �ĸ�ʽ(���ֻ��2λ) */
	public static final String TIME_FORMAT_yMD = "yy-MM-dd";
	/** ��-�� ʱ:�ֵĸ�ʽ */
	public static final String TIME_FORMAT_MDHM = "MM-dd HH:mm";
	/** ʱ:�� ��ʽ */
	public static final String TIME_FORMAT_HM = "HH:mm";
	/** ������ ʱ:�ָ�ʽ */
	public static final String TIME_FORMAT_YMD_HM = "yyyy��MM��dd�� HH:mm";

	/** 1���� */
	private static final int ONE_MIUNUTE = 60;
	/** 1Сʱ */
	private static final int ONE_HOUR = ONE_MIUNUTE * 60;
	/** 24Сʱ */
	private static final int ONE_DAY = ONE_HOUR * 24;

	public static int getCurrentTimeStamp() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	//��ȡ��ǰʱ��
	public static String getSystemTimeDetail() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��

		String str = formatter.format(curDate);
		return str;

	}
	//��ȡ��ǰʱ��
		public static String getSystemTimeNoSecond() {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��

			String str = formatter.format(curDate);
			return str;

		}
	/**
	 * @describe:����timeFormat��ȡ��ǰ��ʱ��
	 * @return ��ǰʱ���ַ���
	 */
	public static String getCurrentTime(String timeFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.CHINA);
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		return sdf.format(curDate);
	}

	/**
	 * ��ʱ���תΪ�ַ���
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
	 * ��ʱ���תΪ�ַ���
	 * 
	 * @param timeStamp
	 * @param timeFormat
	 * @return
	 */
	public static String getStrTime(long timeStamp, String timeFormat) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.CHINA);
		// ���磺cc_time=1291778220
		re_StrTime = sdf.format(new Date(timeStamp * 1000L));
		return re_StrTime;
	}

	/**
	 * ��Ϣ �����¼���ʾ���� ������-��-�� ʱ:�ֵĸ�ʽ
	 */
	public static final String getSmartMessaTimeRules(long currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT_YMDHM);
		long time = currentTime * 1000;
		Date date = new Date(time);
		return formatter.format(date);
	}

	/**
	 * ����ʱ������ڵ�Сʱ
	 * 
	 * @param time
	 *            ʱ�������λ����
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
	 * ��ȡ�������͵� ʱ���֡�������
	 * 
	 * @param timestamp
	 * @return [0]ʱ��[1]�֡�[2]��
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