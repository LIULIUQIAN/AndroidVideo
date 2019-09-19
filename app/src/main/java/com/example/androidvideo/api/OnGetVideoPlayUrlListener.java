package com.example.androidvideo.api;

import com.example.androidvideo.model.ErrorInfo;
import com.example.androidvideo.model.sohu.Video;

public interface OnGetVideoPlayUrlListener {

    void onGetSuperUrl(Video video, String url);//超清url

    void onGetNoramlUrl(Video video, String url);//标清url

    void onGetHighUrl(Video video, String url);//高清url

    void onGetFailed(ErrorInfo info);
}
