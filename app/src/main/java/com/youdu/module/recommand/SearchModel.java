package com.youdu.module.recommand;

import com.youdu.Bean.Article;
import com.youdu.Bean.ResultBean;
import com.youdu.http.ApiService;
import com.youdu.http.HttpManager;

import retrofit2.Callback;

/**
 * Created by kyrie on 2017/10/3.
 */

public class SearchModel{//getRetrofitPRC
    public void getSearchResult(Callback<Article<ResultBean>> callback, String keywords){
        HttpManager httpManager=new HttpManager();
        ApiService apiService=httpManager.getRetrofitPRC(httpManager.getOkHttpClient()).create(ApiService.class);
        apiService.getResult(keywords).enqueue(callback);
        //apiService.getResult(keywords).enqueue(callback);
    }
}
