package com.cloud.order.bean;

import java.util.ArrayList;
import java.util.List;

public class MySupplierBean {

	private String success = "";// 成功=1 失败=0
	private String Message = "";// 失败返回错误描述
	private List<MySupplierData>  result = new ArrayList<MySupplierData>();// 成功返回供应商列表json格式，失败返回null

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	

	public List<MySupplierData> getResult() {
		return result;
	}

	public void setResult(List<MySupplierData> result) {
		this.result = result;
	}



	public class MySupplierData {
	
		private String FName = "";//     string		供应商名称
		private String FSupplierID = "";// 		int		供应商ID，供删除供应商时使用
		private String FImageUrl = "";// 		string	图片url  
		private String FPhone = "";// 	string	电话
		private String FAddress = "";// 	string	地址
		public String getFName() {
			return FName;
		}
		public void setFName(String fName) {
			FName = fName;
		}
		public String getFSupplierID() {
			return FSupplierID;
		}
		public void setFSupplierID(String fSupplierID) {
			FSupplierID = fSupplierID;
		}
		public String getFImageUrl() {
			return FImageUrl;
		}
		public void setFImageUrl(String fImageUrl) {
			FImageUrl = fImageUrl;
		}
		public String getFPhone() {
			return FPhone;
		}
		public void setFPhone(String fPhone) {
			FPhone = fPhone;
		}
		public String getFAddress() {
			return FAddress;
		}
		public void setFAddress(String fAddress) {
			FAddress = fAddress;
		}
		
	}
}
