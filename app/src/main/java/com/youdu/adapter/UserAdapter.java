package com.youdu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.youdu.R;
import com.youdu.activity.AdBrowserActivity;
import com.youdu.adutil.Utils;
import com.youdu.core.AdContextInterface;
import com.youdu.core.video.VideoAdContext;
import com.youdu.module.recommand.RecommandBodyValue;
import com.youdu.share.ShareDialog;
import com.youdu.util.ImageLoaderManager;
import com.youdu.util.Util;

import java.util.ArrayList;

import cn.sharesdk.framework.Platform;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: qndroid
 * @function:
 * @date: 16/6/15
 */
public class UserAdapter extends BaseAdapter {
    /**
     * Common
     */
    /**
     * 为不同类型的item做标识
     */
    private static final int CARD_COUNT = 4;
    private static final int VIDOE_TYPE = 0x00;//视频类型
    private static final int CARD_TYPE_ONE = 0x01;//单一图片类型
    private static final int CARD_TYPE_TWO = 0x02;//多图片类型
    private static final int CARD_TYPE_THREE = 0x03;//viewpager
    private static final int TEST=0x01;//测试

    private LayoutInflater mInflate;
    private Context mContext;
    private ArrayList<RecommandBodyValue> mData;
    private ViewHolder mViewHolder;
    private VideoAdContext mAdsdkContext;
    private ImageLoaderManager mImagerLoader;//异步图片加载工具


