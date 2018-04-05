package com.youdu.Bean;

/**
 * Created by Administrator on 2017/3/26.
 */

public class ItemBean {
    public int imageViewID;
    public String tv_title;
    public String tv_content;

    public ItemBean(int imageView, String tv_title, String tv_content){
        this.imageViewID = imageView;
        this.tv_title = tv_title;
        this.tv_content = tv_content;

    }
}
