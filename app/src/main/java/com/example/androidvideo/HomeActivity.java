package com.example.androidvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.androidvideo.base.BaseActivity;
import com.example.androidvideo.home.AboutFragment;
import com.example.androidvideo.home.BlogFragment;
import com.example.androidvideo.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MenuItem mPreItem;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mSetSupportActionBar();
        setActionBarIcon(R.drawable.ic_home_icon);
        setTitle("首页");

        mDrawerLayout = bindViewId(R.id.drawer_layout);
        mNavigationView = bindViewId(R.id.navigation_view);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,R.string.drawer_open,R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        mPreItem = mNavigationView.getMenu().getItem(0);
        mPreItem.setChecked(true);
        initFragment();
        handleNatigationView();

    }

    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = FragmentManagerWrapper.getInstance().createFragment(HomeFragment.class);
        mFragmentManager.beginTransaction().add(R.id.fl_main_content,mCurrentFragment).commit();

    }

    private void handleNatigationView(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (mPreItem != null){
                    mPreItem.setChecked(false);
                }
                switch (item.getItemId()){
                    case R.id.navigation_item_video:
                        switchFragment(HomeFragment.class);
                        mToolbar.setTitle("视频");
                        break;
                    case R.id.navigation_item_blog:
                        switchFragment(BlogFragment.class);
                        mToolbar.setTitle("播客");
                        break;
                    case R.id.navigation_item_about:
                        switchFragment(AboutFragment.class);
                        mToolbar.setTitle("关于我们");
                        break;
                }
                mPreItem = item;
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                item.setChecked(true);

                return false;
            }
        });
    }

    private void switchFragment(Class<?> clazz) {
        Fragment fragment = FragmentManagerWrapper.getInstance().createFragment(clazz);
        if (fragment.isAdded()){
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(fragment).commitAllowingStateLoss();
        }else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.fl_main_content,fragment).commitAllowingStateLoss();
        }
      mCurrentFragment = fragment;
    }

    @Override
    protected void initDate() {

    }
}
