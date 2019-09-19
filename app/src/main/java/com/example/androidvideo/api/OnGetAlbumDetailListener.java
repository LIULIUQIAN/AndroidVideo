package com.example.androidvideo.api;

import com.example.androidvideo.model.Album;
import com.example.androidvideo.model.ErrorInfo;

public interface OnGetAlbumDetailListener {
    void onGetAlbumDetailSuccess(Album album);
    void onGetAlbumDetailFailed(ErrorInfo info);
}
