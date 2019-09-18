package com.example.androidvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewpager;
    private List<View> mViewList;
    private ImageView[] mDotList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    private void initView() {
        viewpager = findViewById(R.id.guide_viewpager);

        mViewList = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        mViewList.add(inflater.inflate(R.layout.guide_one_layout,null));
        mViewList.add(inflater.inflate(R.layout.guide_two_layout,null));
        View guideThreeView = inflater.inflate(R.layout.guide_three_layout,null);
        mViewList.add(guideThreeView);

        guideThreeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this, HomeActivity.class));
                finish();
            }
        });

        LinearLayout linearLayout = findViewById(R.id.ll_dots_layout);
        mDotList = new ImageView[mViewList.size()];
        for (int i = 0;i < mViewList.size();i++){
            mDotList[i] = (ImageView) linearLayout.getChildAt(i);
            mDotList[i].setEnabled(false);
        }
        mDotList[0].setEnabled(true);

        MyPagerAdapter adapter = new MyPagerAdapter();
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i<mDotList.length;i++){
            mDotList[i].setEnabled(false);
        }
        mDotList[position].setEnabled(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
