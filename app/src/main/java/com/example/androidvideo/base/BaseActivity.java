package com.example.androidvideo.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.androidvideo.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initDate();

    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initDate();

    protected <T extends View> T bindViewId(int resId){
        return (T)findViewById(resId);
    }

    protected void mSetSupportActionBar(){
        mToolbar = bindViewId(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }
    protected void setSupportArrowActionBar(boolean isSupport){
        getSupportActionBar().setDisplayHomeAsUpEnabled(isSupport);
    }

    protected void setActionBarIcon(int resId){
        if (mToolbar != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mToolbar.setNavigationIcon(resId);
            }
        }
    }

}
