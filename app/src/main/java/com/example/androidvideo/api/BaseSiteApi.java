package com.example.androidvideo.api;

import java.nio.channels.Channel;

public abstract class BaseSiteApi {
    public abstract void onGetChannelAlbums(Channel channel, int pageNo, int pageSize, OnGetChannelAlbumListener listener);
}
