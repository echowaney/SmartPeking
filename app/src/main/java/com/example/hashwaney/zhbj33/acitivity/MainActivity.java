package com.example.hashwaney.zhbj33.acitivity;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity
        extends AppCompatActivity
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
        ButterKnife.bind(this);
        initViewPager();

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
    }



}
