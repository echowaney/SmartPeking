package com.example.hashwaney.zhbj33.acitivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.hashwaney.zhbj33.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailActivity
        extends AppCompatActivity
{

    @BindView(R.id.ib_back)
    ImageButton  mIbBack;
    @BindView(R.id.ib_share)
    ImageButton  mIbShare;
    @BindView(R.id.ib_textsize)
    ImageButton  mIbTextsize;
    @BindView(R.id.webview)
    WebView      mWebview;
    @BindView(R.id.pb_refresh)
    ProgressBar  mPbRefresh;
    @BindView(R.id.activity_news_detail)
    LinearLayout mActivityNewsDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //初始化webview界面
        String url = getIntent().getStringExtra("url");

        //允许js脚本
//        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebview.getSettings().setJavaScriptEnabled(true);
        //隐藏进度条



        //加载webview
        mWebview.loadUrl(url);

    }

    @OnClick({R.id.ib_back,
              R.id.ib_share,
              R.id.ib_textsize})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_share:
                break;
            case R.id.ib_textsize:
                break;
        }
    }
}
