package com.cloud.order.api;

public class BaseAPI {

	public static final String URL1 ="http://www.sintoyu.net/xtyorgainfo";//�yԇ������
	public static final String URL2 ="http://www.sintoyu.cn/LoginPage";//�yԇ������
	/**
	 * ��ȡ��Ӧ���б�ӿ�1
	 */
	public static String getSupplierOne(String machineid) {

		return URL1 + "/getsupplierlist?machineid="+machineid;
	}
	/**
	 * ��ȡ��Ӧ���б�ӿ�2
	 */
	public static String getSupplierTwo(String machineid) {

		return URL2 + "/getsupplierlist?machineid="+machineid;
	}
}
