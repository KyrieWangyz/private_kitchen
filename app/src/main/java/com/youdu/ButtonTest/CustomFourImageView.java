package com.youdu.ButtonTest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.youdu.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cleverlin on 2017/8/2.
 */

public class CustomFourImageView extends View {
    //用于判断只在第一次绘制才初始化一些资源数据
    private int state = -1;
    private final int START = 1;
    private Bitmap topImage;
    private Paint paint;
    private Matrix topMatrix;
    private Path top;
    private Bitmap btmap=null;//用来转换网络图片的bitmap
    public CustomFourImageView(Context context) {
        super(context);
    }

    public CustomFourImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (state > 0) {
            return;
        }
        state = START;
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);// 软件加速
        if(btmap!=null){
            topImage=btmap;
        }else {
            topImage = BitmapFactory.decodeResource(getResources(), R.drawable.food);
        }
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);// 防抖动
        paint.setFilterBitmap(true);// 过滤
        initMatrix();// 缩小图片
        initPath();
    }
    private void initMatrix() {
        topMatrix = new Matrix();
        float w = getWidth();
        float h = getWidth();
        // 第一个图片
        float scale = 1;// 缩放量
        float scaleX = w / topImage.getWidth();
        float scaleY = h / topImage.getHeight();
        scale = scaleX > scaleY ? scaleX : scaleY;
        topMatrix.setScale(scale, scale);// 开始缩放比例
    }
    // 画好矩阵模块
    private void initPath() {
        top = new Path();
        top.moveTo(0, 0);
        top.lineTo(dip2px(getContext(),getWidth()), 0);
        top.lineTo(dip2px(getContext(),getWidth()), dip2px(getContext(),350));//自定义斜图的右边长度
        top.lineTo(0, dip2px(getContext(),240));//左边的长度
        top.close();
    }
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (topImage != null) {
            // 设置抗锯齿
            PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
            canvas.setDrawFilter(pfd);
            canvas.save();
            canvas.clipPath(top);// 先画好模块
            canvas.drawBitmap(topImage, topMatrix, paint);// 再画图
            canvas.restore();
        }
    }
/*
网络url转bitmap
 */

    public void getbitmap(String url){
        URL fileUrl = null;


        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            btmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getBitmap(String url)throws Exception {
//        Bitmap bm = null;
//        try {
//            URL iconUrl = new URL(url);
//            URLConnection conn = iconUrl.openConnection();
//            HttpURLConnection http = (HttpURLConnection) conn;
//
//            int length = http.getContentLength();
//
//            conn.connect();
//            // 获得图像的字符流
//            InputStream is = conn.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is, length);
//            bm = BitmapFactory.decodeStream(bis);
//            bis.close();
//            is.close();// 关闭流
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        URL fileUrl = null;


        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is=conn.getInputStream();
            if (is == null){
                throw new RuntimeException("stream is null");
            }else{
                try {
                    byte[] data=readStream(is);
                    if(data!=null){
                        btmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //initbm(bitmap);
       // initbm(bm);
    }


    private void initbm(Bitmap bm) {

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);// 软件加速
        topImage=bm;
      //  topImage = BitmapFactory.decodeResource(getResources(), R.drawable.food);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);// 防抖动
        paint.setFilterBitmap(true);// 过滤
        initMatrix();// 缩小图片
        initPath();
    }





    public static byte[] readStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }


}
