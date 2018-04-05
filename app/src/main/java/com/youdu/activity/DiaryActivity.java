package com.youdu.activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.youdu.R;
import com.youdu.activity.base.BaseActivity;
import com.youdu.fragment.myFragment.FirdayFragment;
import com.youdu.fragment.myFragment.MondayFragment;
import com.youdu.fragment.myFragment.SaturdayFragment;
import com.youdu.fragment.myFragment.SundayFragment;
import com.youdu.fragment.myFragment.ThusdayFragment;
import com.youdu.fragment.myFragment.TuesdayFragment;
import com.youdu.fragment.myFragment.WednesdayFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrie on 2017/8/1.
 */

public class DiaryActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout mon;
    private RelativeLayout tur;
    private RelativeLayout wed;
    private RelativeLayout thu;
    private RelativeLayout fir;
    private RelativeLayout sat;
    private RelativeLayout sun;
    private ViewPager mPage;
    private MondayFragment monday;
    private ThusdayFragment thursday;
    private WednesdayFragment wednesday;
    private TuesdayFragment tuesday;
    private FirdayFragment firday;
    private SaturdayFragment saturday;
    private SundayFragment sunday;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_diary_layout);
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
        mPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem=mPage.getCurrentItem();
                switch (position){
                    case 0:
                       select(1);
                        break;
                    case 1:
                       select(2);
                        break;
                    case 2:
                        select(3);
                        break;
                    case 3:
                        select(4);
                        break;
                    case 4:
                        select(5);
                        break;
                    case 5:
                        select(6);
                        break;
                    case 6:
                        select(7);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        select(1);
    }
    private void init(){
        mFragments=new ArrayList<Fragment>() ;
        mon=(RelativeLayout) findViewById(R.id.monday);
        mon.setOnClickListener(this);
        tur=(RelativeLayout)findViewById(R.id.tuesday);
        tur.setOnClickListener(this);
        wed=(RelativeLayout)findViewById(R.id.wednesday);
        wed.setOnClickListener(this);
        thu=(RelativeLayout)findViewById(R.id.thursday);
        thu.setOnClickListener(this);
        fir=(RelativeLayout)findViewById(R.id.firsday);
        fir.setOnClickListener(this);
        sat=(RelativeLayout)findViewById(R.id.saturday);
        sat.setOnClickListener(this);
        sun=(RelativeLayout)findViewById(R.id.sunday);
        sun.setOnClickListener(this);
        mPage=(ViewPager)findViewById(R.id.viewPager);
        monday=new MondayFragment();
        thursday=new ThusdayFragment();
        wednesday=new WednesdayFragment();
        tuesday=new TuesdayFragment();
        firday=new FirdayFragment();
        saturday=new SaturdayFragment();
        sunday=new SundayFragment();
        mFragments.add(monday);
        mFragments.add(tuesday);
        mFragments.add(wednesday);
        mFragments.add(thursday);
        mFragments.add(firday);
        mFragments.add(saturday);
        mFragments.add(sunday);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.monday:
                select(1);
                break;
            case R.id.tuesday:
                select(2);
                break;
            case R.id.wednesday:
                select(3);
                break;
            case R.id.thursday:
                select(4);
                break;
            case R.id.firsday:
                select(5);
                break;
            case R.id.saturday:
                select(6);
                break;
            case R.id.sunday:
                select(7);
                break;

        }
    }
    private void select(int i){
        switch (i){
            case 1:
                clear();
                mon.setBackgroundResource(R.drawable.suggest_select);

                break;
            case 2:
                clear();
                tur.setBackgroundResource(R.drawable.suggest_select);
                break;
            case 3:
                clear();
                wed.setBackgroundResource(R.drawable.suggest_select);
                break;
            case 4:
                clear();
                thu.setBackgroundResource(R.drawable.suggest_select);
                break;
            case 5:
                clear();
                fir.setBackgroundResource(R.drawable.suggest_select);
                break;
            case 6:
                clear();
                sat.setBackgroundResource(R.drawable.suggest_select);
                break;
            case 7:
                clear();
                sun.setBackgroundResource(R.drawable.suggest_select);
                break;

        }
        int j=i-1;
        mPage.setCurrentItem(j);

    }
    private void clear(){
        mon.setBackgroundResource(0);
        tur.setBackgroundResource(0);
        wed.setBackgroundResource(0);
        thu.setBackgroundResource(0);
        fir.setBackgroundResource(0);
        sat.setBackgroundResource(0);
        sun.setBackgroundResource(0);
    }


}
