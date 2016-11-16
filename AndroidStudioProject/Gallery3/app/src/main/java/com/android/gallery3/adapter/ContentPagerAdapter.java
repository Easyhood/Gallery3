package com.android.gallery3.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.android.gallery3.ui.pager.BasePager;

import java.util.ArrayList;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/16 14:01
 */

public class ContentPagerAdapter extends PagerAdapter {
    private ArrayList<BasePager> mlist;
    public ContentPagerAdapter(ArrayList<BasePager> arrayList) {
        super();
        this.mlist = arrayList;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager pager = mlist.get(position);
        container.addView(pager.mRootView);
        return pager.mRootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
