package com.android.gallery3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gallery3.R;
import com.android.gallery3.domain.TimeViewHolder;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/21 13:59
 */

public class TimeAdapter extends RecyclerView.Adapter<TimeViewHolder> {

    private final Context mContext;
    private final List<String> mDatas;
    private final LayoutInflater inflater;

    public TimeAdapter(Context context,List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pager_time_item, parent, false);
        TimeViewHolder holder = new TimeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TimeViewHolder holder, int position) {
        holder.tvMonth.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
