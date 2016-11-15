package com.android.gallery3.ui.pager;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.gallery3.R;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/15 17:00
 */

public class TimePager extends BasePager {

    private RecyclerView recyclerViewTime;

    public TimePager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_time, null);
        recyclerViewTime = (RecyclerView) view.findViewById(R.id.recyclerView_time);
        return view;
    }

    @Override
    public void initData() {

    }
}
