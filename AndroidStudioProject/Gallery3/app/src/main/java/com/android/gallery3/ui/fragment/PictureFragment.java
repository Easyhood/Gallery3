package com.android.gallery3.ui.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gallery3.R;
import com.android.gallery3.adapter.ContentPagerAdapter;
import com.android.gallery3.global.GlobalConstants;
import com.android.gallery3.ui.pager.BasePager;
import com.android.gallery3.ui.pager.MapPager;
import com.android.gallery3.ui.pager.TimePager;
import com.android.gallery3.utils.EventUtil;
import com.android.gallery3.utils.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * 照片的fragment
 */
public class PictureFragment extends BaseFragment {


    private NoScrollViewPager mViewPager;
    private ArrayList<BasePager> mList;
    public Activity mActivity;

    public PictureFragment() {
        // Required empty public constructor
    }

    public static PictureFragment newInstance() {
        PictureFragment fragment = new PictureFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        //注册eventbus
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册eventbus
        EventBus.getDefault().unregister(this);
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

        mViewPager.setAdapter(new ContentPagerAdapter(mList));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                BasePager pager = mList.get(i);
                pager.initData();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //初始化第一个页面数据
        mList.get(0).initData();
    }

    @Subscribe
    public void onEvent(EventUtil event) {
        int msg = event.getMsg();
        if (msg == GlobalConstants.TIME_MSG) {
            mViewPager.setCurrentItem(0, false);
        } else if (msg == GlobalConstants.MAP_MSG) {
            mViewPager.setCurrentItem(1, false);
        }
    }
}
