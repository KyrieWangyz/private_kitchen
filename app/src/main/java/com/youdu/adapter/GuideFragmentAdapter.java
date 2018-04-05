package com.youdu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cleverlin on 2017/8/4.
 */

public class GuideFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments;




    public void setmFragments(List<Fragment> fragments) {
        if (fragments==null) {
            mFragments=new ArrayList<>();
        }else{
            mFragments=fragments;
        }
    }


    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }//getItem()返回的是要显示的fragent对象。

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
