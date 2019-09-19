package com.example.androidvideo;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

public class AppManager extends Application {

    private static Gson mGson;
    private static OkHttpClient mOkHttpClient;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();

    }

    public static OkHttpClient getHttpClient() {
        return mOkHttpClient;
    }

    public static Gson getmGson() {
        return mGson;
    }

    public static void setmGson(Gson mGson) {
        AppManager.mGson = mGson;
    }

    public static OkHttpClient getmOkHttpClient() {
        return mOkHttpClient;
    }

    public static void setmOkHttpClient(OkHttpClient mOkHttpClient) {
        AppManager.mOkHttpClient = mOkHttpClient;
    }

    /**
     * 当前网络是否可用
     * @return
     */
    public static boolean isNetWorkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isNetworkWifiAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(1) != null) {
            NetworkInfo.State state = connectivityManager.getNetworkInfo(1).getState();
            if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
