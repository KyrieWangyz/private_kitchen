package com.youdu.fragment.myFragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.jude.rollviewpager.RollPagerView;
import com.youdu.R;
import com.youdu.activity.ArticalActivity;
import com.youdu.activity.DailyrecommendationActivity;
import com.youdu.activity.MainActivitycirel;
import com.youdu.activity.SearchActivity;
import com.youdu.adapter.ArticleAdapter;
import com.youdu.adapter.CourseAdapter;
import com.youdu.adapter.CoursennAdapter;
import com.youdu.fragment.BaseFragment;
import com.youdu.implem.Callintent;
import com.youdu.module.recommand.ArticleModel;
import com.youdu.module.recommand.ArticlenModel;
import com.youdu.module.recommand.DouguommModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;
import com.youdu.view.home.MainHeaderLayout;

import java.util.List;

/**
 * Created by kyrie on 2017/8/7.
 */

public class MainFragment extends BaseFragment implements View.OnClickListener{


    private ImageView mLoadingView;
    private ListView mListView;
    private View mContentView;
    private String date;

    private RollPagerView mRollPagerView;
    private View view;
    private View vv;
    private ViewPager mPage;
 //   private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
   private RelativeLayout recommond ;
    private  RelativeLayout search;
    private RelativeLayout personal;

    private CourseAdapter cAdapter;
    private CoursennAdapter mAdaptern;
    private ArticleAdapter mAdapternn;
    private DouguommModel mRecommandData;
    private ArticlenModel model;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        view = inflater.inflate(R.layout.new_fragment_home, container, false);
        //vv=inflater.from(mContext).inflate(R.layout.listview_main_head_layout, null);////////////////////////////原本mcontext为this
        vv= LayoutInflater.from(getActivity()).inflate(R.layout.listview_main_head_layout,null);

//        recommond = (RelativeLayout) vv.findViewById(R.id.recommand_layout);
//        recommond.setOnClickListener(this);
//        search=(RelativeLayout)vv.findViewById(R.id.search_layout);
//        search.setOnClickListener(this);
//        personal=(RelativeLayout)vv.findViewById(R.id.personal_layout);
//        personal.setOnClickListener(this);
//
//        mRollPagerView = (RollPagerView) vv.findViewById(R.id.rollviewpager);
//        mRollPagerView.setAnimationDurtion(500);//设置切换时间
//        mRollPagerView.setAdapter(new RollViewPagerAdapter(mRollPagerView));


        init(inflater);

//        mAdapter = new FragmentPagerAdapter(getFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return mFragments.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return mFragments.size();
//            }
//        };

//        mPage.setAdapter(mAdapter);
//        mPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
        //mPage.setOnPageChangeListener(

        //);


//        mRollPagerView = (RollPagerView) view.findViewById(R.id.rollviewpager);
//        mRollPagerView.setAnimationDurtion(500);//设置切换时间
//        mRollPagerView.setAdapter(new RollViewPagerAdapter(mRollPagerView));

        //mRollPagerView.setHintView(new ColorPointHintView(getContext().getApplicationContext(), Color.WHITE,Color.GRAY));
        // Inflate the layout for this fragment
        return view;
    }

    //发送推荐产品请求
    private void requestRecommandData() {

        RequestCenter.requestarticle(date,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                model = (ArticlenModel) responseObj;
                //更新UI
                showSuccessView();

            }

            @Override
            public void onFailure(Object reasonObj) {
                //显示请求失败View
                showErrorView();

            }
        });

