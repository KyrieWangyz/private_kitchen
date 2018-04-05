package com.youdu.adapter;

/**
 * Created by kyrie on 2017/8/6.
 */

public class Vegetable {
    private String vegetable;
    private int img;
    private String time;
    private int difficult;
    public Vegetable(String vegetable,int img,String time,int difficult)
    {
        this.vegetable=vegetable;
        this.img=img;
        this.time=time;
        this.difficult=difficult;
    }
    public String getVegetable(){
        return vegetable;
    }
    public int getImg(){
        return  img;
    }
    public String getTime(){
        return time;
    }
    public int getDifficult(){
        return difficult;
    }
}
