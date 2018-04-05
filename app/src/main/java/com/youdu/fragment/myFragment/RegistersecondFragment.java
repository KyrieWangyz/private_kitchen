package com.youdu.fragment.myFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.youdu.R;
import com.youdu.fragment.BaseFragment;
import com.youdu.okhttp.listener.SendFragment;
import com.youdu.okhttp.listener.ToFragment;

/**
 * Created by kyrie on 2017/7/30.
 */

public class RegistersecondFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    private View mContentView;
    private ImageView imageView;
    private String sex;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {


        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_register_second, container, false);
        initView();
        return mContentView;

        // view = inflater.inflate(R.layout.fragment_home, container, false);
    }
    private void initView(){
        imageView=(ImageView)mContentView.findViewById(R.id.man);
        imageView.setOnClickListener(this);
        imageView=(ImageView)mContentView.findViewById(R.id.girl);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.man:
                sex="man";
                ((SendFragment)getActivity()).setsex(sex);
                if (getActivity() instanceof ToFragment) {
                    ((ToFragment) getActivity()).skip(2);
                }
                break;
            case R.id.girl:
                sex="girl";
                ((SendFragment)getActivity()).setsex(sex);
                if (getActivity() instanceof ToFragment) {
                    ((ToFragment) getActivity()).skip(2);
                }
        }
    }
}