//        RequestCenter.requestRecommandDatan(new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
//                mRecommandData = (DouguommModel) responseObj;
//                //更新UI
//                showSuccessView();
//
//            }
//
//            @Override
//            public void onFailure(Object reasonObj) {
//                //显示请求失败View
//                showErrorView();
//
//            }
//        });
    }

    //显示请求成功UI
    private void showSuccessView() {
        if(model.artical !=null&&model.artical.size()>0){
            mLoadingView.setVisibility(View.GONE);//隐藏lodingview
            mListView.setVisibility(View.VISIBLE);
            //为listview添加头

            mListView.addHeaderView(new MainHeaderLayout(mContext));
            mAdapternn = new ArticleAdapter(mContext, model.artical);//mRecommandData.data.list为我们的数据，mcontext为上下文，courseadapter为我们的每个ui更新
            mListView.setAdapter(mAdapternn);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ArticleModel value = (ArticleModel) mAdapternn.getItem(position);



                    Intent intent = new Intent(mContext, ArticalActivity.class);
                    intent.putExtra(ArticalActivity.URL,value.url);
                    startActivity(intent);


        ////////////////////////////////////////////////////////////


//                    Bundle bundle=new Bundle();
//                    bundle.putString(TestActivity.VEGETABLE, value.vegetable);
//                    startActivity(intent);


//                    Intent intent = new Intent(mContext, TestActivity.class);
//                    Bundle bundle=new Bundle();
//                    bundle.putInt(TestActivity.ORDERID,value.orderid);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                }
            });



        }
        else {
            showErrorView();
        }
//        if (mRecommandData.data.list != null && mRecommandData.data.list.size() > 0) {//判断数据是否为空，不为空则服务器为我们返回了数据，真正去调用
//            mLoadingView.setVisibility(View.GONE);//隐藏lodingview
//            mListView.setVisibility(View.VISIBLE);
//            //为listview添加头
//            mListView.addHeaderView(new MainHeaderLayout(mContext));
//            mAdaptern = new CoursennAdapter(mContext, mRecommandData.data.list);//mRecommandData.data.list为我们的数据，mcontext为上下文，courseadapter为我们的每个ui更新
//            mListView.setAdapter(mAdaptern);
//            setListViewHeight(mListView);
//            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    DouguoModel value = (DouguoModel) mAdaptern.getItem(position);
//
//                    Intent intent = new Intent(mContext, TestActivity.class);
////                    Bundle bundle=new Bundle();
////                    bundle.putString(TestActivity.VEGETABLE, value.vegetable);
////                    startActivity(intent);
//                    intent.putExtra(TestActivity.VEGETABLE,value.vegetable);
//                    startActivity(intent);
//
////                    Intent intent = new Intent(mContext, TestActivity.class);
////                    Bundle bundle=new Bundle();
////                    bundle.putInt(TestActivity.ORDERID,value.orderid);
////                    intent.putExtras(bundle);
////                    startActivity(intent);
//                }
//            });
//
//        } else {
//            showErrorView();
//
//        }
    }
    //  mListView.addHeaderView(new HomeHeaderLayout(mContext, mRecommandData.data.head));


//            }
//        });



    private void showErrorView() {
        Log.d("lodd", "showSuccessView: hjfj91919515");

    }


    private void init(LayoutInflater inflater) {


        mListView = (ListView) view.findViewById(R.id.list_view);
        //    mListView.setOnItemClickListener(this);
        mLoadingView = (ImageView) view.findViewById(R.id.loading_view);
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();//启动lodingview动画
        anim.start();




        //mPage = (ViewPager) view.findViewById(R.id.viewPager);

//        mFragments = new ArrayList<Fragment>();
//        GetExpressFragment getExpressFragment = new GetExpressFragment();
//        HistoryFragment historyFragment = new HistoryFragment();
//
//        mFragments.add(historyFragment);
//        mFragments.add(getExpressFragment);
//        mFragments = new ArrayList<Fragment>();
//        MessageFragment messageFragment = new MessageFragment();
//        HistoryFragment historyFragment = new HistoryFragment();
//
//        mFragments.add(historyFragment);
//        mFragments.add(messageFragment);



    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.recommand_layout:
//                Intent intent=new Intent(getActivity(), DailyrecommendationActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.search_layout:
//
//                break;
//            case R.id.personal_layout:
//                Intent intent1=new Intent(getActivity(), MainActivitycirel.class);
//                startActivity(intent1);
//                break;
//        }
    }







    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void setListViewHeight(ListView listView) {

        // 获取ListView对应的Adapter

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }



    public void intent1(){
        Intent i1=new Intent(getActivity(),DailyrecommendationActivity.class);
        startActivity(i1);
    }


    public void intent3(){
        Intent i1=new Intent(getActivity(),MainActivitycirel.class);
        startActivity(i1);
    }
}
