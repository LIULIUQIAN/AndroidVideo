package com.example.androidvideo.api;

import android.content.Context;

import com.example.androidvideo.model.Album;
import com.example.androidvideo.model.Channel;
import com.example.androidvideo.model.sohu.Video;

public class SiteApi {

    public static void onGetChannelAlbums(Context context, int pageNo, int pageSize, int siteId, int channelId, OnGetChannelAlbumListener listener) {
        switch (siteId) {
            case 1:
                new LetvApi().onGetChannelAlbums(new Channel(channelId, context), pageNo, pageSize , listener);
                break;
            case 2:
                new SohuApi().onGetChannelAlbums(new Channel(channelId, context), pageNo, pageSize , listener);
                break;
        }
    }

    public static void onGetAlbumDetail(Album album, OnGetAlbumDetailListener listener) {
        int siteId = album.getSite().getSiteId();
        switch (siteId) {
            case 1:
                new LetvApi().onGetAlbumDetail(album, listener);
                break;
            case 2:
                new SohuApi().onGetAlbumDetail(album, listener);
                break;
        }
    }

    /**
     * 取video相关信息
     * @param album
     * @param listener
     */
    public static void onGetVideo(int pageSize, int pageNo, Album album, OnGetVideoListener listener) {
        int siteId = album.getSite().getSiteId();
        switch (siteId) {
            case 2:
                new LetvApi().onGetVideo(album, pageSize, pageNo, listener);
                break;
            case 1:
                new SohuApi().onGetVideo(album,  pageSize, pageNo, listener);
                break;
        }
    }

    public static void onGetVideoPlayUrl(Video video, OnGetVideoPlayUrlListener listener) {
        int siteId = video.getSite();
        switch (siteId) {
            case 2:
                new LetvApi().onGetVideoPlayUrl(video,  listener);
                break;
            case 1:
                new SohuApi().onGetVideoPlayUrl(video,   listener);
                break;
        }
    }
}
