package com.youdu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youdu.R;


/**
 * Created by kyrie on 2017/5/9.
 */

public class VerifyActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView input;
    private EditText  mail;
    private EditText pass;
    private EditText verify;
    private TextView loading;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_layout);

        // getWindow().setStatusBarColor(0xFF00a9FF);

        //添加默认要显示的fragment
        init();
       // startAllService();


    }
//    private void startAllService() {
//        RequestCenter.postRequest(HttpConstants.wang, null, new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                //请求成功处理
//                VerifyModel name = (BaseSearchModel) responseObj;
//                if (searchModel.data.uptime != -1) {
//                    if (searchModel.data.uptime != spManager.getLong(SPManager.LAST_UPDATE_PRODUCT, -1)) {
//                        updateTime = searchModel.data.uptime;
//                        mHandler.sendEmptyMessage(PRODUCT_FLAG);
//                    } else {
//                        stopSelf();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Object reasonObj) {
//                //请求失败处理
//                if (DBDataHelper.getInstance().select(DBHelper.FUND_LIST_TABLE, null, null, null, ProductModel.class).size() > 0) {
//                    stopSelf();
//                } else {
//                    mHandler.sendEmptyMessageDelayed(UPDATE_FLAG, 1000);
//                }
//            }
//        }, BaseSearchModel.class);
//    }

    public void init(){
        input=(ImageView) findViewById(R.id.verify_input_pressed);
        input.setOnClickListener(this);
        mail=(EditText) findViewById(R.id.edittext_mail);
        pass=(EditText)findViewById(R.id.edittext_pass);
        verify=(EditText) findViewById(R.id.edittext_verify);
        loading=(TextView)findViewById(R.id.verify_loding);
        loading.setOnClickListener(this);

    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.verify_loding:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;


            case R.id.verify_input_pressed:


                String m=mail.getText().toString();
                String p=pass.getText().toString();
                if (m=="123"||p=="0" ) {
                    Intent intent1 = new Intent(this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
                }

        }
    }

}