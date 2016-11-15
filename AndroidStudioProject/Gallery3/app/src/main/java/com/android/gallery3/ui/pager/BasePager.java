package com.android.gallery3.ui.pager;

import android.app.Activity;
import android.view.View;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/15 16:10
 */

public abstract class BasePager {

    public Activity mActivity;
    public View mRootView;

    /**
     * 获得Activity
     */
   public BasePager(Activity activity){
       mActivity = activity;
       mRootView = initView();
   }


    /**
     * 初始化布局
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public abstract void initData();
}
