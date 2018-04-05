package com.youdu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.core.video.VideoAdContext;
import com.youdu.module.recommand.HotvegetablenModel;
import com.youdu.util.ImageLoaderManager;

import java.util.ArrayList;

/**
 * Created by kyrie on 2017/9/27.
 */

public class HotvegetableAdapter extends BaseAdapter {


    private ViewHolder mViewHolder;
    private VideoAdContext mAdsdkContext;
    private ImageLoaderManager mImagerLoader;//异步图片加载工具
    private LayoutInflater mInflate;
    private Context mContext;
    private ArrayList<HotvegetablenModel> mData;

    private String one="1";
    private String two="2";
    private String three="3";

    public HotvegetableAdapter(Context context, ArrayList<HotvegetablenModel> data) {
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

        final HotvegetablenModel value = (HotvegetablenModel) getItem(position);//获取对应列上的数据
        //无tag时
        if (convertView == null) {
            //判断缓存是否为空，空时才来新建

            mViewHolder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.hotvegetable_item, parent, false);
            mViewHolder.vegetable = (TextView) convertView.findViewById(R.id.recycle_text);
            mViewHolder.time = (TextView) convertView.findViewById(R.id.time1);
            mViewHolder.img= (ImageView) convertView.findViewById((R.id.recycle_img));
            mViewHolder.img1=(ImageView)convertView.findViewById(R.id.img1);
            mViewHolder.img2=(ImageView)convertView.findViewById(R.id.img2);
            mViewHolder.img3=(ImageView)convertView.findViewById(R.id.img3);
            convertView.setTag(mViewHolder);
        }//有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mImagerLoader.displayImage(mViewHolder.img, value.img);
        mViewHolder.vegetable.setText(value.name);
        mViewHolder.time.setText(value.time);
        //mViewHolder.footer.setText(value.remark);
        if(value.difficulty.equals("1"))
        {
            mViewHolder.img1.setBackgroundResource(R.mipmap.recommend_icon_star);
        }
        else if(value.difficulty.equals("2")){
            mViewHolder.img1.setBackgroundResource(R.mipmap.recommend_icon_star);
            mViewHolder.img2.setBackgroundResource(R.mipmap.recommend_icon_star);
        }
        else if(value.difficulty.equals("2")){
            mViewHolder.img1.setBackgroundResource(R.mipmap.recommend_icon_star);
            mViewHolder.img2.setBackgroundResource(R.mipmap.recommend_icon_star);
            mViewHolder.img3.setBackgroundResource(R.mipmap.recommend_icon_star);
        }


        return convertView;
    }


    //动态添加ImageView

    //用来缓存我们所有的item
    private static class ViewHolder {
        //所有Card共有属性
        private ImageView img;
        private TextView time;
        private TextView vegetable;
        private ImageView img1;
        private ImageView img2;
        private ImageView img3;
        private LinearLayout mProductLayout;



    }
}
