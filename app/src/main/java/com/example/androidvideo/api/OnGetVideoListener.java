package com.example.androidvideo.api;

import com.example.androidvideo.model.ErrorInfo;
import com.example.androidvideo.model.sohu.VideoList;

public interface OnGetVideoListener {

    void OnGetVideoSuccess(VideoList videoList);
    void OnGetVideoFailed(ErrorInfo info);
}
