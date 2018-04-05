package com.youdu.view.home;

import android.app.Fragment;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.youdu.R;

import java.util.List;

/**
 * Created by kyrie on 2017/10/26.
 */

public class CalculateListviewHeaderLayout extends RelativeLayout implements View.OnClickListener {

    private Context mContext;

    /**
     * UI
     */
    private ImageView mLoadingView;
    private ListView mListView;
    private View mContentView;

    private RollPagerView mRollPagerView;

    //   private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private RelativeLayout recommond;
    private RelativeLayout search;
    private RelativeLayout personal;
    private TextView name;
    private TextView kll;
    private RelativeLayout relativeLayout;


    private View mRootView;

    /**
     * Data
     */
   private String getname;
    private String getkll;



    public CalculateListviewHeaderLayout(Context context, AttributeSet attrs, String getname,String getkll) {
        super(context, attrs);
        mContext = context;
        this.getname=getname;
        this.getkll=getkll;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        recommond =  (RelativeLayout) inflater.inflate(R.layout.item_listview_calculate, this);

        name= (TextView) recommond.findViewById(R.id.food);
        kll= (TextView) recommond.findViewById(R.id.kll);

        name.setText(getname);
        kll.setText(getkll);
//        relativeLayout= (RelativeLayout) mRootView.findViewById(R.id.relativeLayout);
//        relativeLayout.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativeLayout:


                break;

        }
    }
}
