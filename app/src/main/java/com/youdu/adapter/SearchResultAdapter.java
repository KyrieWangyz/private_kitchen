package com.youdu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youdu.R;
import com.youdu.imageloder.ImageLoader;
import com.youdu.module.recommand.ResultBeanModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kyrie on 2017/10/3.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private Context mContext;
    private List<ResultBeanModel> mDatas;
    private LayoutInflater mLayoutInflater;
    private MyItemClick myItemClick;
    public SearchResultAdapter(Context mContext, List<ResultBeanModel> mDatas,MyItemClick myItemClick) {//修改List<....>
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.myItemClick=myItemClick;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.search_result_n, parent, false),myItemClick);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultBeanModel resultBean=mDatas.get(position);
        holder.foodName.setText(resultBean.name);
        ImageLoader.load(resultBean.img, holder.foodIcon);
        holder.material.setText(resultBean.material);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        MyItemClick myItemClick;
        @BindView(R.id.food_icon)
        ImageView foodIcon;
        @BindView(R.id.food_name)
        TextView foodName;
        @BindView(R.id.material)
        TextView material;

        public ViewHolder(View itemView,MyItemClick myItemClick) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.myItemClick=myItemClick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(myItemClick!=null){
                Log.d("position",getPosition()+"");
                myItemClick.itemClick(getPosition());
            }
        }
    }

    public interface MyItemClick{
        void itemClick(int position);
    }
}
