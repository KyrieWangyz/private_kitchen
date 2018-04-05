package com.youdu.okhttptest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youdu.R;

import java.util.List;

/**
 * Created by cleverlin on 2017/7/13.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener{
    private List<String> mDataList;
    private OnItemClickListener mOnItemClickListener = null;
    public MyAdapter(List<String> list) {
        mDataList = list;
    }

    @Override
    public int getItemCount() {
        // 返回数据集合大小
        //Log.d("getItemCount",mDataList.size()+"");
        Log.d("setText","1");
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //获取这个TextView

        TextView tv= holder.mTvTitle;

        tv.setText(mDataList.get(position));
        Log.d("setText","2");
        holder.itemView.setTag(position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.main_listview, null, false);
        ViewHolder viewHolder=new ViewHolder(view);
        view.setOnClickListener(this);
        Log.d("setText","3");
        return viewHolder;
        // return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_listview, null, false));
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
            Log.d("setText","4");
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.item_tv);
            Log.d("setText","5");

        }


    }
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}