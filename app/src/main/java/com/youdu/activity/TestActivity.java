package com.youdu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.youdu.R;
import com.youdu.activity.base.BaseActivity;
import com.youdu.module.recommand.DouguobbModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;
import com.youdu.util.ImageLoaderManager;

/**
 * Created by kyrie on 2017/7/13.
 */

public class TestActivity extends BaseActivity {
    private DouguobbModel mRecommandData;
    private ImageView img;
    private TextView vegetable;
    private TextView text;
    private int orderid;
    private int getOrderid;
    private String getvegetable;
    private String vegetables;
    private int mLenght;
    public static final String VEGETABLE="vegetable";
    private ImageLoaderManager mImagerLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_layout);
        initData();
        init();
        requestRecommandData();

    }

    private void init(){
        img=(ImageView)findViewById(R.id.text_image);
        vegetable=(TextView)findViewById(R.id.text_vegetable);
        text=(TextView)findViewById(R.id.text_message);
    }
    private void initData() {
        Intent intent = getIntent();
        getvegetable = intent.getStringExtra(VEGETABLE);

//        Intent intent = getIntent();
//        Bundle bundle=intent.getExtras();
//        getvegetable = bundle.getString(VEGETABLE);
        //mLenght = getOrderid.size();
    }
    private void requestRecommandData() {
        RequestCenter.requestpostDetail(getvegetable,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                mRecommandData = (DouguobbModel) responseObj;

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
        if (mRecommandData != null  ) {//判断数据是否为空，不为空则服务器为我们返回了数据，真正去调用
            text.setText(mRecommandData.message);
            vegetable.setText(mRecommandData.vegetable);
            Picasso.with(TestActivity.this).load(Uri.parse(mRecommandData.picture)).into(img);

      //      mImagerLoader.displayImage(img, mRecommandData.picture);
        } else {
            showErrorView();
        }
        Log.d("lodd", "showSuccessView: hjfj91919515");


    }
    private void showErrorView() {
        Log.d("lodd", "showSuccessView: hjfj91919515");

    }
}
