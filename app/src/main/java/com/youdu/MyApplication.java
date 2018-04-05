package com.youdu;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;

/**
 * Created by Administrator on 2017/3/26.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {

        SpeechUtility.createUtility(MyApplication.this,"appid="+getString(R.string.app_id));
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance(){

        return instance;
    }

}
