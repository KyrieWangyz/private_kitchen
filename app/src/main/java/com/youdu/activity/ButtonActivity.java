package com.youdu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youdu.ButtonTest.CustomFourImageView;
import com.youdu.ButtonTest.ObservableScrollView;
import com.youdu.ButtonTest.OnScrollChangedListener;
import com.youdu.R;
import com.youdu.adapter.StepAdapter;
import com.youdu.module.recommand.HotvegetableModel;
import com.youdu.module.recommand.HotvegetablenutrientModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;
import com.youdu.util.ImageLoaderManager;


/**
 * Created by kyrie on 2017/8/25.
 */

public class ButtonActivity extends Activity implements OnScrollChangedListener,View.OnClickListener{

    private HotvegetableModel model;
    private String stepText[]={"3","1","2"};




    private ObservableScrollView scrollView= null;
    private TranslateAnimation animation1, animation2;
    private Button startCook;
    private RelativeLayout.LayoutParams params;
    private int top1;
    public static final String NAME="keywords";
    public static final String IMG="img";
    public static final String DIFFICULTY="difficulty";
    public static final String TIME="time";
    public static final String MATERIAL="material";
    public static final String NUTRIENT="nutrient";
    public static final String AXUNGE="axunge";
    public static final String CARBOHYDRATE="carbohydrate";
    public static final String CALORIE="calorie";

    public static final String IMG1="img1";
    public static final String IMG2="img2";
    public static final String IMG3="img3";
    public static final String IMG4="img4";
    public static final String TEXT1="text1";
    public static final String TEXT2="text2";
    public static final String TEXT3="text3";
    public static final String TEXT4="text4";



    private ImageLoaderManager mImagerLoader;
    private String getname;
    private String getimg;
    private String gettime;
    private String getdifficulty;
    private String getmaterial;
    private String getCalorie;
    private String textone;
    private String texttwo;
    private String textthird;
    private String textfour;
    private String imgone;
    private String imgtwo;
    private String imgthird;
    private String imgfour;

 //   private String []getnutrient;

    private HotvegetableModel mRecommandData;
    private HotvegetablenutrientModel nutrientmodel;
    private CustomFourImageView img;
    private TextView name;
    private TextView remark3;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    private TextView kll;
    private TextView dbz;
    private TextView zf;
    private TextView ts;
    private StepAdapter madapter;
    private ListView mlistview;
    private ImageView star1;
    private ImageView star2;
    private ImageView star3;
    private ImageView star4;
    private ImageView star5;
    private CustomFourImageView customFourImageView;
    int statusBarHeigh;
    Rect frame=new Rect();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setFullScreen();//设置全屏

        setContentView(R.layout.activity_teach);
getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeigh=frame.top;

