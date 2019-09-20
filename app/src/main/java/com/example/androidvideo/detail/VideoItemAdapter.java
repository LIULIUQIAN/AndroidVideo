package com.example.androidvideo.detail;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.androidvideo.model.sohu.VideoList;

public class VideoItemAdapter extends BaseAdapter {

    private Context mContext;
    private int mTotalCount;
    private OnVideoSelectedListener mListener;
    private VideoList mVideoList;
    private boolean mIsFirst = true;

    public VideoItemAdapter(Context context, int totalCount, OnVideoSelectedListener listener){
        mContext = context;
        mTotalCount = totalCount;
        mListener = listener;

    }

    @Override
    public int getCount() {
        return mVideoList.size();
    }

    @Override
    public Object getItem(int i) {
        return mVideoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }


}
