package com.youdu.network.http;

import com.youdu.module.course.BaseCourseModel;
import com.youdu.module.recommand.ArticlenModel;
import com.youdu.module.recommand.BaseRecommandModel;
import com.youdu.module.recommand.DouguobbModel;
import com.youdu.module.recommand.DouguommModel;
import com.youdu.module.recommand.EatingrecordnModel;
import com.youdu.module.recommand.GetcaloriebykeywordsnModel;
import com.youdu.module.recommand.HotvegetableModel;
import com.youdu.module.recommand.HotvegetablenModel;
import com.youdu.module.recommand.LoginModel;
import com.youdu.module.recommand.MainmmModel;
import com.youdu.module.recommand.PresonalMessagenModel;
import com.youdu.module.recommand.ResultBeannModel;
import com.youdu.module.update.UpdateModel;
import com.youdu.module.user.User;
import com.youdu.okhttp.CommonOkHttpClient;
import com.youdu.okhttp.listener.DisposeDataHandle;
import com.youdu.okhttp.listener.DisposeDataListener;
import com.youdu.okhttp.listener.DisposeDownloadListener;
import com.youdu.okhttp.request.CommonRequest;
import com.youdu.okhttp.request.RequestParams;

/**
 * @author: vision
 * @function:
 * @date: 16/8/12
 */
public class RequestCenter {

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 用户登陆请求
     *
     * @param listener
     * @param userName
     * @param passwd
     */
    public static void login(String userName, String passwd, DisposeDataListener listener) {

        RequestParams params = new RequestParams();
        params.put("mb", userName);
        params.put("pwd", passwd);
        RequestCenter.postRequest(HttpConstants.LOGIN, params, listener, User.class);
    }
/*
厨房的登录验证
 */
    public static void requestloginstate(String id, String password,DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("password",password);

        RequestCenter.postRequestn(HttpConstants.LOGINSTATE, params, listener, LoginModel.class);

    }
    /*
    推文请求
     */
    public static void requestarticle(String date,DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("date", date);
        RequestCenter.postRequestn(HttpConstants.ARTICAL, params, listener, ArticlenModel.class);
    }
    /*
    情景推荐
     */
    public static void requestscene(String keywords,String number,DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("keywords", keywords);
        params.put("number",number);
        RequestCenter.postRequestn(HttpConstants.SCENE, params, listener, HotvegetableModel.class);
    }
    /*
    语音教学页面的数据请求
     */
    public static void requeststep(String keywords,DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("keywords", keywords);
        RequestCenter.postRequestn(HttpConstants.VEGETABLEMESSAGE, params, listener, HotvegetableModel.class);
    }

    /*
    输入食材返回卡路里
     */
    public static void requestcaloriebukeywords(String keywords,DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("keywords", keywords);
        RequestCenter.postRequestn(HttpConstants.GETCALORIEBYKEYWORDS, params, listener, GetcaloriebykeywordsnModel.class);
    }

    /*
    通过菜名返回结果，搜索
     */
    public static void requestsearchresult(String keywords,DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("keywords", keywords);
        RequestCenter.postRequestn(HttpConstants.SEARCHBYKEYWORDS, params, listener, ResultBeannModel.class);
    }
    /*
    通过食材返回结果，搜索
     */
    public static void requestsearchresultn(String keywords,DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("keywords", keywords);
        RequestCenter.postRequestn(HttpConstants.GETCALORIEBYKEYWORDS, params, listener, ResultBeannModel.class);
    }
    /*
    用户信息请求
     */
    public static void requestpersonalmessage(String id,DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("id", id);
        RequestCenter.postRequestn(HttpConstants.PERSONALMESSAGE, params, listener, PresonalMessagenModel.class);
    }
/*
饮食记录信息请求
 */
public static void requesteatingrecord(String id,String weekday,DisposeDataListener listener){
    RequestParams params = new RequestParams();
    params.put("id",id);
    params.put("weekday", weekday);
    RequestCenter.postRequestn(HttpConstants.EATINGRECORD, params, listener, EatingrecordnModel.class);
}




    /**
     * 应用版本号请求
     *
     * @param listener
     */
    public static void checkVersion(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.CHECK_UPDATE, null, listener, UpdateModel.class);
    }
//   快递的接口
    public static void requestRecommandData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.LIN, null, listener, MainmmModel.class);//改了两个参数在这里，如有需要自己改回
    }
    public static void requestRecommandDatan(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.CLEVER, null, listener, DouguommModel.class);//改了两个参数在这里，如有需要自己改回
    }



//推荐个人食谱的接口
public static void requestPrivateData(DisposeDataListener listener) {
    RequestCenter.postRequest(HttpConstants.PRIVATEVEGETABLE, null, listener, HotvegetableModel.class);//改了两个参数在这里，如有需要自己改回
}





    //推荐热门菜谱的接口
    public static void requestHotvegetableData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.Hotvegetable, null, listener, HotvegetableModel.class);//改了两个参数在这里，如有需要自己改回
    }
    //    首页数据请求的接口
    public static void requestRecommandDatam(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener, BaseRecommandModel.class);//改了两个参数在这里，如有需要自己改回
    }

    public static void downloadFile(String url, String path, DisposeDownloadListener listener) {
        CommonOkHttpClient.downloadFile(CommonRequest.createGetRequest(url, null),
                new DisposeDataHandle(listener, path));
    }

    /**
     * 请求课程详情
     *
     * @param listener
     */
    public static void requestCourseDetail(String courseId, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("courseId", courseId);
        RequestCenter.postRequest(HttpConstants.COURSE_DETAIL, params, listener, BaseCourseModel.class);
    }
    /*
    哈哈哈，接下来就是我的post请求了
     */
    public static void requestVegetableDetail(String courseId, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("courseId", courseId);
        RequestCenter.postRequest(HttpConstants.COURSE_DETAIL, params, listener, BaseCourseModel.class);
    }
    public static void postRequestn(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
//        CommonOkHttpClient.get(CommonRequest.
//                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url, params),
                new DisposeDataHandle(listener, clazz));
    }
/*
historyFragment的post请求方法
 */
    public static void requestpostDetail(String vegetable ,DisposeDataListener listener) {
            RequestParams params = new RequestParams();
            params.put("vegetable", vegetable);

            RequestCenter.postRequestn(HttpConstants.DETAIL, params, listener, DouguobbModel.class);

    }
    /*
    详情教学接口
     */
    public static void requestVegetableteach(String name ,DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("name", name);

        RequestCenter.postRequestn(HttpConstants.Hotvegetable, params, listener, HotvegetablenModel.class);
    }
    /*
    菜谱详情接口
     */
    public static void requestSearchvegetable(String keywords ,DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("keywords", keywords);

        RequestCenter.postRequestn(HttpConstants.SEARCHVEGETABLE, params, listener, HotvegetableModel.class);
    }

    /*
    个人信息注册的相关信息提交post请求
     */
    public static void postregistermessage(String personalimformation,DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("personalimformation", personalimformation);

        RequestCenter.postRequestn(HttpConstants.DETAIL, params, listener, DouguobbModel.class);//网址什么的要根据需求来改

    }




}
