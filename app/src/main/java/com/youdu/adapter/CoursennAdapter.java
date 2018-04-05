package com.youdu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.core.video.VideoAdContext;
import com.youdu.module.recommand.DouguoModel;
import com.youdu.util.ImageLoaderManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kyrie on 2017/7/14.
 */

public class CoursennAdapter extends BaseAdapter {


    private ViewHolder mViewHolder;
    private VideoAdContext mAdsdkContext;
    private ImageLoaderManager mImagerLoader;//异步图片加载工具
    private LayoutInflater mInflate;
    private Context mContext;
    private ArrayList<DouguoModel> mData;

    public CoursennAdapter(Context context, ArrayList<DouguoModel> data) {
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

    public int getItemViewType(int position) {//通知adapter要使用哪种类型的item去加载数据
        DouguoModel value = (DouguoModel) getItem(position);
        return value.orderid;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final DouguoModel value = (DouguoModel) getItem(position);//获取对应列上的数据
        //无tag时
        if (convertView == null) {
            //判断缓存是否为空，空时才来新建

            mViewHolder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.item_product_test_layout, parent, false);
            mViewHolder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
            mViewHolder.logo = (CircleImageView) convertView.findViewById(R.id.icon);
            mViewHolder.textView3 = (TextView) convertView.findViewById(R.id.textView3);

            mViewHolder.name = (TextView) convertView.findViewById(R.id.name);
           mViewHolder.picture = (ImageView) convertView.findViewById(R.id.picture);





            convertView.setTag(mViewHolder);
        }//有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mImagerLoader.displayImage(mViewHolder.picture, value.picture);
        mImagerLoader.displayImage(mViewHolder.logo, value.icon);
        // mViewHolder.address.setText(value.name);
        mViewHolder.textView2.setText(value.vegetable);
        Log.d("coursen", "getView: in");
        mViewHolder.textView3.setText(value.message);
        mViewHolder.name.setText("       王允璋老教授");

        //mViewHolder.footer.setText(value.remark);
        return convertView;
    }


    //动态添加ImageView

    //用来缓存我们所有的item
    private static class ViewHolder {
        //所有Card共有属性
        private ImageView picture;
        private CircleImageView logo;
        private TextView textView2;
        private TextView textView3;

        private TextView name;

        private LinearLayout mProductLayout;



    }
}
