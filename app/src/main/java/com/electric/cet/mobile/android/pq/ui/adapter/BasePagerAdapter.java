package com.electric.cet.mobile.android.pq.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xushasha
 * use
 */
public class BasePagerAdapter extends PagerAdapter {
    private List<View> pages = null;

    public BasePagerAdapter(List<View> pages) {
        super();
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return ((pages == null) ? 0 : pages.size());
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(pages.get(position));
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        // TODO Auto-generated method stub
        super.finishUpdate(container);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ((ViewPager) container).addView(pages.get(position));
        return pages.get(position);
    }
}
