package com.example.androidvideo.home;


import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.androidvideo.R;
import com.example.androidvideo.base.BaseFragment;

public class BlogFragment extends BaseFragment {

    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blog;
    }

    @Override
    protected void initView() {

        mWebView = bindViewId(R.id.webview);
        mProgressBar = bindViewId(R.id.pb_progress);

        WebSettings settings = mWebView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www.baidu.com/");
        mWebView.setWebChromeClient(mWebChromeClient);

    }

    @Override
    protected void initData() {

    }

    private WebChromeClient mWebChromeClient = new WebChromeClient(){

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if (newProgress == 100){
                mProgressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    };
}
