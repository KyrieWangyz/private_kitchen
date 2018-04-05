package com.youdu.okhttptest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by cleverlin on 2017/7/5.
 */

public interface TestRetrofit {//猪肉
    @POST("foodApp/interface/searchByKeyword.php")
    @FormUrlEncoded
    Call<ResponseBody> TestData(@Field("keywords") String information);

    @POST("foodApp/interface/searchByName.php")
    @FormUrlEncoded
    Call<ResponseBody> caiming(@Field("name") String information);

}
