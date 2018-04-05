package com.youdu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.youdu.R;
import com.youdu.activity.base.BaseActivity;
import com.youdu.adapter.HotvegetableAdapter;
import com.youdu.adapter.Vegetable;
import com.youdu.module.recommand.HotvegetableModel;
import com.youdu.module.recommand.HotvegetablenModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrie on 2017/10/25.
 */

public class PrivatecipenActivity extends BaseActivity {
    private List<Vegetable> vegetableList=new ArrayList<>();
    private ListView mListView;
    private HotvegetableAdapter mAdapter;
    private HotvegetableModel mRecommandData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privatecipen);
        init();




//        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
//        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        VegetableAdapter adapter=new VegetableAdapter(vegetableList);
//        recyclerView.setAdapter(adapter);
        requestRecommandData();

    }

    private void init() {
        mListView= (ListView) findViewById(R.id.listview);
    }


    private void requestRecommandData() {
        RequestCenter.requestPrivateData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                mRecommandData = (HotvegetableModel) responseObj;
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
        if (mRecommandData.result != null && mRecommandData.result.size() > 0) {//判断数据是否为空，不为空则服务器为我们返回了数据，真正去调用
            mAdapter = new HotvegetableAdapter(this, mRecommandData.result);//mRecommandData.data.list为我们的数据，mcontext为上下文，courseadapter为我们的每个ui更新
            mListView.setAdapter(mAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HotvegetablenModel value = (HotvegetablenModel) mAdapter.getItem(position);

                    Intent intent = new Intent(PrivatecipenActivity.this, ButtonActivity.class);
//                    Bundle bundle=new Bundle();
//                    bundle.putString(TestActivity.VEGETABLE, value.vegetable);
//                    startActivity(intent);

                    intent.putExtra(ButtonActivity.NAME,value.name);
                    intent.putExtra(ButtonActivity.DIFFICULTY,value.difficulty);
                    intent.putExtra(ButtonActivity.IMG,value.img);
//                    intent.putExtra(ButtonActivity.MATERIAL,value.material);
//                    intent.putExtra(ButtonActivity.TIME,value.time);
//                    intent.putExtra(ButtonActivity.NUTRIENT,value.nutrient);
//                    intent.putExtra(ButtonActivity.CALORIE,value.nutrient.get(position).calorie);
//                    intent.putExtra(ButtonActivity.AXUNGE,value.nutrient.get(position).axunge);
//                    intent.putExtra(ButtonActivity.CARBOHYDRATE,value.nutrient.get(position).carbohydrate);
                    startActivity(intent);

//                    Intent intent = new Intent(mContext, TestActivity.class);
//                    Bundle bundle=new Bundle();
//                    bundle.putInt(TestActivity.ORDERID,value.orderid);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                }
            });

        } else {
            showErrorView();

        }
    }
    //  mListView.addHeaderView(new HomeHeaderLayout(mContext, mRecommandData.data.head));


//            }
//        });



    private void showErrorView() {
        Log.d("lodd", "showSuccessView: hjfj91919515");

    }
}


