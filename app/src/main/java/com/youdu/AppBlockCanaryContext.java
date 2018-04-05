package com.youdu;

import com.github.moduth.blockcanary.BlockCanaryContext;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class AppBlockCanaryContext extends BlockCanaryContext {

    @Override
    public String provideQualifier() {
        return "com.android.rxjava2study";
    }

    @Override
    public String provideNetworkType() {
        return "Wifi";
    }

    @Override
    public int provideBlockThreshold() {
        return 500;
    }

    @Override
    public String providePath() {
        return "/blockcanary/log";
    }
}