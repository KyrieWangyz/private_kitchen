package com.youdu.module.recommand;

import com.youdu.module.BaseModel;

import java.util.ArrayList;

/**
 * Created by kyrie on 2017/9/26.
 */

public class HotvegetablenModel extends BaseModel {
    public String name;
    public String img;
    public String difficulty;
    public String time;
    public String material;
    public ArrayList<HotvegetablestepModel> step;
    public ArrayList<HotvegetablenutrientModel> nutrient;
}
