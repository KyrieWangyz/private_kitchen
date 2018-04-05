package com.youdu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.youdu.fragment.myFragment.FragmentInfo;
import com.youdu.fragment.searchfragments.MaterialFragment;
import com.youdu.fragment.searchfragments.RecipesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrie on 2017/10/3.
 */

public class SearchAdapter extends FragmentPagerAdapter {
    List<FragmentInfo> mFragments=new ArrayList<>(2);


    public SearchAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        mFragments.add(new FragmentInfo("按菜名搜索", MaterialFragment.class));
        mFragments.add(new FragmentInfo("按食材搜索", RecipesFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return  null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
