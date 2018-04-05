package com.youdu.fragment.myFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.fragment.BaseFragment;
import com.youdu.module.recommand.EatingrecordnModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;

/**
 * Created by kyrie on 2017/8/1.
 */

public class SundayFragment extends BaseFragment {
    private String id = "znr123";
    private String weekday = "星期日";
    private View mContentView;
    private ImageView detailsone;
    private ImageView detailssecond;
    private ImageView detailsthird;
    private EatingrecordnModel model;
    private TextView first;
    private TextView first1;
    private TextView dinner2;
    private TextView dinner1;
    private TextView din1;
    private TextView din2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_vegetable_diary, container, false);
        initView();
        return mContentView;

        // view = inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        requestRecommandData();
    }

    private void initView() {
        first=(TextView) mContentView.findViewById(R.id.first);
        first1= (TextView) mContentView.findViewById(R.id.first1);
        dinner1= (TextView) mContentView.findViewById(R.id.dinner1);
        dinner2=(TextView)mContentView.findViewById(R.id.dinner2);
        din1= (TextView) mContentView.findViewById(R.id.din1);
        din2= (TextView) mContentView.findViewById(R.id.din2);

    }


    private void requestRecommandData() {

        RequestCenter.requesteatingrecord(id, weekday, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                model = (EatingrecordnModel) responseObj;
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

    private void showSuccessView() {
//        String breakfast;
//        String bcll;
//        String lunch;
//        String lcll;
//        String dinner;
//        String dkll;

        if (model.userDayMeals != null && model.userDayMeals.size() > 0) {

            String[] breakfast = model.userDayMeals.get(0).breakfast.split("\\|");
            String[] lunch=model.userDayMeals.get(0).lunch.split("\\|");
            String[] dinner=model.userDayMeals.get(0).dinner.split("\\|");

            first.setText(breakfast[0]);
            first1.setText(breakfast[1]);
            dinner1.setText(lunch[0]);
            dinner2.setText(lunch[1]);
            din1.setText(dinner[0]);
            din2.setText(dinner[1]);


        } else {
            showErrorView();
        }


    }
    private void showErrorView() {
        Log.d("lodd", "showSuccessView: hjfj91919515");

    }
}