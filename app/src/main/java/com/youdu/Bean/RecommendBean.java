package com.youdu.Bean;

/**
 * Created by kyrie on 2017/10/3.
 */

public class RecommendBean {
    /**
     * photo : http://115.159.55.206/forAndroidSelf/a.jpg
     * name : 土豆丸子
     * stars : 5
     * cooktime : 36
     */

    private String photo;
    private String name;
    private float stars;
    private int cooktime;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getCooktime() {
        return cooktime;
    }

    public void setCooktime(int cooktime) {
        this.cooktime = cooktime;
    }
}
