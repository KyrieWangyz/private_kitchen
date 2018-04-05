package com.youdu.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.activity.base.BaseActivity;
import com.youdu.module.recommand.PresonalMessagenModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;

/**
 * Created by kyrie on 2017/10/23.
 */

public class ChangeGoalActivity extends BaseActivity implements View.OnClickListener{
    private PresonalMessagenModel presonalMessagenModel;
    private String id="1234";
    private TextView taler;
    private TextView weight;
    private TextView age;
    private TextView sex;
    private TextView weightgoal;
    private TextView time;
    private TextView bianji;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changegoaln);
        init();
        requestRecommandData();
    }

    private void init() {
        taler= (TextView) findViewById(R.id.taler_edit);
        weight= (TextView) findViewById(R.id.weight_edit);
        age= (TextView) findViewById(R.id.age);
        sex= (TextView) findViewById(R.id.sex_edit);
        weightgoal= (TextView) findViewById(R.id.weightgoal_edit);
        time= (TextView) findViewById(R.id.time_edit);
        bianji= (TextView) findViewById(R.id.bianji);
        bianji.setOnClickListener(this);

    }




    private void requestRecommandData() {

        RequestCenter.requestpersonalmessage(id, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                presonalMessagenModel = (PresonalMessagenModel) responseObj;
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
        int ree=0;
        String ta,we,se,weg,ag,ti;

        if (presonalMessagenModel.user != null && presonalMessagenModel.user.size() > 0) {
            taler.setText(presonalMessagenModel.user.get(0).high);
            weight.setText(presonalMessagenModel.user.get(0).weight);
            if(presonalMessagenModel.user.get(0).sex.equals("1")){
                sex.setText("男");
            }else {
                sex.setText("女");
            }

            weightgoal.setText(presonalMessagenModel.user.get(0).targetWeight);
            time.setText(presonalMessagenModel.user.get(0).reachDay);
            age.setText(presonalMessagenModel.user.get(0).age);

            ta=presonalMessagenModel.user.get(0).high;
            we=presonalMessagenModel.user.get(0).weight;
            weg=presonalMessagenModel.user.get(0).targetWeight;
            ag=presonalMessagenModel.user.get(0).age;
            ti=presonalMessagenModel.user.get(0).reachDay;



        } else {
            showErrorView();
        }
    }

    private void showErrorView() {
        Log.d("lodd", "showSuccessView: hjfj91919515");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bianji:

        }
    }
}
