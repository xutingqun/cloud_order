package com.smart.library.util;

import java.util.Arrays;

import android.annotation.SuppressLint;
import android.text.TextUtils;

@SuppressLint("NewApi")
public final class Collections {

	/**
	 * 将多个数组的内容合并在一个数组中
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static <T> T[] merge(T[] array1, T[]... array2) {
		if(array2 == null) return array1;
		
		int size = array1.length;
		int arraySize = array2.length;
		for(int i = 0;i < arraySize;i++ ) {
			if(array2[i] == null) continue;
			size += array2[i].length;
		}
		
		T[] d = Arrays.copyOf(array1, size);
		size = array1.length;
		int size2;
		for(int i = 0;i < arraySize;i++ ) {
			if(array2[i] == null) continue;
			size2 = array2[i].length;
			System.arraycopy(array2[i], 0, d, size, size2);
			size += size2;
		}
		return d;
	}
	
	/**
	 * 将多个内容合并在一个数组中
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static <T> T[] merge(T[] array1, T... array2) {
		if(array2 == null) return array1;
		
		int size = array1.length;
		int arraySize = array2.length;
		
		T[] d = Arrays.copyOf(array1, size + arraySize);
		System.arraycopy(array2, 0, d, size, arraySize);
		return d;
	}
	
	public static int[] merge(int[] array1, int[]... array2) {
		if(array2 == null) return array1;
		
		int size = array1.length;
		int arraySize = array2.length;
		for(int i = 0;i < arraySize;i++ ) {
			if(array2[i] == null) continue;
			size += array2[i].length;
		}
		
		int[] d = Arrays.copyOf(array1, size);
		size = array1.length;
		int size2;
		for(int i = 0;i < arraySize;i++ ) {
			if(array2[i] == null) continue;
			size2 = array2[i].length;
			System.arraycopy(array2[i], 0, d, size, size2);
			size += size2;
		}
		return d;
	}

	/**
	 * 将多个内容合并在一个数组中
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static int[] merge(int[] array1, int... array2) {
		if(array2 == null) return array1;
		
		int size = array1.length;
		int arraySize = array2.length;
		
		int[] d = Arrays.copyOf(array1, size + arraySize);
		System.arraycopy(array2, 0, d, size, arraySize);
		return d;
	}
	
	public static <T> T[] remove(T[] array, T data) {
		if(array == null) return null;
		
		int size = array.length;
		boolean found = false;
		for(int i = 0;i < size;i++) {
			if(array[i] == data) {
				found = true;
			}
			if(found && (i + 1) < size) {
				array[i] = array[i + 1];
			}
		}
		T[] d = Arrays.copyOf(array, size - 1);
		return d;
	}
	
	public static <T> T[] remove(T[] array, int position) {
		if(array == null) return null;
		
		int size = array.length;
		boolean found = false;
		for(int i = 0;i < size;i++) {
			if(i == position) {
				found = true;
			}
			if(found && (i + 1) < size) {
				array[i] = array[i + 1];
			}
		}
		T[] d = Arrays.copyOf(array, size - 1);
		return d;
	}
	
	/**
	 * 将 “[arg0,arg1,arg2,...]”这个格式的数据串解析为数组
	 * @param string
	 * @return
	 */
	public static String[] toArray(String string) {
		if(TextUtils.isEmpty(string)) return null;
		if("null".equals(string) || "[]".equals(string)) return null;
		
		return string.substring(1, string.length() - 1).split(",");
	}
}
