package com.example.androidvideo.detail;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidvideo.R;
import com.example.androidvideo.model.sohu.Video;
import com.example.androidvideo.model.sohu.VideoList;

public class VideoItemAdapter extends BaseAdapter {

    private Context mContext;
    private VideoList mVideoList = new VideoList();
    private boolean mIsShowTitleContent;
    private int currentSelectIndex = 0;

    public void setCurrentSelectIndex(int currentSelectIndex) {
        this.currentSelectIndex = currentSelectIndex;
        notifyDataSetChanged();
    }

    public VideoItemAdapter(Context context, int totalCount, boolean isShow){
        mContext = context;
        mIsShowTitleContent = isShow;
    }

    @Override
    public int getCount() {

        return mVideoList.size();
    }

    @Override
    public Object getItem(int i) {
        return mVideoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.video_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.videoContainer = view.findViewById(R.id.video_container);
            viewHolder.videoTitle = view.findViewById(R.id.bt_video_title);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (currentSelectIndex == i) {
            viewHolder.videoContainer.setBackgroundResource(R.color.colorPrimary);
        } else {
            viewHolder.videoContainer.setBackgroundColor(Color.TRANSPARENT);
        }

        final Video video = mVideoList.get(i);
        if (mIsShowTitleContent){
            if (TextUtils.isEmpty(video.getVideoName())){
                viewHolder.videoTitle.setText(String.valueOf(i+1));
            }else {
                viewHolder.videoTitle.setText(video.getVideoName());
            }
        }else {
            viewHolder.videoTitle.setText(String.valueOf(i+1));
        }
        return view;
    }

    public void setmVideoList(VideoList mVideoList) {
        this.mVideoList = mVideoList;
    }

    class ViewHolder{
        LinearLayout videoContainer;
        TextView videoTitle;
    }

}
