package com.example.androidvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidvideo.base.BaseActivity;

public class HomeActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mSetSupportActionBar();
        setActionBarIcon(R.drawable.ic_home_icon);
        setTitle("首页");
    }

    @Override
    protected void initDate() {

    }
}
