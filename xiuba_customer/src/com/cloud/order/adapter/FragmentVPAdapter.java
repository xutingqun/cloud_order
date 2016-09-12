package com.cloud.order.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
/**
 * ×îÍâ²ãviewpageµÄÊÊÅäÆ÷
 * @author wudh
 *
 */
public class FragmentVPAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> mFragments;
	private FragmentManager fm;

	public FragmentVPAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
		super(fm);
		this.fm = fm;
		this.mFragments = fragments;
	}

	public void setFragments(ArrayList<Fragment> fragments) {
		if (this.mFragments != null) {
			FragmentTransaction ft = fm.beginTransaction();
			for (Fragment f : this.mFragments) {
				ft.remove(f);
			}
			ft.commit();
			ft = null;
			fm.executePendingTransactions();
		}
		this.mFragments = fragments;
		notifyDataSetChanged();
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public Fragment getItem(int arg0) {
		return mFragments.get(arg0);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}
}