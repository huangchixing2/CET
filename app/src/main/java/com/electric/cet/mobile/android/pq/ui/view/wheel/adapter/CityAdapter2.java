package com.electric.cet.mobile.android.pq.ui.view.wheel.adapter;

import android.content.Context;

import java.util.List;


public class CityAdapter2 extends AbstractWheelTextAdapter{

	private List<String> list;

	public CityAdapter2(Context context, List<String> list) {
		super(context);
		this.list = list;
	}

	public void setData(List<String> list){
		this.list = list;
//		notifyDataChangedEvent();
		notifyDataInvalidatedEvent();
	}
	
	@Override
	public int getItemsCount() {
		return list == null ? 0 :list.size();
	}

	@Override
	protected CharSequence getItemText(int index) {
		if (index >= 0 && index < list.size()) {
			return list.get(index);
		}
		return null;
	}
	
}
