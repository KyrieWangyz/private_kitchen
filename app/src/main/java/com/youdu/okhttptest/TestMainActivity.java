package com.youdu.okhttptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.youdu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestMainActivity extends AppCompatActivity {


    @BindView(R.id.food1)
    EditText food1;
    @BindView(R.id.food2)
    EditText food2;
    @BindView(R.id.food3)
    EditText food3;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.main_recylist)
    RecyclerView mainRecylist;
    private List<String> mDataListnew=new ArrayList<String>(); ;

    private OkHttpClient httpClient;
    private MyAdapter myAdapter;
    String[] int_Array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        httpClient = new OkHttpClient();


    }

    private void getInfo(String last) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestRetrofit testRetrofit = retrofit.create(TestRetrofit.class);
        Call<ResponseBody> data = testRetrofit.TestData(last);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String json = null;
                    try {
                        json = response.body().string();
                        String[] str_Array = json.split("\"");
                        int len = str_Array.length;//取得数组的长度

                        int_Array = new String[len];//定义整型数组
                        int k = 0;
                        for (int i = 0; i < len; i++) {
                            if (str_Array[i].equals(",") || str_Array[i].equals("[") || str_Array[i].equals("]")) {
                            } else {
                                int_Array[k] = str_Array[i];
                                k++;
                            }
                        }

                        for (int i = 0; i < int_Array.length; i++) {
                            mDataListnew.add(int_Array[i]);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("succsss","chenggong");

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("failure","shibai");
                Toast.makeText(TestMainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @OnClick(R.id.submit)
    public void onClick() {
        Log.d("click","ok");
        String first = food1.getText().toString().trim();
        String second = food2.getText().toString().trim();
        String third = food3.getText().toString().trim();
        String last = first + "-" + second + "-" + third;
        getInfo(last);
        Log.d("getok","getok");
        mainRecylist.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(mDataListnew);
        mainRecylist.setAdapter(myAdapter);
        mainRecylist.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mainRecylist.addItemDecoration(new DividerItemDecoration(
                TestMainActivity.this, DividerItemDecoration.VERTICAL));
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                Log.d("click",int_Array[position]+"");
                Intent intent=new Intent();
                intent.putExtra("caiming",int_Array[position]);
                intent.setClass(TestMainActivity.this,Second.class);
                startActivity(intent);
            }
        });
        Log.d("getfinish","finish");
    }
}
