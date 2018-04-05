package myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youdu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.circle_friend)
    RecyclerView recyclerContent;
    RecyclerViewAdapter adapter;
    @BindView(R.id.bg_circle)
    ImageView bgCircle;
    private static final int TEXT = 1001;
    @BindView(R.id.already)
    LinearLayout already;
    @BindView(R.id.re_already)
    RecyclerView reAlready;
    @BindView(R.id.collection)
    LinearLayout collection;
    @BindView(R.id.re_collection)
    RecyclerView reCollection;
    List<CircleBean> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_friend);
        ButterKnife.bind(this);
        //        if(getIntent()!=null){
        //            byte[] b= (byte[]) getIntent().getSerializableExtra("bitmap");
        //            Bitmap bmp= BitmapFactory.decodeByteArray(b, 0, b.length);
        //            bgCircle.setImageBitmap(bmp);
        //        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initBean();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent,1001);

            }
        });
    }

    private void initBean() {
        CircleBean bean1=new CircleBean();
        CircleBean bean2=new CircleBean();
        bean1.setContent("哈哈哈 好开心 今天煮了一顿饭可口的饭 一起吃吗!!!!!!!!");
        bean1.setNick_name("曲奇阿姨1");
        bean2.setContent("哈哈哈 好开心 今天煮了一顿饭可口的饭 一起吃吗------");
        bean2.setNick_name("曲奇阿姨2");
        list.add(bean1);
        list.add(bean2);
        initContentRecycy();
    }

    private void initContentRecycy() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerContent.setLayoutManager(linearLayoutManager);
        recyclerContent.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerViewAdapter(this, list);
        recyclerContent.setAdapter(adapter);

    }
    CircleBean bean=new CircleBean();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1001) {
            if (data != null) {
                byte[] b1 = (byte[]) data.getSerializableExtra("picture1");
                byte[] b2 = (byte[]) data.getSerializableExtra("picture2");
                byte[] b3 = (byte[]) data.getSerializableExtra("picture3");
                bean.setContent(data.getStringExtra("content"));
                bean.setNick_name(data.getStringExtra("nickname"));
             //   bean.setHeadimg(null);
                if(b1!=null){
                    Bitmap bmp1 = BitmapFactory.decodeByteArray(b1, 0, b1.length);
                    bean.setOne(bmp1);
                }
                if(b2!=null){
                    Bitmap bmp2 = BitmapFactory.decodeByteArray(b2, 0, b2.length);
                    bean.setTwo(bmp2);
                }
                if(b3!=null){
                    Bitmap bmp3 = BitmapFactory.decodeByteArray(b3, 0, b3.length);
                    bean.setThree(bmp3);
                }
                list.add(bean);
                adapter.notifyDataSetChanged();
            }

        }


    }
}


