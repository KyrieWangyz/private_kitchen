package com.youdu.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.youdu.R;

/**
 * Created by Administrator on 2017/3/8.
 */

public class RollViewPagerAdapter extends LoopPagerAdapter {

    private int[] img = {R.drawable.kfc1,R.drawable.kfc2,R.drawable.kfc3};
    private int count = img.length;

    public RollViewPagerAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        final int picNo = position + 1;
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(img[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("rollViewPager",position+"");
            }
        });
        return  view;
    }

    @Override
    protected int getRealCount() {
        return count;
    }
}
