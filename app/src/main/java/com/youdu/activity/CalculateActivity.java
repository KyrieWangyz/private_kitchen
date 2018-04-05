package com.youdu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youdu.R;
import com.youdu.activity.base.BaseActivity;
import com.youdu.adapter.CalculateAdapter;
import com.youdu.adapter.CalculatelistAdapter;
import com.youdu.module.recommand.GetcaloriebykeywordsnModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;

import java.util.Random;

/**
 * Created by kyrie on 2017/10/22.
 */

public class CalculateActivity extends BaseActivity implements View.OnClickListener{
    private ImageView add_button;
    private ListView listview;
    private CalculatelistAdapter adaptern;
    private TextView kll;

    private GetcaloriebykeywordsnModel model;
    private CalculateAdapter adapter;

    private int i;


    private String addstring="ada";
    private String string="ttt";
    public static final String NAME="name";
    private String getname;
    private String getkll;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_layout);
        getintentmessage();
        init();
        requestRecommandData();

        add(addstring);
        add(string);
    }

    private void init() {
        add_button= (ImageView) findViewById(R.id.add);
        add_button.setOnClickListener(this);
        listview= (ListView) findViewById(R.id.listview);
        kll= (TextView) findViewById(R.id.kll_text);
    }

    public void add(String addstring){
        this.addstring=addstring;
        if(string==null||"".equals(string)){
            string=addstring;
        }
        else {
            string=string+"-"+addstring;

        }
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void getintentmessage() {
        Intent intent = getIntent();
        getname = intent.getStringExtra(NAME);
    }


    private void requestRecommandData() {

        RequestCenter.requestcaloriebukeywords(getname,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                model = (GetcaloriebykeywordsnModel) responseObj;
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

//        String nu=model.result.get(0).img;
//
//        HotvegetablenModel modeln=(HotvegetablenModel) model.result.get(0);


//        Toast.makeText(getApplicationContext(), uu,
//                Toast.LENGTH_SHORT).show();
        if(model !=null){
            int number = new Random().nextInt(14000) + 800;
            String no=number+"";


            kll.setText(no);
//            getkll=model.result.get(0).calorie.toString();
//            listview.addHeaderView(new CalculateListviewHeaderLayout(this,null,getname,getkll));
            adaptern=new CalculatelistAdapter(this,model.result);
            listview.setAdapter(adaptern);

        }
        else {
            showErrorView();
        }

    }



    private void showErrorView() {
        Log.d("lodd", "showSuccessView: hjfj91919515");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
Intent intent=new Intent(this,SearchFromCalculateActivity.class);
                startActivity(intent);
                break;
        }
    }
}
