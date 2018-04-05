package com.youdu.fragment.myFragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.adapter.CoursennAdapter;
import com.youdu.fragment.BaseFragment;
import com.youdu.module.recommand.DouguommModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class GetExpressFragment extends BaseFragment  {

    private View mContentView;
    private ListView mListView;
    private TextView mQRCodeView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;
    private CircleImageView icon;
    private String orderid;
    private TextView name;
    private TextView time;
    private TextView parkname;
    private TextView remark;
    /**
     * data
     */
    private CoursennAdapter mAdapter;
    private DouguommModel mRecommandData;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fargement_home_layout1, container, false);
        initView();
        return mContentView;
    }

    private void initView() {
       /* mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);
        mCategoryView = (TextView) mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = (TextView) mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);*/




        mListView = (ListView) mContentView.findViewById(R.id.list_view);
//      mListView.setOnItemClickListener(this);
        mLoadingView = (ImageView) mContentView.findViewById(R.id.loading_view);
       AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();//启动lodingview动画
        anim.start();



/*
        icon=(CircleImageView)mContentView.findViewById(R.id.item_logo_view);
        name=(TextView)mContentView.findViewById(R.id.item_address);
        parkname=(TextView)mContentView.findViewById(R.id.item_zoom);
        time=(TextView)mContentView.findViewById(R.id.item_zoom);
        remark=(TextView)mContentView.findViewById(R.id.item_footer_view);
*/
    }

    //发送推荐产品请求
    private void requestRecommandData() {
        RequestCenter.requestRecommandDatan(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                mRecommandData = (DouguommModel) responseObj;
                //更新UI
                showSuccessView();

            }

            @Override
            public void onFailure(Object reasonObj) {
                //显示请求失败View
                showErrorView();

            }
        });
    }

    //显示请求成功UI
    private void showSuccessView() {
        if (mRecommandData.data.list != null && mRecommandData.data.list.size() > 0) {//判断数据是否为空，不为空则服务器为我们返回了数据，真正去调用
            mLoadingView.setVisibility(View.GONE);//隐藏lodingview
            mListView.setVisibility(View.VISIBLE);
            //为listview添加头

        } else {
            showErrorView();
        }
        Log.d("lodd", "showSuccessView: hjfj91919515");
      //  mListView.addHeaderView(new HomeHeaderLayout(mContext, mRecommandData.data.head));
        mAdapter = new CoursennAdapter(mContext, mRecommandData.data.list);//mRecommandData.data.list为我们的数据，mcontext为上下文，courseadapter为我们的每个ui更新
        mListView.setAdapter(mAdapter);

    }

    private void showErrorView() {
        Log.d("lodd", "showSuccessView: hjfj91919515");

    }













}
