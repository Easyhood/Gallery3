package com.android.gallery3.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gallery3.R;

/**
 * 照片的fragment
 */
public class PictureFragment extends BaseFragment {

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
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

}