        mImagerLoader = ImageLoaderManager.getInstance(this);////////////////////////////////////////////////////
        init();
        initData();
        setduration();
 //       requestRecommandData();

//        try {
//            setlayout();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        setlayoutn();
        requestRecommandData();
    }

    private void setlayoutn() {
        name.setText(getname);
        setStarNumber(getdifficulty);


        Thread t = new Thread( new MyRunable());
        t.start();
        try {
            System.out.println("join前");
            t.join(1000);
            System.out.println("join后");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }



    }



    class MyRunable implements Runnable {
        @Override
        public void run() {
            customFourImageView.getbitmap(getimg);
        }


    };




    private void initData() {
            Intent intent = getIntent();
            getname = intent.getStringExtra(NAME);
        getdifficulty=intent.getStringExtra(DIFFICULTY);
        getimg=intent.getStringExtra(IMG);
        gettime=intent.getStringExtra(TIME);
        getmaterial=intent.getStringExtra(MATERIAL);
        getCalorie=intent.getStringExtra(CALORIE);
        textone=intent.getStringExtra(TEXT1);
        texttwo=intent.getStringExtra(TEXT2);
        textthird=intent.getStringExtra(TEXT3);
        textfour=intent.getStringExtra(TEXT4);
        imgone=intent.getStringExtra(IMG1);
        imgtwo=intent.getStringExtra(IMG2);
        imgthird=intent.getStringExtra(IMG3);
              imgfour=intent.getStringExtra(IMG4);
    }



    private void setduration() {
        animation1 = new TranslateAnimation(0, 0, 0, dip2px(getApplicationContext(),260));//从上往下
        animation1.setDuration(600);//设置动画持续时间
        animation2 = new TranslateAnimation(0, 0, 0, dip2px(getApplicationContext(),-260));//从下往上
        animation2.setDuration(500);//设置动画持续时间

    }

    private void init() {
        customFourImageView=(CustomFourImageView)findViewById(R.id.customfourimageview);
        star1= (ImageView) findViewById(R.id.star1);
        star2= (ImageView) findViewById(R.id.star2);
        star3= (ImageView) findViewById(R.id.star3);
        star4= (ImageView) findViewById(R.id.star4);
        star5= (ImageView) findViewById(R.id.star5);
        name= (TextView) findViewById(R.id.name);
        kll= (TextView) findViewById(R.id.kll_text);
        zf= (TextView) findViewById(R.id.zf_text);
        dbz= (TextView) findViewById(R.id.dbz_text);
        ts= (TextView) findViewById(R.id.ts_text);
        scrollView= (ObservableScrollView) findViewById(R.id.activity_main);
        img= (CustomFourImageView) findViewById(R.id.customfourimageview);
        startCook= (Button) findViewById(R.id.startCook);
        startCook.setOnClickListener(this);
        scrollView.setScrollViewListener(this);
        params= (RelativeLayout.LayoutParams) startCook.getLayoutParams();
        remark3= (TextView) findViewById(R.id.remark3);
        img1= (ImageView) findViewById(R.id.img1);
        img2= (ImageView) findViewById(R.id.img2);
        img3= (ImageView) findViewById(R.id.img3);
        img4= (ImageView) findViewById(R.id.img4);
        text1= (TextView) findViewById(R.id.text1);
        text2=(TextView)findViewById(R.id.text2);
                text3= (TextView) findViewById(R.id.text3);
        text4= (TextView) findViewById(R.id.text4);

    }


    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    int i=0;
    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {

        top1=startCook.getTop();
//        int ddd=dip2px(getApplicationContext(),228);
        Log.d("gettop",top1+"----"+dip2px(getApplicationContext(),228)+"----"+statusBarHeigh);
        if (y!=statusBarHeigh && i==0){
            i=1;
            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    startCook.clearAnimation();
                    params.setMargins(
                            dip2px(getApplicationContext(),268),
                            dip2px(getApplicationContext(),488+statusBarHeigh),
                            dip2px(getApplicationContext(),20),
                            dip2px(getApplicationContext(),80));
                    startCook.setLayoutParams(params);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if(top1==dip2px(getApplicationContext(),228+statusBarHeigh))

                startCook.startAnimation(animation1);//动画监听
        }
        if (y==statusBarHeigh && i==1){
            i=0;
            animation2.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    startCook.clearAnimation();
                    params.setMargins(
                            dip2px(getApplicationContext(),268),
                            dip2px(getApplicationContext(),228+statusBarHeigh),
                            dip2px(getApplicationContext(),20),
                            dip2px(getApplicationContext(),80));
                    startCook.setLayoutParams(params);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if(top1==dip2px(getApplicationContext(),488+statusBarHeigh))
                startCook.startAnimation(animation2);
        }

    }
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startCook:
                Intent intent=new Intent(this,ProgressActivity.class);
                intent.putExtra(ProgressActivity.NAME,getname);

                intent.putExtra(ProgressActivity.STEP1,stepText[0]);
                intent.putExtra(ProgressActivity.STEP2,stepText[1]);
                intent.putExtra(ProgressActivity.STEP3,stepText[2]);
                startActivity(intent);

        }
    }


    public void setStarNumber(String starNumber) {
        switch (starNumber){
            case "1":
                star2.setVisibility(View.INVISIBLE);
                star3.setVisibility(View.INVISIBLE);
                star4.setVisibility(View.INVISIBLE);
                star5.setVisibility(View.INVISIBLE);
                break;
            case "2":
                star3.setVisibility(View.INVISIBLE);
                star4.setVisibility(View.INVISIBLE);
                star5.setVisibility(View.INVISIBLE);
                break;
            case "3":
                star4.setVisibility(View.INVISIBLE);
                star5.setVisibility(View.INVISIBLE);
                break;
            case "4":
                star5.setVisibility(View.INVISIBLE);
                break;



        }
    }


    private void requestRecommandData() {

        RequestCenter.requeststep(getname,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                model = (HotvegetableModel) responseObj;
                //更新UI
                showSuccessView();

            }

            @Override
            public void onFailure(Object reasonObj) {
                //显示请求失败View
                showErrorView();

            }
        });




    }
    private void showSuccessView() {


//        String nu=model.result.get(0).img;
//
//        HotvegetablenModel modeln=(HotvegetablenModel) model.result.get(0);

        String uu=model.result.get(0).material;
//        Toast.makeText(getApplicationContext(), uu,
//                Toast.LENGTH_SHORT).show();
        String s = model.result.get(0).material;
        String s1 = s.replace(">","     ");
        remark3.setText(s1);
        if(model !=null){
            for(int i=0;i<3;i++){
                stepText[i]=model.result.get(0).step.get(i).stepName;
//                Toast.makeText(getApplicationContext(), stepText[i],
//                        Toast.LENGTH_SHORT).show();
            }
            text1.setText(textone);
            text2.setText(texttwo);
            text3.setText(textthird);
            text4.setText(textfour);
            mImagerLoader.displayImage(img1, imgone);
            mImagerLoader.displayImage(img2, imgtwo);
            mImagerLoader.displayImage(img3, imgthird);
            mImagerLoader.displayImage(img4, imgfour);



        }
        else {
            showErrorView();
        }

    }

    private void showErrorView() {
        Log.d("lodd", "showSuccessView: hjfj91919515");

    }






}
