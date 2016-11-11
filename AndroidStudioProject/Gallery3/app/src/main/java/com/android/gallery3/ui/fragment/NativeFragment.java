package com.android.gallery3.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gallery3.R;

/**
 * 本地Fragment
 */
public class NativeFragment extends BaseFragment {

    public NativeFragment() {
        // Required empty public constructor
    }

    public static NativeFragment newInstance() {
        NativeFragment fragment = new NativeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_native, container, false);
    }
}
