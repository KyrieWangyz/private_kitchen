package com.youdu.fragment.myFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.activity.SearchFromCalculateActivity;
import com.youdu.fragment.BaseFragment;

/**
 * Created by kyrie on 2017/10/26.
 */

public class CalculateFragment extends BaseFragment implements View.OnClickListener{
    private String addstring="ada";
    private String string="ttt";
    private View view;
    private ImageView add_button;
    private ListView listView;
    private TextView clean;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        add(addstring);
        add(string);



    }

    private void init(View view) {
        add_button= (ImageView) view.findViewById(R.id.add);
        add_button.setOnClickListener(this);
        listView= (ListView) view.findViewById(R.id.listview);
        clean= (TextView) view.findViewById(R.id.clean);
        clean.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        view = inflater.inflate(R.layout.activity_calculate_layout, container, false);
        init(view);
        return  view;

    }
    public void add(String addstring){
        this.addstring=addstring;
        if(string==null||"".equals(string)){
            string=addstring;
        }
        else {
            string=string+"-"+addstring;

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                Intent intent=new Intent(getActivity(), SearchFromCalculateActivity.class);
                startActivity(intent);
                break;
        }
    }
}