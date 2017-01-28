package com.example.hashwaney.zhbj33.acitivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.adapter.MainAdapter;
import com.example.hashwaney.zhbj33.adapter.MenuAdapter;
import com.example.hashwaney.zhbj33.base.BaseFragment;
import com.example.hashwaney.zhbj33.base.OnLoadDataOperator;
import com.example.hashwaney.zhbj33.bean.NewsCenterBean;
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
import static com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.TOUCHMODE_FULLSCREEN;
import static com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.TOUCHMODE_NONE;

public class MainActivity
        extends FragmentActivity
        implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener
{
    private List<Fragment> mFragmentList;
    private List<NewsCenterBean.NewsMenuBean> mNewsMenuBeenLists =new ArrayList<>() ;

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
    public SlidingMenu mSlidingMenu;
    private String url;
    private RecyclerView mRecycleview;
    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        initViewPager();
        initSlidingMenu();
        initMenuAdapter();
        //一上来默认选中首页
//        mViewpager.setCurrentItem(0);
//        mRbHome.setChecked(true);
        mRg.setOnCheckedChangeListener(this);


    }

    //定义一个方法去接收来自NewsCenterFragment的数据

    public void  setNewsMenuBeenLists(List<NewsCenterBean.NewsMenuBean> newsMenuBeenLists){
        mNewsMenuBeenLists.clear();
//        mNewsMenuBeenLists =newsMenuBeenLists;
        mNewsMenuBeenLists.addAll(newsMenuBeenLists);
        Log.d("result", "initMenuAdapter:  mNewsMenuBeenLists "+mNewsMenuBeenLists.size());
        //数据是请求到了，但是需要将数据传递给menuAdapter -- 那么就应该去adapter中定义一个方法用来接收从activity中传过来的数据
        //在将数据传递到adapter中
       // mAdapter.setNewsMenuBeenLists(mNewsMenuBeenLists);
        mMenuAdapter.setMenuBeanLists(mNewsMenuBeenLists);



    }


    //初始化侧滑菜单
    private void initSlidingMenu() {
        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        mSlidingMenu.setMode(LEFT);
        mSlidingMenu.setBehindWidth(250);
        mSlidingMenu.setMenu(R.layout.activity_main_menu);
        mSlidingMenu.attachToActivity(this, SLIDING_CONTENT);

        mRecycleview = (RecyclerView) mSlidingMenu.findViewById(R.id.recycleview);



    }
    //初始化slidingmenu的菜单
    private void initMenuAdapter() {
//        mNewsMenuBeenLists.clear();

//        setNewsMenuBeenLists( ); 这样做是不行的,,这样做并没有将集合传递过来,就是一个空集合


      //  Log.d("result", "initMenuAdapter:  mNewsMenuBeenLists "+mNewsMenuBeenLists.toString());
       // mAdapter = new MenuAdapter(null);
        //mRecycleview.setAdapter(mAdapter);
        mMenuAdapter =new MenuAdapter(this,null);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mRecycleview.setAdapter(mMenuAdapter);




    }

    //初始化viewpager
    private void initViewPager() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new NewsCenterFragment());
        mFragmentList.add(new SmartServiceFragment());
        mFragmentList.add(new GovaffairsFragment());
        mFragmentList.add(new SettingFragment());
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(),mFragmentList);
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
                //设置首页不让slidingmenu滑出
                mSlidingMenu.setTouchModeAbove(TOUCHMODE_NONE);

                 break;
            case R.id.rb_newscenter:
                itemid =1;
                mSlidingMenu.setTouchModeAbove(TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_smartservice:
                itemid=2;
                mSlidingMenu.setTouchModeAbove(TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_govaffairs:
                itemid=3;
                mSlidingMenu.setTouchModeAbove(TOUCHMODE_FULLSCREEN);
                break;

            case R.id.rb_setting:
                itemid=4;
                //设置 设置界面不让slidingmenu滑出
                mSlidingMenu.setTouchModeAbove(TOUCHMODE_NONE);
                 break;

        }
        mViewpager.setCurrentItem(itemid,false);

        //            mViewpager.getCurrentItem()
        //通过fragmentmanger
//        getSupportFragmentManager().findFragmentById(itemid);
        //这里就是加载数据的入口 当点击了底部button的时候,就得到相应的fragment,其中有些fragment需要联网
//        有些就不需要 ,只要是联网接口的实现类,就可以进行联网操作
        BaseFragment fragment = (BaseFragment) mFragmentList.get(itemid);
        if (fragment instanceof OnLoadDataOperator){
            //去网络加载数据
            ((OnLoadDataOperator)fragment).onLoadNetData();


        }

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


    //获取对应的fragment的实例对象
    public BaseFragment getCurrentFragment(){
        int currentItem = mViewpager.getCurrentItem();
        BaseFragment fragment = (BaseFragment) mFragmentList.get(currentItem);
        return fragment;
    }



}
