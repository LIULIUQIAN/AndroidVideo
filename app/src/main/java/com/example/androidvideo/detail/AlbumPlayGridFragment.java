package com.example.androidvideo.detail;


import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;

import com.example.androidvideo.R;
import com.example.androidvideo.api.OnGetVideoListener;
import com.example.androidvideo.api.SiteApi;
import com.example.androidvideo.base.BaseFragment;
import com.example.androidvideo.model.Album;
import com.example.androidvideo.model.ErrorInfo;
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
    }

    @Override
    protected void initData() {

        loadData();

    }

    private void loadData(){
        mPageNo++;
        SiteApi.onGetVideo(mPageSize, mPageNo, mAlbum, new OnGetVideoListener() {
            @Override
            public void OnGetVideoSuccess(VideoList videoList) {

            }

            @Override
            public void OnGetVideoFailed(ErrorInfo info) {

            }
        });
    }
}

