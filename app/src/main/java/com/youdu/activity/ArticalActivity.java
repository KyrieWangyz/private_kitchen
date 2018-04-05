package com.youdu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.youdu.R;
import com.youdu.activity.base.BaseActivity;

/**
 * Created by kyrie on 2017/10/21.
 */

public class ArticalActivity extends BaseActivity{
    public static final String URL="url";
    private String urln;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_layout);
        initdate();
        WebView web= (WebView) findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());

        web.loadUrl(urln);
    }

    private void initdate() {
        Intent intent = getIntent();
        urln = intent.getStringExtra(URL);
    }
}
