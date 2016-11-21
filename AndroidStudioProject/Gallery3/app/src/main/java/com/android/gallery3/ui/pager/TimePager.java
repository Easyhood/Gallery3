package com.android.gallery3.ui.pager;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.gallery3.R;
import com.android.gallery3.adapter.TimeAdapter;
import com.android.gallery3.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 时间流页面
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/15 17:00
 */

public class TimePager extends BasePager {

    private RecyclerView recyclerViewTime;
    private List<String> mDatas;
    public TimePager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_time, null);
        recyclerViewTime = (RecyclerView) view.findViewById(R.id.recyclerView_time);
        initData();
        TimeAdapter timeAdapter = new TimeAdapter(mActivity, mDatas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 4);
        recyclerViewTime.setAdapter(timeAdapter);
        recyclerViewTime.setLayoutManager(gridLayoutManager);
        recyclerViewTime.addItemDecoration(new DividerGridItemDecoration(mActivity));
        return view;
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            mDatas.add(i + "月");
        }
    }
}
