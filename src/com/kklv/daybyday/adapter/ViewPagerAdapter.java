package com.kklv.daybyday.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 引导页背景的适配器
 * @author lvzhendong
 * @email lvzhendong1993@gmail.com
 *
 */
public class ViewPagerAdapter extends PagerAdapter {
	private ArrayList<View> data;
	
	public ViewPagerAdapter(ArrayList<View> data){
		super();
		this.data=data;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(data.get(position));
		return data.get(position);
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(data.get(position));
	}
	
	
	

}
