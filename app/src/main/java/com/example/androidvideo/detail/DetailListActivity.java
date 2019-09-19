package com.example.androidvideo.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.androidvideo.R;
import com.example.androidvideo.base.BaseActivity;
import com.example.androidvideo.model.Channel;

import java.util.HashMap;

public class DetailListActivity extends BaseActivity {

    public static final  String CHANNEL_ID = "channid";
    private int mChannId;
    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_list;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null){
            mChannId = intent.getIntExtra(CHANNEL_ID,0);
        }

        Channel channel = new Channel(mChannId,this);

        mSetSupportActionBar();
        setSupportArrowActionBar(true);
        setTitle(channel.getChannelName());

        mViewPager = bindViewId(R.id.pager);
        mViewPager.setAdapter(new SitePagerAdapter(getSupportFragmentManager(),1,this,mChannId));

    }

    @Override
    protected void initDate() {

    }

    class SitePagerAdapter extends FragmentPagerAdapter {

        private HashMap<Integer, DetailListFragment> mPagerMap;
        private Context mContext;
        private int mChannelID;

        public SitePagerAdapter(@NonNull FragmentManager fm, int behavior,Context context, int channelid) {
            super(fm, behavior);
            mContext = context;
            mChannelID = channelid;
            mPagerMap = new HashMap<>();
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {

            return DetailListFragment.newInstance(position+2,mChannelID);
        }

        @Override
        public int getCount() {
            return 1;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Object object = super.instantiateItem(container, position);
            if (object instanceof DetailListFragment){
                mPagerMap.put(position, (DetailListFragment) object);
            }
            return object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
            mPagerMap.remove(position);

        }
    }


}
