package com.android.gallery3.domain;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.gallery3.R;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/18 17:40
 */

public class TimeViewHolder extends RecyclerView.ViewHolder {

    public TextView tvMonth;
    public TextView tvYear;
    public ImageView ivTimepic;

    public TimeViewHolder(View view) {
        super(view);
        tvMonth = (TextView) view.findViewById(R.id.tv_month);
        tvYear = (TextView) view.findViewById(R.id.tv_year);
        ivTimepic = (ImageView) view.findViewById(R.id.iv_timepic);
    }
}
