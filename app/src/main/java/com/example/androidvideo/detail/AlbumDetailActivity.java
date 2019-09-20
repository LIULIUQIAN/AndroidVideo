package com.example.androidvideo.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.androidvideo.R;
import com.example.androidvideo.api.OnGetAlbumDetailListener;
import com.example.androidvideo.api.SiteApi;
import com.example.androidvideo.base.BaseActivity;
import com.example.androidvideo.model.Album;
import com.example.androidvideo.model.ErrorInfo;

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
}
