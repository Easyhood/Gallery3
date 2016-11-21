package com.android.gallery3.ui.pager;

import android.app.Activity;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Marker;
import com.android.gallery3.R;

import java.util.List;

/**
 * Description: 地图页面
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/15 17:13
 */

public class MapPager extends BasePager implements
        AMap.OnMarkerClickListener,
        AMap.OnMapLoadedListener,
        AMap.OnCameraChangeListener{
    private List<String> mDatas;
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

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {

    }

    @Override
    public void onMapLoaded() {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
