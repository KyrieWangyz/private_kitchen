package com.youdu.Bean;

import java.util.List;

/**
 * Created by kyrie on 2017/10/3.
 */

public class Article<T> {


    private List<AllBean> all;
    private List<BannersBean> banners;
    private List<RecommendBean> foodmenu;
    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }
    public List<RecommendBean> getFoodmenu() {
        return foodmenu;
    }

    public void setFoodmenu(List<RecommendBean> foodmenu) {
        this.foodmenu = foodmenu;
    }
    public List<AllBean> getAll() {
        return all;
    }

    public void setAll(List<AllBean> all) {
        this.all = all;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }




}
