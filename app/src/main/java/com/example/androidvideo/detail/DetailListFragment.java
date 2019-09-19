package com.example.androidvideo.detail;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidvideo.R;
import com.example.androidvideo.base.BaseFragment;


public class DetailListFragment extends BaseFragment {

    private static final String CHANNEL_ID = "channelid";
    private static final String SITE_ID = "siteid";
    private RecyclerView mRecyclerView;
    private int mSiteId;
    private int mChannelId;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_list;
    }

    @Override
    protected void initView() {
        mRecyclerView = bindViewId(R.id.pullloadRecyclerView);

        if (getArguments() != null){
            mSiteId = getArguments().getInt(SITE_ID);
            mChannelId = getArguments().getInt(CHANNEL_ID);
        }



    }

    @Override
    protected void initData() {

    }

    public static Fragment newInstance(int siteId, int channld){
        DetailListFragment fragment = new DetailListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SITE_ID,siteId);
        bundle.putInt(CHANNEL_ID,channld);
        fragment.setArguments(bundle);
        return fragment;
    }
}
