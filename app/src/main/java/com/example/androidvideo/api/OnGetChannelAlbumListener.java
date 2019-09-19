package com.example.androidvideo.api;

import com.example.androidvideo.model.AlbumList;
import com.example.androidvideo.model.ErrorInfo;

public interface OnGetChannelAlbumListener {

    void onGetChannelAlbumSuccess(AlbumList albumList);
    void onGetChannelAlbumFailed(ErrorInfo info);
}

