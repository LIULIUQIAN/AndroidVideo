package com.example.androidvideo;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.androidvideo.base.BaseActivity;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mSetSupportActionBar();
        setActionBarIcon(R.drawable.ic_home_icon);
        setTitle("首页");

//        mDrawerLayout = bindViewId(R.id.drawer_layout);
//        mNavigationView = bindViewId(R.id.navigation_view);
//        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,R.string.drawer_open,R.string.drawer_close);
//        mActionBarDrawerToggle.syncState();
//        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    @Override
    protected void initDate() {

    }
}
