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
 * Date       : 2016/11/14 14:38
 */

public class NativeViewHolder extends RecyclerView.ViewHolder {

     public TextView tvNativeItemTitle;
     public ImageView ivNativeItemPic;
     public TextView tvNativeItemPath;
     public TextView tvNativeItemNum;

    public NativeViewHolder(View view){
        super(view);
        ivNativeItemPic = (ImageView) view.findViewById(R.id.iv_native_item_pic);
        tvNativeItemTitle = (TextView) view.findViewById(R.id.tv_native_item_title);
        tvNativeItemPath = (TextView) view.findViewById(R.id.tv_native_item_path);
        tvNativeItemNum = (TextView) view.findViewById(R.id.tv_native_item_num);
    }
}
