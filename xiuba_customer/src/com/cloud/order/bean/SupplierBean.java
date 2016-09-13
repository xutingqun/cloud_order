package com.cloud.order.bean;

import java.util.ArrayList;
import java.util.List;

public class SupplierBean {

	private String success = "";// 成功=1 失败=0
	private String Message = "";// 失败返回错误描述
	private  List<LoginResultData> result = new ArrayList<LoginResultData>();// 成功返回供应商列表json格式，失败返回null

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

	

	public List<LoginResultData> getResult() {
		return result;
	}

	public void setResult(List<LoginResultData> result) {
		this.result = result;
	}



	public class LoginResultData {
		private String FSupplierID = "";// string 供应商ID
		private String FImageUrl = "";// string 图片url
		private String FUserName = "";// string 最近登陆的用户名
		private String FPwd = "";// string 最近登陆密码
		private String FServerAddress = "";// string 服务器，记住该地址并供整个APP全局使用 解密

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

		public String getFUserName() {
			return FUserName;
		}

		public void setFUserName(String fUserName) {
			FUserName = fUserName;
		}

		public String getFPwd() {
			return FPwd;
		}

		public void setFPwd(String fPwd) {
			FPwd = fPwd;
		}

		public String getFServerAddress() {
			return FServerAddress;
		}

		public void setFServerAddress(String fServerAddress) {
			FServerAddress = fServerAddress;
		}

	}
}
