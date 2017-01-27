package com.example.hashwaney.zhbj33.acitivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.adapter.MainAdapter;
import com.example.hashwaney.zhbj33.fragment.GovaffairsFragment;
import com.example.hashwaney.zhbj33.fragment.HomeFragment;
import com.example.hashwaney.zhbj33.fragment.NewsCenterFragment;
import com.example.hashwaney.zhbj33.fragment.SettingFragment;
import com.example.hashwaney.zhbj33.fragment.SmartServiceFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.LEFT;
import static com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.SLIDING_CONTENT;

public class MainActivity
        extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener
{
    private List<Fragment> mFragmentList;


    @BindView(R.id.viewpager)
    ViewPager   mViewpager;
    @BindView(R.id.rb_home)
    RadioButton mRbHome;
    @BindView(R.id.rb_newscenter)
    RadioButton mRbNewscenter;
    @BindView(R.id.rb_smartservice)
    RadioButton mRbSmartservice;
    @BindView(R.id.rb_govaffairs)
    RadioButton mRbGovaffairs;
    @BindView(R.id.rb_setting)
    RadioButton mRbSetting;
    @BindView(R.id.rg)
    RadioGroup  mRg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        initViewPager();
        initSlidingMenu();
        //一上来默认选中首页
        mViewpager.setCurrentItem(0);
        mRbHome.setChecked(true);
        mRg.setOnCheckedChangeListener(this);


    }
    //初始化侧滑菜单
    private void initSlidingMenu() {
        SlidingMenu slidingMenu =new SlidingMenu(this);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setMode(LEFT);
        slidingMenu.setBehindWidth(250);
        slidingMenu.setBackgroundColor(Color.RED);
        slidingMenu.attachToActivity(this,SLIDING_CONTENT);



    }

    //初始化viewpager
    private void initViewPager() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new NewsCenterFragment());
        mFragmentList.add(new SmartServiceFragment());
        mFragmentList.add(new GovaffairsFragment());
        mFragmentList.add(new SettingFragment());
        MainAdapter adapter =new MainAdapter(getSupportFragmentManager(),mFragmentList);
        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(this);

    }

    //当底部button被点击了,相应的就去切换对应的fragment
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int itemid =0;
        switch (checkedId) {
            case R.id.rb_home :
                itemid=0;
                 break;
            case R.id.rb_newscenter:
                itemid =1;
                break;
            case R.id.rb_smartservice:
                itemid=2;
                break;
            case R.id.rb_govaffairs:
                itemid=3;
                break;

            case R.id.rb_setting:
                itemid=4;
                 break;

        }
        mViewpager.setCurrentItem(itemid,false);

        //            mViewpager.getCurrentItem()

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    //当页面被选中的时候,相应的底部button文字和图标改变
    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mRbHome.setChecked(true);
                break;
            case 1:
                mRbNewscenter.setChecked(true);
                break;
            case 2:
                mRbSmartservice.setChecked(true);
                break;
            case 3:
                mRbGovaffairs.setChecked(true);
                break;
            case 4:
                mRbSetting.setChecked(true);
                break;

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
