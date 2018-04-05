package com.youdu.application;

import android.app.Application;
import android.content.Context;

import com.github.moduth.blockcanary.BlockCanary;
import com.iflytek.cloud.SpeechUtility;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.youdu.AppBlockCanaryContext;
import com.youdu.R;
import com.youdu.core.AdSDKManager;
import com.youdu.share.ShareManager;

import cn.jpush.android.api.JPushInterface;


/**
 * *******************************************************
 *
 * @文件名称：CommonApplication.java
 * @文件作者：renzhiqiang
 * @创建时间：2015年11月19日 下午10:38:25
 * @文件描述：App容器
 * @修改历史：2015年11月19日创建初始版本 ********************************************************
 */
public class ImoocApplication extends Application {

    private static ImoocApplication mApplication = null;

    private RefWatcher refWatcher;//关于内存泄漏

    @Override
    public void onCreate() {
        SpeechUtility.createUtility(ImoocApplication.this,"appid="+getString(R.string.app_id));
        super.onCreate();
        mApplication = this;
        initShareSDK();
        initJPush();
        initAdSDK();

        refWatcher= setupLeakCanary();//2

        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }

    public static ImoocApplication getInstance() {
        return mApplication;
    }

    public void initShareSDK() {
        ShareManager.initSDK(this);
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    private void initAdSDK() {
        AdSDKManager.init(this);
    }


    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);//1
    }

    public static RefWatcher getRefWatcher(Context context) {
        ImoocApplication leakApplication = (ImoocApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }
}