package com.youdu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youdu.Bean.Bean.ResultBean;
import com.youdu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cleverlin on 2017/8/21.
 */

public class HintAdapter extends RecyclerView.Adapter<HintAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<ResultBean> mData;

    public HintAdapter(Context mContext, List<ResultBean> mData) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new ViewHolder(mLayoutInflater.inflate(R.layout.layout_hint, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultBean resultBean=mData.get(position);
        holder.textHint.setText(resultBean.getName());
        Log.d("onBindViewHolder",resultBean.getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_hint)
        TextView textHint;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
