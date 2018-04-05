package com.youdu.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.jude.rollviewpager.RollPagerView;
import com.youdu.R;
import com.youdu.activity.base.BaseActivity;
import com.youdu.adapter.CourseAdapter;
import com.youdu.adapter.CoursennAdapter;
import com.youdu.adapter.HotvegetableAdapter;
import com.youdu.module.recommand.DouguommModel;
import com.youdu.module.recommand.HotvegetableModel;
import com.youdu.module.recommand.HotvegetablenModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;

import java.util.List;

/**
 * Created by kyrie on 2017/10/27.
 */

public class SceneActivity extends BaseActivity {
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
    private HotvegetableAdapter mAdapternn;
    private DouguommModel mRecommandData;
    private HotvegetableModel model;
    public static final String KEYWORDS="keywords";
    public static final String NUMBER="number";
    private String getKeywords;
    private String getnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenen_layout);
        mListView= (ListView) findViewById(R.id.list);
        initdata();
        requestRecommandData();
    }

    private void initdata() {
        Intent intent=getIntent();
        getKeywords=intent.getStringExtra(KEYWORDS);
        getnumber=intent.getStringExtra(NUMBER);
    }

    //发送推荐产品请求
    private void requestRecommandData() {

        RequestCenter.requestscene(getKeywords,getnumber,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                model = (HotvegetableModel) responseObj;
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
        if(model.result !=null&&model.result.size()>0){



            mAdapternn = new HotvegetableAdapter(this, model.result);//mRecommandData.data.list为我们的数据，mcontext为上下文，courseadapter为我们的每个ui更新
            mListView.setAdapter(mAdapternn);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HotvegetablenModel value = (HotvegetablenModel) mAdapternn.getItem(position);

                    Intent intent = new Intent(SceneActivity.this, ButtonActivity.class);
//                    Bundle bundle=new Bundle();
//                    bundle.putString(TestActivity.VEGETABLE, value.vegetable);
//                    startActivity(intent);

                    intent.putExtra(ButtonActivity.NAME,value.name);
                    intent.putExtra(ButtonActivity.DIFFICULTY,value.difficulty);
                    intent.putExtra(ButtonActivity.IMG,value.img);
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
}
