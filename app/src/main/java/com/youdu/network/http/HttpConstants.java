package com.youdu.network.http;

/**
 * @author: qndroid
 * @function: 所有请求相关地址
 * @date: 16/8/12
 */
public class HttpConstants {
    /*
    情景推荐
     */
    public static String SCENE="http://119.29.245.39/foodApp/interface/getFoodCommendByKeyword.php";
    /*
    通过食材获取卡路里
     */
    public static String GETCALORIEBYKEYWORDS="http://119.29.245.39/foodApp/interface/getCalorieByKeywords.php";

    /*
    通过关键词返回结果
     */
    public static String SEARCHBYKEYWORDS="http://119.29.245.39/foodApp/interface/searchByKeywords.php";

    /*
    获取用户自己的推荐菜谱
     */
    public static String PRIVATEVEGETABLE="http://119.29.245.39/foodApp/interface/getFoodCommendById.php";

    /*
    通过菜名获取菜谱详细信息
     */
    public static String VEGETABLEMESSAGE="http://119.29.245.39/foodApp/interface/searchByKeywords.php";

    /*
    用户信息接口
     */
    public static String PERSONALMESSAGE="http://119.29.245.39/foodApp/interface/getUserInformation.php";

    /*
    推文接口
     */
    public static String ARTICAL="http://119.29.245.39/foodApp/interface/getArticalByDate.php";
/*
饮食记录接口
 */
    public static String EATINGRECORD="http://119.29.245.39/foodApp/interface/getDayMealsById.php";

    /*
    熊魁的后端接口
     */

    public static String DETAIL="http://115.159.55.206/test/wangyunzhang.php";
    public static String CLEVER="http://115.159.55.206/test/new/wangyunzhang.php";
    public static String LIN="http://115.159.55.206/test/wangyunzhang.php";
    public static String WANG="http://delivery/api.php/order/showorder?userid=1";
    /*
    登录接口
     */
    public static String LOGINSTATE="http://119.29.245.39/foodApp/interface/checkUserInformation.php";

    /*
    荣超的后端接口
     */
    public static String Hotvegetable="http://119.29.245.39/foodApp/interface/getFoodRankByDate.php";

    public static String TP="http://data.test/Public/img/manstudent.png";
    public static String SEARCHVEGETABLE="http://119.29.245.39/foodApp/interface/searchByKeywords.php";


    private static final String ROOT_URL = "http://yuexibo.top/yxbApp/";
    /**
     * 请求本地产品列表
     */
    public static String PRODUCT_LIST = ROOT_URL + "/fund/search.php";

    /**
     * 本地产品列表更新时间措请求
     */
    public static String PRODUCT_LATESAT_UPDATE = ROOT_URL + "/fund/upsearch.php";

    /**
     * 登陆接口
     */
    public static String LOGIN = ROOT_URL + "user_info.json";

    /**
     * 检查更新接口
     */
    public static String CHECK_UPDATE = ROOT_URL + "update.json";

    /**
     * 首页产品请求接口
     */
    public static String HOME_RECOMMAND = ROOT_URL + "home_data.json";

    /**
     * 课程详情接口
     */
    public static String COURSE_DETAIL = ROOT_URL + "course_detail.json";


    /*
    登录界面接口
     */



}


