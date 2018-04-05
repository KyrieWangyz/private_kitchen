package com.youdu.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.youdu.R;
import com.youdu.adapter.SearchAdapter;

import butterknife.ButterKnife;

/**
 * Created by kyrie on 2017/10/3.
 */

public class SearchActivity extends AppCompatActivity {
//    @BindView(R.id.search_viewpager)
   ViewPager searchViewpager;
//    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);
        ButterKnife.bind(this);
        init();
        initSearch();
    }

    private void init() {
        searchViewpager= (ViewPager) findViewById(R.id.search_viewpager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
    }

    private void initSearch() {
        PagerAdapter adapter = new SearchAdapter(getSupportFragmentManager());
        searchViewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(searchViewpager);
    }
}