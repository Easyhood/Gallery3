package com.android.gallery3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gallery3.R;
import com.android.gallery3.domain.NativeViewHolder;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/14 14:41
 */

public class NativeAdapter extends RecyclerView.Adapter<NativeViewHolder> {

    private final Context mContext;
    private final List<String> mDatas;
    private final LayoutInflater inflater;

    public NativeAdapter(Context context, List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public NativeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_native_item, parent, false);
        NativeViewHolder holder = new NativeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NativeViewHolder holder, int position) {
        holder.tvNativeItemTitle.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
