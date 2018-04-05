package com.youdu.fragment.myFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.youdu.R;
import com.youdu.fragment.BaseFragment;
import com.youdu.myapplication.MainActivity;

/**
 * Created by kyrie on 2017/9/18.
 */

public class FriendGroupFragment extends BaseFragment implements View.OnClickListener{
    private View mContentView;
    private ImageView img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.activity_personal_center_new_layout, container, false);
        img= (ImageView) mContentView.findViewById(R.id.add);
        img.setOnClickListener(this);
        return mContentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                Intent in=new Intent(getActivity(), MainActivity.class);
                startActivity(in);
        }
    }
}
