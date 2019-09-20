package com.example.androidvideo.detail;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidvideo.R;
import com.example.androidvideo.api.OnGetChannelAlbumListener;
import com.example.androidvideo.api.SiteApi;
import com.example.androidvideo.base.BaseFragment;
import com.example.androidvideo.model.Album;
import com.example.androidvideo.model.AlbumList;
import com.example.androidvideo.model.Channel;
import com.example.androidvideo.model.ErrorInfo;
import com.scwang.smart.refresh.footer.ClassicsFooter;

import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;


public class DetailListFragment extends BaseFragment {

    private static final String CHANNEL_ID = "channelid";
    private static final String SITE_ID = "siteid";
    private RecyclerView mRecyclerView;
    private int mSiteId;
    private int mChannelId;
    private int pageNo;
    private DetailListAdapter mAdapter;

    private Handler handler = new Handler(Looper.getMainLooper());
    private AlbumList mList = new AlbumList();
    private RefreshLayout refreshLayout;

    public static Fragment newInstance(int siteId, int channld){
        DetailListFragment fragment = new DetailListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SITE_ID,siteId);
        bundle.putInt(CHANNEL_ID,channld);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_list;
    }

    @Override
    protected void initView() {
        mRecyclerView = bindViewId(R.id.pullloadRecyclerView);

        if (getArguments() != null){
            mSiteId = getArguments().getInt(SITE_ID);
            mChannelId = getArguments().getInt(CHANNEL_ID);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mAdapter = new DetailListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);


        refreshLayout = bindViewId(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                reRreshData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadData();
            }
        });

    }

    @Override
    protected void initData() {

        refreshLayout.autoRefresh();//自动刷新
    }

    /**
     * 下拉刷新
     */
    private void reRreshData() {
        pageNo = 0;
        mList.clear();
        loadData();
    }

    private void loadData() {

        pageNo++;
        SiteApi.onGetChannelAlbums(getActivity(), pageNo, 30, mSiteId, mChannelId, new OnGetChannelAlbumListener() {
            @Override
            public void onGetChannelAlbumSuccess(AlbumList albumList) {
                albumList.debug();

                mList.addAll(albumList);
                mAdapter.setmAlbumList(mList);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                        mAdapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onGetChannelAlbumFailed(ErrorInfo info) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                    }
                });
            }
        });

    }

    class DetailListAdapter extends RecyclerView.Adapter{

        private AlbumList mAlbumList = new AlbumList();
        private Context context;

        public DetailListAdapter(Context context){
            this.context = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.detailist_item,null);
            ItemViewHolder itemViewHolder = new ItemViewHolder(view);
            view.setTag(itemViewHolder);
            return itemViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (mAlbumList.size() == 0){
                return;
            }
           final Album album = mAlbumList.get(position);

            if (holder instanceof ItemViewHolder){
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                itemViewHolder.albumName.setText(album.getTitle());

                itemViewHolder.albumTip.setText(album.getTip());

                if (album.getVerImgUrl() != null){
                    Glide.with(getActivity()).load(album.getVerImgUrl()).into(itemViewHolder.albumPoster);
                }else if(album.getHorImgUrl() != null){
                    Glide.with(getActivity()).load(album.getHorImgUrl()).into(itemViewHolder.albumPoster);
                }

                itemViewHolder.resultContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mChannelId == Channel.DOCUMENTRY|| mChannelId == Channel.MOVIE || mChannelId== Channel.VARIETY || mChannelId == Channel.MUSIC) {
                            AlbumDetailActivity.launch(getActivity(), album, 0, true);
                        } else {
                            AlbumDetailActivity.launch(getActivity(), album);
                        }
                    }
                });
            }


        }

        @Override
        public int getItemCount() {
            return mAlbumList.size();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{
            private LinearLayout resultContainer;
            private ImageView albumPoster;
            private TextView albumName;
            private TextView albumTip;

            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                resultContainer = itemView.findViewById(R.id.album_container);
                albumPoster = itemView.findViewById(R.id.iv_album_poster);
                albumTip = itemView.findViewById(R.id.tv_album_tip);
                albumName = itemView.findViewById(R.id.tv_album_name);

            }
        }

        public void setmAlbumList(AlbumList mAlbumList) {
            this.mAlbumList = mAlbumList;
        }
    }
}
