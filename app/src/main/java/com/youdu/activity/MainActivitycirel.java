package com.youdu.activity;

/**
 * Created by kyrie on 2017/7/27.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.youdu.R;
import com.youdu.view.CircleProgressView;

public class MainActivitycirel extends Activity implements View.OnClickListener {

    private CircleProgressView mCircleBar;
    private CircleProgressView mCirclebarleft;
    private CircleProgressView mCirclebarright;
    private ImageView record,calcution,alter,recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cirel);

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
  //      getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initViews() {

        mCircleBar = (CircleProgressView) findViewById(R.id.circleProgressbar);

        mCircleBar.setProgress(80);
        mCirclebarleft=(CircleProgressView)findViewById(R.id.circleProgressbarleft);
        mCirclebarleft.setProgress(30);
        mCirclebarright=(CircleProgressView)findViewById(R.id.circleProgressbarright);
        mCirclebarright.setProgress(60);
        record= (ImageView) findViewById(R.id.personal_icon_record);
        record.setOnClickListener(this);
        calcution= (ImageView) findViewById(R.id.personal_icon_calculator);
        calcution.setOnClickListener(this);
        alter= (ImageView) findViewById(R.id.personal_icon_alter);
        alter.setOnClickListener(this);
        recipe= (ImageView) findViewById(R.id.personal_icon_recipe);
        recipe.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.personal_icon_record:
            Intent intent=new Intent(MainActivitycirel.this,DiaryActivity.class);
            startActivity(intent);
            break;
            case R.id.personal_icon_calculator:
                Intent intent2=new Intent(MainActivitycirel.this,CalculateActivity.class);
                startActivity(intent2);
                break;

            case R.id.personal_icon_alter:
                Intent intent3=new Intent(MainActivitycirel.this,ChangeGoalActivity.class);
                startActivity(intent3);
                break;


            case R.id.personal_icon_recipe:
                Intent intent1=new Intent(MainActivitycirel.this,PrivatecipenActivity.class);
                startActivity(intent1);
                break;
        }
    }
}