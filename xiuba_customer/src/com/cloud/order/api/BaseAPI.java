package com.cloud.order.api;

public class BaseAPI {

	// public static final String URL = "http://127.0.0.1:8080/xx_client";
	public static final String URL ="http://192.168.2.213:8080/xx_client";//�yԇ������
	// public static final String URL = "http://192.168.2.42:8080/xx_client";// ���ز���

	/*
	 * ��ҳ �ֲ�ͼ
	 */
	public static String getadvertisements() {

		return BaseAPI.URL + "/advert/list-advertisements.htm";
	}

	/**
	 * ��ҳ ҵ������
	 */
	public static String getlistbusiness() {

		return BaseAPI.URL + "/system/list-business_type.htm";
	}

}
