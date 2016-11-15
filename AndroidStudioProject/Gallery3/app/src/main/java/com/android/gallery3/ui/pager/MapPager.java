package com.android.gallery3.ui.pager;

import android.app.Activity;
import android.view.View;

import com.android.gallery3.R;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/15 17:13
 */

public class MapPager extends BasePager {

    public MapPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_map, null);

        return view;
    }

    @Override
    public void initData() {

    }
}
