package com.cloud.order.bean;

import java.util.ArrayList;
import java.util.List;

public class MySupplierBean {

	private String success = "";// �ɹ�=1 ʧ��=0
	private String Message = "";// ʧ�ܷ��ش�������
	private List<MySupplierData>  result = new ArrayList<MySupplierData>();// �ɹ����ع�Ӧ���б�json��ʽ��ʧ�ܷ���null

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
	
		private String FName = "";//     string		��Ӧ������
		private String FSupplierID = "";// 		int		��Ӧ��ID����ɾ����Ӧ��ʱʹ��
		private String FImageUrl = "";// 		string	ͼƬurl  
		private String FPhone = "";// 	string	�绰
		private String FAddress = "";// 	string	��ַ
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
