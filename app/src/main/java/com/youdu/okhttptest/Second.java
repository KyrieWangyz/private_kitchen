package com.youdu.okhttptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.youdu.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cleverlin on 2017/7/14.
 */

public class Second extends AppCompatActivity {
    @BindView(R.id.foodImg)
    ImageView foodImg;
    @BindView(R.id.caiming)
    TextView caidemingzi;
    String caiming;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        caiming = intent.getStringExtra("caiming");
        Log.d("caiming", caiming);
        getRetrofit(caiming);
    }

    private void getRetrofit(final String caiming) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestRetrofit testRetrofit = retrofit.create(TestRetrofit.class);
        Call<ResponseBody> data = testRetrofit.caiming(caiming);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String url = null;
                    try {
                        url = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Picasso.with(Second.this).load(url).into(foodImg);
                    caidemingzi.setText(caiming);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Toast.makeText(TestMainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
