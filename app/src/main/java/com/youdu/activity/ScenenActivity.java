package com.youdu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.youdu.R;
import com.youdu.activity.base.BaseActivity;

/**
 * Created by kyrie on 2017/10/27.
 */

public class ScenenActivity extends BaseActivity implements View.OnClickListener{
    private ImageView img0;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private EditText editext;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_layout);
       init();
    }

    private void init() {
        img0= (ImageView) findViewById(R.id.sr);
        img0.setOnClickListener(this);
        img1=(ImageView)findViewById(R.id.yh);
        img1.setOnClickListener(this);
        img2= (ImageView) findViewById(R.id.jt);
        img2.setOnClickListener(this);
        img3= (ImageView) findViewById(R.id.sk);
        img3.setOnClickListener(this);
        img4= (ImageView) findViewById(R.id.hg);
        img4.setOnClickListener(this);
        img5= (ImageView) findViewById(R.id.yx);
        img5.setOnClickListener(this);
        editext= (EditText) findViewById(R.id.editText);

    }

    @Override
    public void onClick(View v) {
        number=editext.getText().toString();
        Intent intent=new Intent(this,SceneActivity.class);
        switch (v.getId()){
            case R.id.sr:
                intent.putExtra(SceneActivity.KEYWORDS,"生日");
                intent.putExtra(SceneActivity.NUMBER,number);
                startActivity(intent);
                break;
            case R.id.yh:
                intent.putExtra(SceneActivity.KEYWORDS,"烛光晚餐");
                intent.putExtra(SceneActivity.NUMBER,number);
                startActivity(intent);
                break;
            case R.id.jt:
                intent.putExtra(SceneActivity.KEYWORDS,"年夜饭");
                intent.putExtra(SceneActivity.NUMBER,number);
                startActivity(intent);
                break;
            case R.id.sk:
                intent.putExtra(SceneActivity.KEYWORDS,"烧烤");
                intent.putExtra(SceneActivity.NUMBER,number);
                startActivity(intent);
                break;
            case R.id.hg:
                intent.putExtra(SceneActivity.KEYWORDS,"聚餐");
                intent.putExtra(SceneActivity.NUMBER,number);
                startActivity(intent);
                break;
            case R.id.yx:
                intent.putExtra(SceneActivity.KEYWORDS,"夜宵");
                intent.putExtra(SceneActivity.NUMBER,number);
                startActivity(intent);
                break;
        }
    }
}
