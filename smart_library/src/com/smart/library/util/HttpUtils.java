package com.smart.library.util;

import org.apache.http.Header;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class HttpUtils {
	private static AsyncHttpClient client = new AsyncHttpClient(); // ʵ��������

	static {
		client.setTimeout(10000); // �������ӳ�ʱ����������ã�Ĭ��Ϊ10s
	}

	public static void get(String urlString, AsyncHttpResponseHandler res) // ��һ������url��ȡһ��string����
	{
		client.get(urlString, res);
	}

	public static void get(String urlString, TextHttpResponseHandler res) // ��һ������url��ȡһ��string����
	{
		client.get(urlString, res);
	}

	public static void get(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) // url���������
	{
		client.get(urlString, params, res);
	}

	public static void get(String urlString, JsonHttpResponseHandler res) // ������������ȡjson�����������
	{
		client.get(urlString, res);
	}

	public static void get(String urlString, RequestParams params,
			JsonHttpResponseHandler res) // ����������ȡjson�����������
	{
		client.get(urlString, params, res);
	}

	public static void get(String uString, BinaryHttpResponseHandler bHandler) // ��������ʹ�ã��᷵��byte����
	{
		client.get(uString, bHandler);
	}

	public static AsyncHttpClient getClient() {
		return client;
	}
	
	public static void post(String urlString,RequestParams params, AsyncHttpResponseHandler res) // ��һ������url��ȡһ��string����
	{
		client.post(urlString,params,res);
	}
	
	/**
	 * ��ȡ����ͼƬ
	 * @param url ͼƬ·��
	 * @param image Ҫ���õ�imageview
	 */
	public static void voidSetImage(String url,final ImageView image){
		
		HttpUtils.get(url, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				// TODO Auto-generated method stub
				 if(statusCode==200){  
                     BitmapFactory factory=new BitmapFactory();  
                     Bitmap bitmap=factory.decodeByteArray(responseBody, 0, responseBody.length);  
                     image.setImageBitmap(bitmap);  
                 }  
			}
			
			 public void onFailure(int statusCode, Header[] headers,  
                     byte[] responseBody, Throwable error) {  
                 error.printStackTrace();  
             }  
			 
			 @Override
			public void onFinish() {
				// TODO Auto-generated method stub
				System.out.println("�����������");
			}
		});
		
	}

}
