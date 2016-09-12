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
	private static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象

	static {
		client.setTimeout(10000); // 设置链接超时，如果不设置，默认为10s
	}

	public static void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
	{
		client.get(urlString, res);
	}

	public static void get(String urlString, TextHttpResponseHandler res) // 用一个完整url获取一个string对象
	{
		client.get(urlString, res);
	}

	public static void get(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) // url里面带参数
	{
		client.get(urlString, params, res);
	}

	public static void get(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组
	{
		client.get(urlString, res);
	}

	public static void get(String urlString, RequestParams params,
			JsonHttpResponseHandler res) // 带参数，获取json对象或者数组
	{
		client.get(urlString, params, res);
	}

	public static void get(String uString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据
	{
		client.get(uString, bHandler);
	}

	public static AsyncHttpClient getClient() {
		return client;
	}
	
	public static void post(String urlString,RequestParams params, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
	{
		client.post(urlString,params,res);
	}
	
	/**
	 * 获取网络图片
	 * @param url 图片路径
	 * @param image 要设置的imageview
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
				System.out.println("访问网络结束");
			}
		});
		
	}

}
