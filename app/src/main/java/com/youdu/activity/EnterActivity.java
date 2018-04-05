package com.youdu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.youdu.R;
import com.youdu.activity.base.BaseActivity;
import com.youdu.module.recommand.LoginModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;


/**
 * Created by kyrie on 2017/7/31.
 */

public class EnterActivity extends BaseActivity implements View.OnClickListener{
    private LoginModel loginModel;
    private ImageView login;
    private ImageView regist;
    private EditText name;
    private EditText password;
    private String rightname;
    private String rightpassword;
    private String text1;
    private String text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_layout);
        init();
        getdata();
    }
    private void init(){
        login=(ImageView)findViewById(R.id.login_img);
        login.setOnClickListener(this);
        regist=(ImageView)findViewById(R.id.register_img);
        regist.setOnClickListener(this);
        name=(EditText)findViewById(R.id.login_name);
        password=(EditText)findViewById(R.id.login_password);

    }
    private void getdata(){
        rightname="1";
        rightpassword="2";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register_img:
                Intent intent=new Intent(EnterActivity.this, LoginNewActivity.class);
                startActivity(intent);
            case R.id.login_img:
                requestRecommandData();
            break;

        }
    }

    private void requestRecommandData() {
        String stringname=name.getText().toString();
        String stringpassword=password.getText().toString();
        RequestCenter.requestloginstate(stringname,stringpassword,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                loginModel = (LoginModel) responseObj;
                //更新UI
                showSuccessView();

            }

            @Override
            public void onFailure(Object reasonObj) {
                //显示请求失败View


            }
        });
    }
    private void showSuccessView() {
        if (loginModel != null ) {//判断数据是否为空，不为空则服务器为我们返回了数据，真正去调用
            switch (loginModel.state){
                case "succeed":
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                    this.finish();
                    break;
                case "failed":
                    Toast toast=Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case "noExist":
                    Toast toast2=Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_SHORT);
                    toast2.show();
                    break;
            }

        }
        else {

        }
        Log.d("lodd", "showSuccessView: hjfj91919515");
        //  mListView.addHeaderView(new HomeHeaderLayout(mContext, mRecommandData.data.head));


    }


}
