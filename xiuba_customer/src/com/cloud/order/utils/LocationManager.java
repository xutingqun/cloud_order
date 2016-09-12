package com.cloud.order.utils;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.cloud.order.bean.MapModel;

public class LocationManager implements AMapLocationListener {
	private Context mContext;
	private MyLocationListener mListener;
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;

	public LocationManager(Context context) {
		this.mContext = context;
	}

	public void location(MyLocationListener listener) {
		this.mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(mContext);
			mLocationOption = new AMapLocationClientOption();
			// ���ö�λ����
			mlocationClient.setLocationListener(this);
			// ����Ϊ�߾��ȶ�λģʽ
			mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
			// �����Ƿ�����ģ��λ��,Ĭ��Ϊfalse��������ģ��λ��
			mLocationOption.setMockEnable(false);
			// �����Ƿ�ֻ��λһ��,Ĭ��Ϊfalse
			mLocationOption.setOnceLocation(true);
			// ���ö�λ���,��λ����,Ĭ��Ϊ2000ms
			mLocationOption.setInterval(10000000);

			// ���ö�λ����
			mlocationClient.setLocationOption(mLocationOption);

			// �˷���Ϊÿ���̶�ʱ��ᷢ��һ�ζ�λ����Ϊ�˼��ٵ������Ļ������������ģ�
			// ע�����ú��ʵĶ�λʱ��ļ������С���֧��Ϊ2000ms���������ں���ʱ�����stopLocation()������ȡ����λ����
			// �ڶ�λ�������ں��ʵ��������ڵ���onDestroy()����
			// �ڵ��ζ�λ����£���λ���۳ɹ���񣬶��������stopLocation()�����Ƴ����󣬶�λsdk�ڲ����Ƴ�
			mlocationClient.startLocation();
		}
	}

	public void destory() {
		if (mlocationClient != null) {
			mlocationClient.onDestroy();
			mlocationClient = null;
		}
	}

	@Override
	public void onLocationChanged(AMapLocation location) {
		if (mListener == null)
			return;

		MapModel zone = new MapModel();
		zone.setAddressName(location.getAddress());
		zone.setAddressDetails(location.getAddress());
		zone.setLatitude(location.getLatitude() + "");
		zone.setLongitude(location.getLongitude() + "");
		if (location != null && location.getErrorCode() == 0) {
			mListener.onLocation(zone);
		} else {
			// Toast.makeText(mContext, "定位错误~" +
			// location.getAMapException().getErrorCode(), Toast.LENGTH_LONG)
			// .show();
			// CheckNet.showToast("定位失败，请�?��定位权限或网络情�?, mContext);
			mListener.onLocationFailure();
		}
	}

	public interface MyLocationListener {
		public void onLocation(MapModel zone);

		public void onLocationFailure();
	}
}