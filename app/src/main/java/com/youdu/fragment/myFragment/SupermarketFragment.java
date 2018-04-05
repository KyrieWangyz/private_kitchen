package com.youdu.fragment.myFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.youdu.R;
import com.youdu.fragment.BaseFragment;

/**
 * Created by kyrie on 2017/10/28.
 */

public class SupermarketFragment  extends BaseFragment{
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_article_layout, container, false);
        WebView web= (WebView) view.findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());

        web.loadUrl("https://pages.tmall.com/wow/act/18132/main-100556?spm=a222r.10398678.leftnav007.3.2ed38fa9Zyc4tZ&wh_weex=true&acm=lb-zebra-272073-2573184.1003.4.2300656&scm=1003.4.lb-zebra-272073-2573184.OTHER_15072334079483_2300656");
        return view;
    }


}
