package com.cloud.order.bean;

import java.util.ArrayList;
import java.util.List;

public class SupplierBean {

	private String success = "";// �ɹ�=1 ʧ��=0
	private String Message = "";// ʧ�ܷ��ش�������
	private  List<LoginResultData> result = new ArrayList<LoginResultData>();// �ɹ����ع�Ӧ���б�json��ʽ��ʧ�ܷ���null

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
		private String FSupplierID = "";// string ��Ӧ��ID
		private String FImageUrl = "";// string ͼƬurl
		private String FUserName = "";// string �����½���û���
		private String FPwd = "";// string �����½����
		private String FServerAddress = "";// string ����������ס�õ�ַ��������APPȫ��ʹ�� ����

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
