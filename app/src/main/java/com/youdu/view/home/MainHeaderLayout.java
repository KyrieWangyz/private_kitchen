package com.youdu.view.home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.youdu.R;
import com.youdu.activity.DailyrecommendationActivity;
import com.youdu.activity.MainActivitycirel;
import com.youdu.activity.ScenenActivity;
import com.youdu.activity.SearchActivity;
import com.youdu.adapter.CourseAdapter;
import com.youdu.adapter.CoursennAdapter;
import com.youdu.adapter.PhotoPagerAdapter;
import com.youdu.adapter.RollViewPagerAdapter;
import com.youdu.adutil.ImageLoaderUtil;
import com.youdu.module.recommand.DouguommModel;
import com.youdu.module.recommand.RecommandFooterValue;
import com.youdu.module.recommand.RecommandHeadValue;
import com.youdu.view.viewpagerindictor.CirclePageIndicator;

import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by kyrie on 2017/10/21.
 */

public class MainHeaderLayout extends RelativeLayout implements View.OnClickListener {

    private Context mContext;

    /**
     * UI
     */
    private ImageView mLoadingView;
    private ListView mListView;
    private View mContentView;

    private RollPagerView mRollPagerView;
    private View view;
    private ViewPager mPage;
    //   private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private RelativeLayout recommond;
    private RelativeLayout search;
    private RelativeLayout personal;
    private RelativeLayout mn;

    private CourseAdapter cAdapter;
    private CoursennAdapter mAdaptern;
    private DouguommModel mRecommandData;
    private RelativeLayout mRootView;
    private AutoScrollViewPager mViewPager;
    private CirclePageIndicator mPagerIndictor;
    private TextView mHotView;
    private PhotoPagerAdapter mAdapter;
    private ImageView[] mImageViews = new ImageView[4];
    private LinearLayout mFootLayout;

    /**
     * Data
     */
    private RecommandHeadValue mHeaderValue;
    private ImageLoaderUtil mImageLoader;

    public MainHeaderLayout(Context context, RecommandHeadValue headerValue) {
        this(context, null, headerValue);
    }

    public MainHeaderLayout(Context context) {
        this(context, null, null);
    }

    public MainHeaderLayout(Context context, AttributeSet attrs, RecommandHeadValue headerValue) {
        super(context, attrs);
        mContext = context;
        mHeaderValue = headerValue;
        mImageLoader = ImageLoaderUtil.getInstance(mContext);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootView = (RelativeLayout) inflater.inflate(R.layout.listview_main_head_layout, this);


        recommond = (RelativeLayout) mRootView.findViewById(R.id.recommand_layout);
        recommond.setOnClickListener(this);
        search = (RelativeLayout) mRootView.findViewById(R.id.search_layout);
        search.setOnClickListener(this);
        personal = (RelativeLayout) mRootView.findViewById(R.id.personal_layout);
        personal.setOnClickListener(this);
        mn= (RelativeLayout) mRootView.findViewById(R.id.mn_layout);
        mn.setOnClickListener(this);


        mRollPagerView = (RollPagerView) mRootView.findViewById(R.id.rollviewpager);
        mRollPagerView.setAnimationDurtion(500);//设置切换时间
        mRollPagerView.setAdapter(new RollViewPagerAdapter(mRollPagerView));

    }

    private HomeBottomItem createItem(RecommandFooterValue value) {
        HomeBottomItem item = new HomeBottomItem(mContext, value);
        return item;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recommand_layout:
                Toast.makeText(mContext, "默认Toast样式",
                        Toast.LENGTH_SHORT).show();
            //    ((Callintent)mContext).intent1();
                Intent intent=new Intent(mContext, DailyrecommendationActivity.class);
                mContext.startActivity(intent);


                break;
            case R.id.search_layout:

                Intent intent2=new Intent(mContext, SearchActivity.class);
                mContext.startActivity(intent2);

                break;
            case R.id.personal_layout:
                Intent intent1=new Intent(mContext, MainActivitycirel.class);
                mContext.startActivity(intent1);
                break;
            case R.id.mn_layout:
                Intent intent3=new Intent(mContext, ScenenActivity.class);
                mContext.startActivity(intent3);
                break;
        }
    }
}