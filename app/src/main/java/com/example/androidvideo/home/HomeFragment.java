package com.example.androidvideo.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidvideo.R;
import com.example.androidvideo.base.BaseFragment;
import com.example.androidvideo.model.Channel;
import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.hejunlin.superindicatorlibray.LoopViewPager;


public class HomeFragment extends BaseFragment {

    private GridView mGridView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        LoopViewPager viewPager = bindViewId(R.id.looperviewpager);
        CircleIndicator indicator = bindViewId(R.id.indicator);
        viewPager.setAdapter(new HomePicAdapter(getActivity()));
        viewPager.setLooperPic(true);
        indicator.setViewPager(viewPager);

        mGridView = bindViewId(R.id.gv_channel);
        mGridView.setAdapter(new ChannelAdapter());

    }

    @Override
    protected void initData() {

    }

    class ChannelAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int i) {
            return new Channel(i+1,getActivity());
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Channel channel = (Channel) getItem(i);
            ViewHolder holder =null;
            if (view == null){
                view = LayoutInflater.from(getActivity()).inflate(R.layout.home_grid_item,null);
                holder = new ViewHolder();
                holder.imageView = view.findViewById(R.id.iv_home_item_img);
                holder.textView = view.findViewById(R.id.tv_home_item_text);
                view.setTag(holder);

            }else {
                holder = (ViewHolder) view.getTag();

            }
            holder.textView.setText(channel.getChannelName());
            holder.imageView.setImageResource(channel.getImagResId());

            return view;
        }
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }

}
