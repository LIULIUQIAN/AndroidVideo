package com.example.androidvideo.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.androidvideo.R;
import com.example.androidvideo.api.OnGetAlbumDetailListener;
import com.example.androidvideo.api.OnGetVideoPlayUrlListener;
import com.example.androidvideo.api.SiteApi;
import com.example.androidvideo.base.BaseActivity;
import com.example.androidvideo.model.Album;
import com.example.androidvideo.model.ErrorInfo;
import com.example.androidvideo.model.sohu.Video;

public class AlbumDetailActivity extends BaseActivity {

    private Album mAlbum;
    private int mVideNo;
    private boolean mIsShowDesc;
    private ImageView mAlbumImg;
    private TextView mAlbumName;
    private TextView mDirector;
    private TextView mMainActor;
    private TextView mAlbumDesc;
    private AlbumPlayGridFragment mFragment;
    private int mCurrentVideoPosition;
    private Button mSuperBitstreamButton;
    private Button mNormalBitstreamButton;
    private Button mHighBitstreamButton;
    private Handler handler = new Handler(Looper.getMainLooper());

    public static void launch(Activity activity, Album album,int videNo, boolean isShowDesc){
        Intent intent = new Intent(activity,AlbumDetailActivity.class);
        intent.putExtra("album",album);
        intent.putExtra("videNo",videNo);
        intent.putExtra("isShowDesc",isShowDesc);
        activity.startActivity(intent);
    }

    public static void launch(Activity activity, Album album){
        Intent intent = new Intent(activity,AlbumDetailActivity.class);
        intent.putExtra("album",album);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_album_detail;
    }

    @Override
    protected void initView() {
        mAlbum = getIntent().getParcelableExtra("album");
        mVideNo = getIntent().getIntExtra("videNo",0);
        mIsShowDesc = getIntent().getBooleanExtra("isShowDesc",false);

        mSetSupportActionBar();
        setSupportArrowActionBar(true);
        setTitle(mAlbum.getTitle());

        mAlbumImg = bindViewId(R.id.iv_album_image);
        mAlbumName = bindViewId(R.id.tv_album_name);
        mDirector = bindViewId(R.id.tv_album_director);
        mMainActor = bindViewId(R.id.tv_album_mainactor);
        mAlbumDesc = bindViewId(R.id.tv_album_desc);

        mSuperBitstreamButton = bindViewId(R.id.bt_super);
        mNormalBitstreamButton = bindViewId(R.id.bt_normal);
        mHighBitstreamButton = bindViewId(R.id.bt_high);

    }

    @Override
    protected void initDate() {
        updateInfo();

        SiteApi.onGetAlbumDetail(mAlbum, new OnGetAlbumDetailListener() {
            @Override
            public void onGetAlbumDetailSuccess(final Album album) {
                System.out.println(album.toJson());
                mAlbum = album;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mFragment = AlbumPlayGridFragment.newInstance(mAlbum,mIsShowDesc,0);
                        mFragment.setOnPlayVideoSelectedListener(onPlayVideoSelectedListener);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container,mFragment);
                        ft.commit();
                        getFragmentManager().executePendingTransactions();
                    }
                });
            }

            @Override
            public void onGetAlbumDetailFailed(ErrorInfo info) {

            }
        });
    }

    private void updateInfo(){

        if (!TextUtils.isEmpty(mAlbum.getHorImgUrl())){
            Glide.with(this).load(mAlbum.getHorImgUrl()).placeholder(R.drawable.ic_loading).into(mAlbumImg);
        }else if (!TextUtils.isEmpty(mAlbum.getVerImgUrl())){
            Glide.with(this).load(mAlbum.getVerImgUrl()).placeholder(R.drawable.ic_loading).into(mAlbumImg);
        }
        mAlbumName.setText(mAlbum.getTitle());
        if (!TextUtils.isEmpty(mAlbum.getDirector())){
            mDirector.setText("导演："+mAlbum.getDirector());
            mDirector.setVisibility(View.VISIBLE);
        }else {
            mDirector.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mAlbum.getMainActor())){
            mMainActor.setText("导演："+mAlbum.getMainActor());
            mMainActor.setVisibility(View.VISIBLE);
        }else {
            mMainActor.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(mAlbum.getAlbumDesc())){
            mAlbumDesc.setText(mAlbum.getAlbumDesc());
            mAlbumDesc.setVisibility(View.VISIBLE);
        }else {
            mAlbumDesc.setVisibility(View.GONE);
        }
    }

    private AlbumPlayGridFragment.OnPlayVideoSelectedListener onPlayVideoSelectedListener = new AlbumPlayGridFragment.OnPlayVideoSelectedListener() {
        @Override
        public void onPlayVideoSelected(Video video, int position) {
            mCurrentVideoPosition = position;
            SiteApi.onGetVideoPlayUrl(video,onGetVideoPlayUrlListener);
        }
    };

    private OnGetVideoPlayUrlListener onGetVideoPlayUrlListener = new OnGetVideoPlayUrlListener() {
        @Override
        public void onGetSuperUrl(Video video, String url) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mSuperBitstreamButton.setVisibility(View.VISIBLE);
                }
            });

            mSuperBitstreamButton.setTag(R.id.key_video_url, url); //视频url
            mSuperBitstreamButton.setTag(R.id.key_video, video);//视频info
            mSuperBitstreamButton.setTag(R.id.key_current_video_number, mCurrentVideoPosition);//当前视频
            mSuperBitstreamButton.setTag(R.id.key_video_stream, 1); //码流
        }

        @Override
        public void onGetNoramlUrl(Video video, String url) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mNormalBitstreamButton.setVisibility(View.VISIBLE);
                }
            });

            mNormalBitstreamButton.setTag(R.id.key_video_url, url); //视频url
            mNormalBitstreamButton.setTag(R.id.key_video, video);//视频info
            mNormalBitstreamButton.setTag(R.id.key_current_video_number, mCurrentVideoPosition);//当前视频
            mNormalBitstreamButton.setTag(R.id.key_video_stream, 2); //码流
        }

        @Override
        public void onGetHighUrl(Video video, String url) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mHighBitstreamButton.setVisibility(View.VISIBLE);
                }
            });

            mHighBitstreamButton.setTag(R.id.key_video_url, url); //视频url
            mHighBitstreamButton.setTag(R.id.key_video, video);//视频info
            mHighBitstreamButton.setTag(R.id.key_current_video_number, mCurrentVideoPosition);//当前视频
            mHighBitstreamButton.setTag(R.id.key_video_stream, 3); //码流
        }

        @Override
        public void onGetFailed(ErrorInfo info) {

        }
    };
}