    public UserAdapter(Context context, ArrayList<RecommandBodyValue> data) {
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
    public int getViewTypeCount() {
        return CARD_COUNT;
    }//返回我们listview所有item的类型，我们列表有4种类型

    @Override
    public int getItemViewType(int position) {//通知adapter要使用哪种类型的item去加载数据
        RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        return value.type;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);//获取数据的type类型
        final RecommandBodyValue value = (RecommandBodyValue) getItem(position);//获取对应列上的数据
       // Log.d(tag,position);
        //无tag时
        if (convertView == null) {//判断缓存是否为空，空时才来新建
            switch (type) {
                case VIDOE_TYPE:
                    //显示video卡片
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_video_layout, parent, false);
                    mViewHolder.mVieoContentLayout = (RelativeLayout)
                            convertView.findViewById(R.id.video_ad_layout);
                    mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mShareView = (ImageView) convertView.findViewById(R.id.item_share_view);
                    //为对应布局创建播放器
                    mAdsdkContext = new VideoAdContext(mViewHolder.mVieoContentLayout,
                            new Gson().toJson(value), null);
                    mAdsdkContext.setAdResultListener(new AdContextInterface() {
                        @Override
                        public void onAdSuccess() {
                        }

                        @Override
                        public void onAdFailed() {
                        }

                        @Override
                        public void onClickVideo(String url) {
                            Intent intent = new Intent(mContext, AdBrowserActivity.class);
                            intent.putExtra(AdBrowserActivity.KEY_URL, url);
                            mContext.startActivity(intent);
                        }
                    });
                    break;
                case CARD_TYPE_ONE://测试，后面要改回来
                    mViewHolder = new ViewHolder();
//                   convertView = mInflate.inflate(R.layout.item_product_card_one_layout, parent, false);
//                 mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
//                   mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
//                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
//                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
//                   mViewHolder.mPriceView = (TextView) convertView.findViewById(R.id.item_price_view);
//                    mViewHolder.mFromView = (TextView) convertView.findViewById(R.id.item_from_view);
//                   mViewHolder.mZanView = (TextView) convertView.findViewById(R.id.item_zan_view);
//                   mViewHolder.mProductLayout = (LinearLayout) convertView.findViewById(R.id.product_photo_layout);



                    convertView=mInflate.inflate(R.layout.item_product_myself_layout,parent,false);
                    mViewHolder.mTitleView=(TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mLogoView=(CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);









                    break;










                case CARD_TYPE_TWO:
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_product_card_two_layout, parent, false);
                    mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mProductView = (ImageView) convertView.findViewById(R.id.product_photo_view);
                    mViewHolder.mPriceView = (TextView) convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = (TextView) convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = (TextView) convertView.findViewById(R.id.item_zan_view);
                    break;
                case CARD_TYPE_THREE:
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_product_card_three_layout, null, false);
                    mViewHolder.mViewPager = (ViewPager) convertView.findViewById(R.id.pager);
                    //add data
                    ArrayList<RecommandBodyValue> recommandList = Util.handleData(value);
                    mViewHolder.mViewPager.setPageMargin(Utils.dip2px(mContext, 12));
           //         mViewHolder.mViewPager.setAdapter(new HotSalePagerAdapter(mContext, recommandList));
                    mViewHolder.mViewPager.setCurrentItem(recommandList.size() * 100);
                    break;
            }
            convertView.setTag(mViewHolder);
        }//有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        //填充item的数据
        switch (type) {
            case VIDOE_TYPE:
                mImagerLoader.displayImage(mViewHolder.mLogoView, value.logo);
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mShareView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareDialog dialog = new ShareDialog(mContext, false);
                        dialog.setShareType(Platform.SHARE_VIDEO);
                        dialog.setShareTitle(value.title);
                        dialog.setShareTitleUrl(value.site);
                        dialog.setShareText(value.text);
                        dialog.setShareSite(value.title);
                        dialog.setShareTitle(value.site);
                        dialog.setUrl(value.resource);
                        dialog.show();
                    }
                });
                break;
            case CARD_TYPE_ONE:
                mImagerLoader.displayImage(mViewHolder.mLogoView, value.logo);
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                //mViewHolder.mPriceView.setText(value.price);
                //mViewHolder.mFromView.setText(value.from);
                //mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(value.zan));
               /* mViewHolder.mProductLayout.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PhotoViewActivity.class);
                        intent.putStringArrayListExtra(PhotoViewActivity.PHOTO_LIST, value.url);
                        mContext.startActivity(intent);
                    }
                });*/
                //  mViewHolder.mProductLayout.removeAllViews();
                //动态添加多个imageview
              /*  for (String url : value.url) {
                    mViewHolder.mProductLayout.addView(createImageView(url));
                }*/
                break;
            case CARD_TYPE_TWO:
                mImagerLoader.displayImage(mViewHolder.mLogoView, value.logo);
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(value.zan));
                //为单个ImageView加载远程图片
                mImagerLoader.displayImage(mViewHolder.mProductView, value.url.get(0));
                break;
            case CARD_TYPE_THREE:
                break;
        }
        return convertView;
    }

    //自动播放方法
    public void updateAdInScrollView() {
        if (mAdsdkContext != null) {
            mAdsdkContext.updateAdInScrollView();
        }
    }

    //动态添加ImageView
    private ImageView createImageView(String url) {
        ImageView photoView = new ImageView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.
                LayoutParams(Utils.dip2px(mContext, 100),
                LinearLayout.LayoutParams.MATCH_PARENT);
        params.leftMargin = Utils.dip2px(mContext, 5);
        photoView.setLayoutParams(params);
        mImagerLoader.displayImage(photoView, url);
        return photoView;
    }
    //用来缓存我们所有的item
    private static class ViewHolder {
        //所有Card共有属性
        private CircleImageView mLogoView;
        private TextView mTitleView;
        private TextView mInfoView;
        private TextView mFooterView;
        //Video Card特有属性
        private RelativeLayout mVieoContentLayout;
        private ImageView mShareView;

        //Video Card外所有Card具有属性
        private TextView mPriceView;
        private TextView mFromView;
        private TextView mZanView;
        //Card One特有属性
        private LinearLayout mProductLayout;
        //Card Two特有属性
        private ImageView mProductView;
        //Card Three特有属性
        private ViewPager mViewPager;
    }
}
