package com.android.gallery3.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description: 禁止滑动的ViewPager
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/15 17:28
 */

public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    //事件中断,拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //true表示拦截, false不拦截,传给子控件
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //重写父类onTouchEvent, 此处什么都不做, 从而达到禁用事件的目的
        return true;
    }
}
