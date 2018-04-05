package com.youdu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.core.video.VideoAdContext;
import com.youdu.module.recommand.GetcaloriebykeywordsModel;
import com.youdu.util.ImageLoaderManager;

import java.util.ArrayList;

/**
 * Created by kyrie on 2017/10/27.
 */

public class CalculatelistAdapter extends BaseAdapter {


    private ViewHolder mViewHolder;
    private VideoAdContext mAdsdkContext;
    private ImageLoaderManager mImagerLoader;//异步图片加载工具
    private LayoutInflater mInflate;
    private Context mContext;
    private ArrayList<GetcaloriebykeywordsModel> mData;

    public CalculatelistAdapter(Context context, ArrayList<GetcaloriebykeywordsModel> data) {
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);
        mImagerLoader = ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }//获取到列表每一项的数据

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final GetcaloriebykeywordsModel value = (GetcaloriebykeywordsModel) getItem(position);//获取对应列上的数据
        //无tag时
        if (convertView == null) {
            //判断缓存是否为空，空时才来新建

            mViewHolder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.item_listview_calculate, parent, false);
            mViewHolder.textView2 = (TextView) convertView.findViewById(R.id.food);

            mViewHolder.textView3 = (TextView) convertView.findViewById(R.id.kll);




            convertView.setTag(mViewHolder);
        }//有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }


        mViewHolder.textView2.setText(value.name);
        Log.d("coursen", "getView: in");
        mViewHolder.textView3.setText(value.calorie);

        return convertView;
    }


    //动态添加ImageView

    //用来缓存我们所有的item
    private static class ViewHolder {
        //所有Card共有属性

        private TextView textView2;
        private TextView textView3;




    }
}
