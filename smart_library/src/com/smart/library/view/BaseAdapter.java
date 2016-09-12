package com.smart.library.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.smart.library.util.Collections;

import android.view.ViewGroup;


public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

	protected final ViewGroup getGroup() {
		return null;
	}
	
	private T[] mData;
	
	public Object[] getData() { //不能返回 T[]，不然会崩溃
		return mData;
	}
	
	public List<T> getDataList() {
		if(mData == null) return null;
		return Arrays.asList(mData);
	}
	
	public void setDataNull() {
		mData = null;
		notifyDataSetChanged();
	}
	
	public void setData(T data, int position) {
		mData[position] = data;
		notifyDataSetChanged();
	}
	
	public void setData(T[] data) {
		mData = data;
		notifyDataSetChanged();
	}
	
	@SuppressWarnings("unchecked")
	public void setData(Collection<? extends T> data) {
		if(data == null){
			T[] d = null;
			setData(d);
		}else{
			setData((T[]) data.toArray());
		}
	}
	
	public void addFirst(T data) {
		List<T> tmp = new ArrayList<T>();
		tmp.add(data);
		addFirst(tmp);
	}
	
	@SuppressWarnings("unchecked")
	public void addFirst(Collection<? extends T> data) {
		if(data == null) return;
		addFirst((T[]) data.toArray());
	}
	
	@SuppressWarnings("unchecked")
	public void addFirst(T[] data) {
		if(data == null) return;
		setData(Collections.merge(data, mData));
	}
	
	@SuppressWarnings("unchecked")
	public void appendData(Collection<? extends T> data) {
		if(data == null) return;
		appendData((T[]) data.toArray());
	}
	
	@SuppressWarnings("unchecked")
	public void appendData(T[] data) {
		if(data == null) return;
		if(mData == null) {
			setData(data);
		} else {
			setData(Collections.merge(mData, data));
		}
	}
	
	public void removeData(T data) {
		setData(Collections.remove(mData, data));
	}
	
	public void removeData(int position) {
		setData(Collections.remove(mData, position));
	}
	
	@Override
	public int getCount() {
		return mData == null ? 0 : mData.length;
	}

	@Override
	public T getItem(int position) {
		return mData == null ? null : mData[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}