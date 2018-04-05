package com.youdu.activity;

/**
 * Created by kyrie on 2017/5/8.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.fragment.myFragment.CalculateFragment;
import com.youdu.fragment.myFragment.FriendGroupFragment;
import com.youdu.fragment.myFragment.GetExpressFragment;
import com.youdu.fragment.myFragment.HistoryFragment;
import com.youdu.fragment.myFragment.HomeFragment;
import com.youdu.fragment.myFragment.MainFragment;
import com.youdu.fragment.myFragment.MessageFragment;
import com.youdu.fragment.myFragment.SupermarketFragment;
import com.youdu.service.UpdateProductService;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RelativeLayout layout_home;
    private RelativeLayout layout_message;
    private RelativeLayout layout_counter;
    private RelativeLayout layout_shop;
    private TextView mHomeP;
    private TextView mHomeT;
    private TextView mcounterT;
    private TextView mcounterP;
    private TextView mMessageP;
    private TextView mMessageT;
    private RelativeLayout home_acc_view;
    private RelativeLayout home_search_view;
    private TextView mshopP;

    private FragmentManager fm;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessFragment;
    private MainFragment mmainFragment;
    private FriendGroupFragment mfriendgroupFragment;
    private CalculateFragment calculateFragment;

    private SupermarketFragment supermarketFragment;

    private HistoryFragment mh;
    private GetExpressFragment gt;



    private LinearLayout title;
    private TextView issue_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // getWindow().setStatusBarColor(0xFF00a9FF);
        initView();
        //添加默认要显示的fragment
        mmainFragment =new MainFragment();
        fm = getFragmentManager();
        FragmentTransaction fragmentTransaction
                =fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout,mmainFragment);
        mHomeP.setBackgroundResource(R.mipmap.tab_home_pre);
        fragmentTransaction.commit();

        //startAllService();
    }



    private void startAllService() {
        Intent intent = new Intent(this, UpdateProductService.class);//发送我们的网络请求（不一定对，目前看来是这样）
        startService(intent);
    }


    private void initView() {
        title = (LinearLayout) findViewById(R.id.include);
        layout_home = (RelativeLayout) findViewById(R.id.layout_home);
        layout_home.setOnClickListener(this);
        layout_counter=(RelativeLayout)findViewById(R.id.layout_counter);
        layout_counter.setOnClickListener(this);
        layout_message = (RelativeLayout) findViewById(R.id.layout_message);
        layout_message.setOnClickListener(this);
        layout_shop= (RelativeLayout) findViewById(R.id.layout_shop);
        layout_shop.setOnClickListener(this);

        mHomeP = (TextView) findViewById(R.id.home_png);
        mcounterP=(TextView) findViewById(R.id.counter_png);
        mcounterT=(TextView)findViewById(R.id.counter_text);
        mHomeT = (TextView) findViewById(R.id.home_text);
        mMessageP = (TextView) findViewById(R.id.message_png);
        mMessageT = (TextView) findViewById(R.id.message_text);
        mshopP= (TextView) findViewById(R.id.shop_png);


        //  home_acc_view = (RelativeLayout) title.findViewById(R.id.home_acc_view);
     //   home_acc_view.setOnClickListener(this);
     //   home_search_view = (RelativeLayout) title.findViewById(R.id.home_search_view);
//        issue_btn=(TextView) findViewById(R.id.issue_btn);
//        issue_btn.setOnClickListener(this);

    }



    private void hideFragment(Fragment fragment,FragmentTransaction ft){
        if(fragment != null){
            ft.hide(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        switch (v.getId()){
            case R.id.layout_home:
                mHomeP.setBackgroundResource(R.mipmap.tab_home_pre);
                mcounterP.setBackgroundResource(R.mipmap.tab_calculator);
                mMessageP.setBackgroundResource(R.mipmap.tab_personal);
                mshopP.setBackgroundResource(R.mipmap.tab_shop);
                hideFragment(mfriendgroupFragment,fragmentTransaction);
                hideFragment(calculateFragment,fragmentTransaction);
                hideFragment(supermarketFragment,fragmentTransaction);
                if(mmainFragment==null){
                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout,mmainFragment);
                }else{
                    fragmentTransaction.show(mmainFragment);
                }
                break;
            case R.id.layout_counter:
                Log.d("click","layout_counter");
                mHomeP.setBackgroundResource(R.mipmap.tab_home);
                mcounterP.setBackgroundResource(R.mipmap.tab_calculator_pre);
                mshopP.setBackgroundResource(R.mipmap.tab_shop);
                mMessageP.setBackgroundResource(R.mipmap.tab_personal);
                hideFragment(mfriendgroupFragment,fragmentTransaction);
                hideFragment(mmainFragment,fragmentTransaction);
                hideFragment(supermarketFragment,fragmentTransaction);
                if(calculateFragment==null){
                    calculateFragment = new CalculateFragment();
                    fragmentTransaction.add(R.id.content_layout,calculateFragment);
                }else{
                    fragmentTransaction.show(calculateFragment);
                }
                break;



            case R.id.layout_shop:
                Log.d("click","layout_message");
                mHomeP.setBackgroundResource(R.mipmap.tab_home);
                mcounterP.setBackgroundResource(R.mipmap.tab_calculator);
                mMessageP.setBackgroundResource(R.mipmap.tab_personal);
                mshopP.setBackgroundResource(R.mipmap.tab_shop_pre);
                hideFragment(mmainFragment,fragmentTransaction);
                hideFragment(calculateFragment,fragmentTransaction);
                hideFragment(mfriendgroupFragment,fragmentTransaction);
                if(supermarketFragment==null){
                    supermarketFragment = new SupermarketFragment();
                    fragmentTransaction.add(R.id.content_layout,supermarketFragment);
                }else{
                    fragmentTransaction.show(supermarketFragment);
                }
                break;




            case R.id.layout_message:
                Log.d("click","layout_message");
                mHomeP.setBackgroundResource(R.mipmap.tab_home);
                mcounterP.setBackgroundResource(R.mipmap.tab_calculator);
                mMessageP.setBackgroundResource(R.mipmap.tab_personal_pre);
                mshopP.setBackgroundResource(R.mipmap.tab_shop);
                hideFragment(mmainFragment,fragmentTransaction);
                hideFragment(calculateFragment,fragmentTransaction);
                hideFragment(supermarketFragment,fragmentTransaction);
                if(mfriendgroupFragment==null){
                    mfriendgroupFragment = new FriendGroupFragment();
                    fragmentTransaction.add(R.id.content_layout,mfriendgroupFragment);
                }else{
                    fragmentTransaction.show(mfriendgroupFragment);
                }
                break;
//            case R.id.home_acc_view:
//                Log.d("click","home_acc_view");
//                Intent intent=new Intent(this,User.class);
//                startActivity(intent);
//                break;
//            case R.id.issue_btn:
//                Log.d("click","issue_btn");
//                Intent intent1=new Intent(this,TestMainActivity.class);
//                startActivity(intent1);
//                break;
//            default:
//                break;


        }
        fragmentTransaction.commit();
    }
}
