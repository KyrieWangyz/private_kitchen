package com.youdu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.core.video.VideoAdContext;
import com.youdu.module.recommand.ArticleModel;
import com.youdu.util.ImageLoaderManager;

import java.util.ArrayList;

/**
 * Created by kyrie on 2017/10/21.
 */

public class ArticleAdapter extends BaseAdapter {


    private ViewHolder mViewHolder;
    private VideoAdContext mAdsdkContext;
    private ImageLoaderManager mImagerLoader;//异步图片加载工具
    private LayoutInflater mInflate;
    private Context mContext;
    private ArrayList<ArticleModel> mData;



    public ArticleAdapter(Context context, ArrayList<ArticleModel> data) {
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




        final ArticleModel value = (ArticleModel) getItem(position);//获取对应列上的数据
        //无tag时
        if (convertView == null) {
            //判断缓存是否为空，空时才来新建

            mViewHolder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.item_article_layout, parent, false);
            mViewHolder.name= (TextView) convertView.findViewById(R.id.name);
            mViewHolder.picture= (ImageView) convertView.findViewById(R.id.picture);





            convertView.setTag(mViewHolder);
        }//有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mImagerLoader.displayImage(mViewHolder.picture, value.avatar);

        mViewHolder.name.setText(value.name);

        //mViewHolder.footer.setText(value.remark);
        return convertView;
    }


    //动态添加ImageView

    //用来缓存我们所有的item
    private static class ViewHolder {
        //所有Card共有属性
        private ImageView picture;


        private TextView name;



    }
}
