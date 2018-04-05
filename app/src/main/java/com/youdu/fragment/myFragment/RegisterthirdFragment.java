package com.youdu.fragment.myFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.youdu.R;
import com.youdu.activity.MainActivity;
import com.youdu.fragment.BaseFragment;
import com.youdu.okhttp.listener.SendFragment;

/**
 * Created by kyrie on 2017/7/30.
 */

public class RegisterthirdFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    private View mContentView;
    private RelativeLayout finish;
   private String fragmentfirstid;
    private String fragmentfirstpassword;
    private String sex;
    private String message;
//    private String height;
//    private String weight;
//    private String age;
//    private RegisterfirestFragment fragment1;
//    private  EditText editText;
//    private  EditText editText1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {


        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_register_third, container, false);
        initView();


        return mContentView;

        // view = inflater.inflate(R.layout.fragment_home, container, false);
    }
    private void initView(){
        finish=(RelativeLayout) mContentView.findViewById(R.id.finish);
        finish.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finish:
                fragmentfirstid=((SendFragment)getActivity()).getstr();
                fragmentfirstpassword=((SendFragment)getActivity()).getstr2();
                sex=((SendFragment)getActivity()).getsex();
                message=fragmentfirstid+"/"+fragmentfirstpassword+"/"+sex;
                Toast.makeText(getActivity().getApplicationContext(), message,
                        Toast.LENGTH_SHORT).show();


                Intent intent=new Intent(mContext, MainActivity.class);
                startActivity(intent);
                getActivity().finish();

        }
    }
}