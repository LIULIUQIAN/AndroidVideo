package com.example.androidvideo.model.sohu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hejunlin on 17/4/4.
 */

/**
 * 搜狐数据频道数据返回集
 */
public class Result {

    @Expose
    private long status;

    @Expose
    private String statusText;

    //for 列表页
    @Expose
    private Data data;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
