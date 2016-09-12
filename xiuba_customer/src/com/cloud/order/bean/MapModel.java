package com.cloud.order.bean;

import com.amap.api.services.core.LatLonPoint;

public class MapModel {

	private String addressName = "addressName";
	private String addressDetails = "addressDetails";
	private String longitude = "longitude";
	private String latitude = "latitude";
	private boolean isVisible;// 是否打勾了
	private String distance;// 距离当前位置
	private String province = "longitude";// : "省",
	private String city = "";// : "市",
	private String area = "";// : "区",
	private String street = "";// : "街道",

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	@Override
	public String toString() {
		return "MapModel [addressName=" + addressName + ", addressDetails="
				+ addressDetails + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}

}
