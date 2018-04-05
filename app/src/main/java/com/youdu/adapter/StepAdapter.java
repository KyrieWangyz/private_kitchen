package com.youdu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.core.video.VideoAdContext;
import com.youdu.module.recommand.DouguoModel;
import com.youdu.module.recommand.HotvegetablestepModel;
import com.youdu.util.ImageLoaderManager;

import java.util.ArrayList;

/**
 * Created by kyrie on 2017/10/1.
 */

public class StepAdapter extends BaseAdapter {private ViewHolder mViewHolder;
    private VideoAdContext mAdsdkContext;
    private ImageLoaderManager mImagerLoader;//异步图片加载工具
    private LayoutInflater mInflate;
    private Context mContext;
    private ArrayList<HotvegetablestepModel> mData;

    public StepAdapter(Context context, ArrayList<HotvegetablestepModel> data) {
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

        final HotvegetablestepModel value = (HotvegetablestepModel) getItem(position);//获取对应列上的数据
        //无tag时
        if (convertView == null) {
            //判断缓存是否为空，空时才来新建

            mViewHolder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.item_step_layout, parent, false);
            mViewHolder.step_time = (TextView) convertView.findViewById(R.id.step_time);
            mViewHolder.step_name = (TextView) convertView.findViewById(R.id.step_name);
            mViewHolder.picture = (ImageView) convertView.findViewById(R.id.step_img);





            convertView.setTag(mViewHolder);
        }//有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mImagerLoader.displayImage(mViewHolder.picture, value.stepImg);

        // mViewHolder.address.setText(value.name);
        mViewHolder.step_time.setText(value.stepTime);
        Log.d("coursen", "getView: in");
        mViewHolder.step_name.setText(value.stepName);


        //mViewHolder.footer.setText(value.remark);
        return convertView;
    }


    //动态添加ImageView

    //用来缓存我们所有的item
    private static class ViewHolder {
        //所有Card共有属性
        private ImageView picture;
        private TextView step_name;
        private TextView step_time;





    }
}
