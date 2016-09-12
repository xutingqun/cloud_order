package com.cloud.order.api;

public class BaseAPI {

	public static final String URL1 ="http://www.sintoyu.net/xtyorgainfo";//測試服务器
	public static final String URL2 ="http://www.sintoyu.cn/LoginPage";//測試服务器
	/**
	 * 获取供应商列表接口1
	 */
	public static String getSupplierOne(String machineid) {

		return URL1 + "/getsupplierlist?machineid="+machineid;
	}
	/**
	 * 获取供应商列表接口2
	 */
	public static String getSupplierTwo(String machineid) {

		return URL2 + "/getsupplierlist?machineid="+machineid;
	}
}
