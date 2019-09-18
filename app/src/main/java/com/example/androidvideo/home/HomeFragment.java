package com.example.androidvideo.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidvideo.R;
import com.example.androidvideo.base.BaseFragment;
import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.hejunlin.superindicatorlibray.LoopViewPager;


public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        LoopViewPager viewPager = bindViewId(R.id.looperviewpager);
        CircleIndicator indicator = bindViewId(R.id.indicator);
        viewPager.setAdapter(new HomePicAdapter(getActivity()));
        viewPager.setLooperPic(true);
        indicator.setViewPager(viewPager);

    }

    @Override
    protected void initData() {

    }
}
