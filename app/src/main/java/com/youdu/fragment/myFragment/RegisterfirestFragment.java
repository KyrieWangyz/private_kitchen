package com.youdu.fragment.myFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.youdu.R;
import com.youdu.fragment.BaseFragment;
import com.youdu.okhttp.listener.SendFragment;
import com.youdu.okhttp.listener.ToFragment;


/**
 * Created by kyrie on 2017/7/29.
 */

public class RegisterfirestFragment extends BaseFragment implements View.OnClickListener{

    private View mContentView;
    private ImageView imageView;
    private EditText editText1;
    private EditText editText2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {


            mContext = getActivity();
            mContentView = inflater.inflate(R.layout.fragment_register_first, container, false);
            initView();
            return mContentView;

       // view = inflater.inflate(R.layout.fragment_home, container, false);
    }
    private void initView(){
        imageView=(ImageView)mContentView.findViewById(R.id.next);
        imageView.setOnClickListener(this);
        editText1=(EditText)mContentView.findViewById(R.id.yourid);
        editText2=(EditText)mContentView.findViewById(R.id.yourpassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                ((SendFragment)getActivity()).setstr(editText1.getText().toString(),editText2.getText().toString());
            if (getActivity() instanceof ToFragment) {
                ((ToFragment) getActivity()).skip(1);

            }

        }
    }
}
