package com.example.androidvideo.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.androidvideo.AppManager;

public class Album implements Parcelable {

    private String albumId;//专辑id
    private int videoTotal;//集数
    private String title;//专辑名称
    private String subTitle;//专辑子标题
    private String director;//导演
    private String mainActor;//主演
    private String verImgUrl;//专辑竖图
    private String horImgUrl;//专辑横图
    private String albumDesc;//专辑描述
    private Site site;//网站
    private String tip;//提示
    private boolean isCompleted;//专辑是否更新完
    private String letvStyle;//乐视特殊字段
    private Context context;

    public Album(int siteId) {
        site =  new Site(siteId);
    }


    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public int getVideoTotal() {
        return videoTotal;
    }

    public void setVideoTotal(int videoTotal) {
        this.videoTotal = videoTotal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getVerImgUrl() {
        return verImgUrl;
    }

    public void setVerImgUrl(String verImgUrl) {
        this.verImgUrl = verImgUrl;
    }

    public String getHorImgUrl() {
        return horImgUrl;
    }

    public void setHorImgUrl(String horImgUrl) {
        this.horImgUrl = horImgUrl;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getLetvStyle() {
        return letvStyle;
    }

    public void setLetvStyle(String letvStyle) {
        this.letvStyle = letvStyle;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(albumId);
        parcel.writeInt(videoTotal);
        parcel.writeString(title);
        parcel.writeString(subTitle);
        parcel.writeString(director);
        parcel.writeString(mainActor);
        parcel.writeString(verImgUrl);
        parcel.writeString(horImgUrl);
        parcel.writeString(albumDesc);
        parcel.writeString(tip);
        parcel.writeInt(site.getSiteId());
        parcel.writeByte((byte) (isCompleted() ? 1: 0));
        parcel.writeString(letvStyle);
    }

    protected Album(Parcel in) {
        this.albumId = in.readString();
        this.videoTotal = in.readInt();
        this.title = in.readString();
        this.subTitle = in.readString();
        this.director = in.readString();
        this.mainActor = in.readString();
        this.verImgUrl = in.readString();
        this.horImgUrl = in.readString();
        this.albumDesc = in.readString();
        this.tip = in.readString();
        this.site = new Site(in.readInt());
        this.isCompleted = in.readByte() != 0;
        this.letvStyle = in.readString();
    }

    public String toJson(){
      return AppManager.getmGson().toJson(this);
    }

    public static Album fromJson(String json){
        return AppManager.getmGson().fromJson(json,Album.class);
    }
}
