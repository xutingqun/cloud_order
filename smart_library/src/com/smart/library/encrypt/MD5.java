package com.smart.library.encrypt;

import java.security.MessageDigest;

public class MD5 {

	private static String salt = "SMART_HR";
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public MD5() {

	}

	/**
	 * ���ַ�������MD5����
	 * 
	 * @param text
	 *            ����
	 * 
	 * @return ����
	 */
	public static String md5(String rawPass) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// ���ܺ���ַ���
			result = byteArrayToHexString(md.digest(mergePasswordAndSalt(
					rawPass).getBytes("utf-8")));
		} catch (Exception ex) {
		}
		return result;
	}

	public boolean isPasswordValid(String encPass, String rawPass) {
		String pass1 = "" + encPass;
		String pass2 = md5(rawPass);
		return pass1.equals(pass2);
	}

	private static String mergePasswordAndSalt(String password) {
		if (password == null) {
			password = "";
		}
		return password + "{" + salt + "}";

	}

	/**
	 * ת���ֽ�����Ϊ16�����ִ�
	 * 
	 * @param b
	 *            �ֽ�����
	 * @return 16�����ִ�
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static void main(String[] args) {
		MD5 encoderMd5 = new MD5();
		String ss = md5("350884441752125451");
		System.out.println(ss);
		boolean passwordValid = encoderMd5.isPasswordValid(
				"108b3d7f534708c297d5c034d4c34cb9", "test");
		System.out.println(passwordValid);
	}

}