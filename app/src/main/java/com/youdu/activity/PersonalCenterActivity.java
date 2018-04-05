package com.youdu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.youdu.R;
import com.youdu.activity.base.BaseActivity;

/**
 * Created by kyrie on 2017/8/16.
 */

public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener{
    private ListView listView1;
    private ListView listView2;
    private ImageView imageView1;
    private ImageView imageView2;
    private int int1;//用于决定listview是否展开
    private int int2;//用于决定第二个listview是否绽开
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_layout);
        init();
    }

    private void init() {
        listView1=(ListView)findViewById(R.id.cookedlistview);
        listView1.setVisibility(View.GONE);
        listView2=(ListView)findViewById(R.id.collectlist);
        listView2.setVisibility(View.GONE);
        imageView1=(ImageView)findViewById(R.id.img);
        imageView1.setOnClickListener(this);
        imageView2=(ImageView)findViewById(R.id.img2);
        imageView2.setOnClickListener(this);
        int1=0;
        int2=0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img:
                int1+=1;
                if(int1%2==0){
                    listView1.setVisibility(View.GONE);
                }
                else {
                    listView1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img2:
                int2+=1;
                if(int2%2==0){
                    listView2.setVisibility(View.GONE);
                }
                else {
                    listView2.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

}
