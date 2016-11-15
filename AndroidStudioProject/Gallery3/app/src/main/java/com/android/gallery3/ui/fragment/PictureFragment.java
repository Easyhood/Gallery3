package com.android.gallery3.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gallery3.R;
import com.android.gallery3.ui.pager.BasePager;
import com.android.gallery3.ui.pager.MapPager;
import com.android.gallery3.ui.pager.TimePager;
import com.android.gallery3.utils.NoScrollViewPager;

import java.util.ArrayList;

/**
 * 照片的fragment
 */
public class PictureFragment extends BaseFragment {


    private NoScrollViewPager mViewPager;
    private ArrayList<BasePager> mList;

    public PictureFragment() {
        // Required empty public constructor
    }

    public static PictureFragment newInstance() {
        PictureFragment fragment = new PictureFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        mViewPager = (NoScrollViewPager) view.findViewById(R.id.viewpager_picture);
        initData();
        return view;
    }

    @Override
    public void initData() {
        //初始化几个pager页面
        mList = new ArrayList<BasePager>();
        mList.add(new TimePager(mActivity));
        mList.add(new MapPager(mActivity));

        //TODO 2016年11月15日17:52:51
    }
}
