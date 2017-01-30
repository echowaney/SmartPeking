package com.example.hashwaney.zhbj33.acitivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.hashwaney.zhbj33.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
        //        WebSettings.TextSize textSize = mWebview.getSettings()
        //                                                .getTextSize();
        //允许js脚本
        //        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebview.getSettings()
                .setJavaScriptEnabled(true);
        //隐藏进度条 ---设置一个客户端
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //隐藏进度条
                mPbRefresh.setVisibility(View.GONE);

            }
        });


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
                showShare();
                break;
            case R.id.ib_textsize:
                changeTextSize();
                break;
        }
    }


    private String[]               textSizeTypes = new String[]{"特大字体",
                                                                "大号字体",
                                                                "正常字体",
                                                                "小号字体",
                                                                "超小字体",


                                                                };
    private WebSettings.TextSize[] types         = new WebSettings.TextSize[]{WebSettings.TextSize.LARGEST,
                                                                              WebSettings.TextSize.LARGER,
                                                                              WebSettings.TextSize.NORMAL,
                                                                              WebSettings.TextSize.SMALLER,
                                                                              WebSettings.TextSize.SMALLEST

    };

    private int selectItem;
    private void changeTextSize() {
        //弹出一个dialog
        new AlertDialog.Builder(this).setTitle("选择字体大小")
                                     .setSingleChoiceItems(textSizeTypes,       //定义的类型
                                                           2,           //默认选中
                                                           new DialogInterface.OnClickListener() {
                                                               @Override
                                                               public void onClick(DialogInterface dialog,
                                                                                   int which)
                                                               {
                                                                   //which 代表被选中的条目
                                                                   selectItem= which;
                                                               }
                                                           })
                                     .setPositiveButton("确定",
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog,
                                                                                int which)
                                                            {
                                                              //设置真是的字体大小
                                                                mWebview.getSettings().setTextSize(types[selectItem]);

                                                            }
                                                        })
                                     .setNegativeButton("取消",
                                                        null)
                                     .show();

    }

    //第三方分享
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://www.itcast.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.itcast.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.itcast.cn");

        // 启动分享GUI
        oks.show(this);
    }


}
