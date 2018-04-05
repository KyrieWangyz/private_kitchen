package myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youdu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cleverlin on 2017/8/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {



    private Context mContext;
    private List<CircleBean> mDatas;
    private LayoutInflater mLayoutInflater;

    public RecyclerViewAdapter(Context mContext, List<CircleBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mLayoutInflater = LayoutInflater.from(mContext);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.templete_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CircleBean content = mDatas.get(position);
        // Picasso.with(mContext).load(content.getPic()).into(holder.headimg);
        holder.contentTv.setText(content.getContent());
        holder.nickname.setText(content.getNick_name());
        if (content.getOne() != null) {
            holder.photoList.setVisibility(View.VISIBLE);
            holder.one.setVisibility(View.VISIBLE);
            holder.one.setImageBitmap(content.getOne());
        }
        if (content.getTwo() != null) {
            holder.two.setVisibility(View.VISIBLE);
            holder.two.setImageBitmap(content.getTwo());
        }
        if (content.getThree() != null) {
            holder.three.setVisibility(View.VISIBLE);
            holder.three.setImageBitmap(content.getThree());
        }

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.one)
        ImageView one;
        @BindView(R.id.two)
        ImageView two;
        @BindView(R.id.three)
        ImageView three;
        @BindView(R.id.headimg)
        ImageView headimg;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.content_tv)
        TextView contentTv;
        @BindView(R.id.photo_list)
        LinearLayout photoList;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
