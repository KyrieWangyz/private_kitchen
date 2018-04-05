package com.youdu.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.jude.rollviewpager.RollPagerView;
import com.youdu.R;
import com.youdu.activity.base.BaseActivity;
import com.youdu.fragment.myFragment.GetExpressFragment;
import com.youdu.fragment.myFragment.RegisterfirestFragment;
import com.youdu.fragment.myFragment.RegistersecondFragment;
import com.youdu.fragment.myFragment.RegisterthirdFragment;
import com.youdu.myfile.FixedSpeedScroll;
import com.youdu.okhttp.listener.SendFragment;
import com.youdu.okhttp.listener.ToFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrie on 2017/7/29.
 */

public class LoginNewActivity extends BaseActivity implements ToFragment,SendFragment{
    private RegisterfirestFragment mRegisterfirstFragment;
    private RegistersecondFragment mRegistersecondFragment;
    private RegisterthirdFragment mRegisterthirdFragment;
    private GetExpressFragment mg;
    private FragmentManager fm;
    private RollPagerView mRollPagerView;
    private View view;
    private ViewPager mPage;
    private FragmentPagerAdapter mAdapter;/////////
    private List<Fragment> mFragments;
    private  FixedSpeedScroll mScroller;

    private String str1;
    private String str2;
    private String sex;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new_layout);

        init();
        mAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mPage.setAdapter(mAdapter);
        try {
            // 通过class文件获取mScroller属性
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mScroller = new FixedSpeedScroll(mPage.getContext(), new AccelerateInterpolator());
            mField.set(mPage, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
        select(0);

//        fm = getFragmentManager();
//        FragmentTransaction fragmentTransaction
//                =fm.beginTransaction();
//        fragmentTransaction.replace(R.id.content_layout,mRegisterfirstFragment);
//
//        fragmentTransaction.commit();



    }
/*
获取第一个Fragment的两个参数
 */


        public void setstr(String str1,String str2){
            this.str1=str1;
            this.str2=str2;
        }
        public String getstr(){
            return str1;
        }
    public String getstr2(){
        return str2;
    }
    public void setsex(String sex){
        this.sex=sex;
    }
    public String getsex(){
       return sex;
    }

    private void select(int i){
        switch (i)
        {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
        }

        mScroller.setmDuration(4 * 100);// 切换时间，毫秒值
        mPage.setCurrentItem(i);

    }


    private void init(){
        mFragments = new ArrayList<Fragment>();

        mRegisterfirstFragment=new RegisterfirestFragment();
        mRegistersecondFragment=new RegistersecondFragment();
        mRegisterthirdFragment=new RegisterthirdFragment();
        mPage=(ViewPager) findViewById(R.id.viewPager);
        mFragments.add(mRegisterfirstFragment);
        mFragments.add(mRegistersecondFragment);
        mFragments.add(mRegisterthirdFragment);



    }

        public void skip(int i) {
            select(i);
        }

}
