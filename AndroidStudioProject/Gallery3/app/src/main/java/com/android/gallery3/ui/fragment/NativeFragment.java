package com.android.gallery3.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gallery3.adapter.NativeAdapter;
import com.android.gallery3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地Fragment
 */
public class NativeFragment extends BaseFragment {

    private List<String> mDatas;
    private RecyclerView recyclerViewNative;

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
        View view = inflater.inflate(R.layout.fragment_native, container, false);
        recyclerViewNative = (RecyclerView) view.findViewById(R.id.recyclerView_native);
        initData();
        NativeAdapter nativeAdapter = new NativeAdapter(getActivity(), mDatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewNative.setLayoutManager(layoutManager);
        recyclerViewNative.setAdapter(nativeAdapter);
        return view;


    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for ( int i=0; i < 40; i++) {
            mDatas.add( "item"+i);
        }
    }
}
