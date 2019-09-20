package com.example.androidvideo.detail;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.AbsListView;
import android.widget.GridView;

import androidx.annotation.Nullable;

import com.example.androidvideo.R;
import com.example.androidvideo.api.OnGetVideoListener;
import com.example.androidvideo.api.SiteApi;
import com.example.androidvideo.base.BaseFragment;
import com.example.androidvideo.model.Album;
import com.example.androidvideo.model.ErrorInfo;
import com.example.androidvideo.model.sohu.Video;
import com.example.androidvideo.model.sohu.VideoList;

public class AlbumPlayGridFragment extends BaseFragment {

    private static final String ARGS_ALBUM = "album";
    private static final String ARGS_IS_SHOWDESC = "isShowDesc";
    private static final String ARGS_INIT_POSITION = "initVideoPosition";
    private GridView mGridView;
    private Album mAlbum;
    private int mInitPosition;
    private boolean mIsShowDesc;
    private int mPageNo;
    private int mPageSize;
    private VideoItemAdapter mVideoItemAdapter;
    private int mPageTotal;
    private VideoList mVideoList = new VideoList();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsFristSelection = true;
    private boolean isLoading = false;

    public static AlbumPlayGridFragment newInstance(Album album, boolean isShowDesc, int initVideoPosition){
        AlbumPlayGridFragment fragment = new AlbumPlayGridFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_ALBUM, album);
        bundle.putBoolean(ARGS_IS_SHOWDESC, isShowDesc);
        bundle.putInt(ARGS_INIT_POSITION, initVideoPosition);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_album_play_grid;
    }

    @Override
    protected void initView() {
        mGridView = bindViewId(R.id.gv_video_layout);
        if (getArguments() != null){
            mAlbum = getArguments().getParcelable(ARGS_ALBUM);
            mIsShowDesc = getArguments().getBoolean(ARGS_IS_SHOWDESC);
            mInitPosition = getArguments().getInt(ARGS_INIT_POSITION);
            mPageNo = 0;
            mPageSize = 50;
        }
        mGridView.setNumColumns(mIsShowDesc ? 1 : 6);
        mVideoItemAdapter = new VideoItemAdapter(getActivity(),mAlbum.getVideoTotal(),mIsShowDesc,mVideoSelectedListner);
        mGridView.setAdapter(mVideoItemAdapter);
        mPageTotal = (mAlbum.getVideoTotal() + mPageSize -1)/ mPageSize;

        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i2 > 0){
                    int lastViewVisible = i + i1;
                    if (lastViewVisible == i2 && !isLoading){
                        loadData();
                    }

                }
            }
        });
    }

    @Override
    protected void initData() {

        loadData();

    }

    private void loadData(){
        mPageNo++;
        isLoading = true;
        SiteApi.onGetVideo(mPageSize, mPageNo, mAlbum, new OnGetVideoListener() {
            @Override
            public void OnGetVideoSuccess(VideoList videoList) {
                isLoading = false;
                mVideoList.addAll(videoList);
                mVideoItemAdapter.setmVideoList(mVideoList);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mVideoItemAdapter != null){
                            mVideoItemAdapter.notifyDataSetChanged();
                        }
                        if (mVideoItemAdapter.getCount() > mInitPosition && mIsFristSelection){
                            mGridView.setSelection(mInitPosition);
                            mGridView.setItemChecked(mInitPosition,true);
                            mIsFristSelection = false;
                            SystemClock.sleep(100);
                            mGridView.smoothScrollToPosition(mInitPosition);
                        }
                    }
                });
            }

            @Override
            public void OnGetVideoFailed(ErrorInfo info) {
                isLoading = false;
            }
        });
    }

    private OnVideoSelectedListener mVideoSelectedListner = new OnVideoSelectedListener() {
        @Override
        public void onVideoSelected(Video video, int position) {
            if (mGridView != null){
                mGridView.setSelection(position);
                mGridView.setItemChecked(position,true);

            }
        }
    };
}

