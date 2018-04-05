package com.youdu.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;
import com.youdu.ApkInstaller.ApkInstaller;
import com.youdu.R;
import com.youdu.adapter.GuideFragmentAdapter;
import com.youdu.module.recommand.HotvegetableModel;
import com.youdu.myfile.FixedSpeedScroll;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;
import com.youdu.okhttp.listener.Pausethevoice;
import com.youdu.okhttp.listener.Resumethevoice;
import com.youdu.okhttp.listener.Sendlocation;
import com.youdu.okhttp.listener.Startlisten;
import com.youdu.okhttp.listener.Startthevoice;
import com.youdu.okhttp.listener.Stopthevoice;
import com.youdu.setting.TtsSettings;
import com.youdu.util.JsonParser;
import com.youdu.view.fragment.guide.GuideFragmentnew;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by kyrie on 2017/8/28.
 */

public class ProgressActivity extends FragmentActivity implements View.OnClickListener,Startthevoice,Pausethevoice,Resumethevoice,Stopthevoice,Sendlocation,Startlisten{

    public static final String NAME="name";

    public static final String STEP1="step1";
    public static final String STEP2="step2";
    public static final String STEP3="step3";




    private HotvegetableModel model;
    private String getname;
    private static int state=0;//用来判断是否开始语音播放，0为可以说话，1为语音状态，不能说话

private int x,y;

    private static String TAG = ProgressActivity.class.getSimpleName();
    // 语音合成对象
    private SpeechSynthesizer mTts;

    // 默认发音人
    private String voicer = "xiaoyan";

    private String[] mCloudVoicersEntries;
    private String[] mCloudVoicersValue ;

    // 缓冲进度
    private int mPercentForBuffering = 0;
    // 播放进度
    private int mPercentForPlaying = 0;

    // 云端/本地单选按钮
    private RadioGroup mRadioGroup;
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    // 语记安装助手
    ApkInstaller mInstaller ;


    private Toast mToast;
    private SharedPreferences mSharedPreferences;


    private int selectedNum = 0;


    private ImageView imageView;


    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();



    private boolean mTranslateEnable = false;
    private ImageView back;
    private Button b;
    private Button b2;

    private FixedSpeedScroll mScroller;

    GuideFragmentnew guidefragmentnew;


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////分割线

   // @BindView(R.id.viewpage)
    ViewPager viewpage;
    RelativeLayout activityMain;
    //private int stepText[]={R.string.first,R.string.second,R.string.third};///////////////////////////////////////////////////这里先注释掉测试下
    private String stepText[]={"准备工作：鸡胸肉用刀背拍一下，切成大拇指甲大小的丁,用料酒一汤匙，食用油半汤匙，白胡椒半茶匙，盐半茶匙，淀粉一茶匙，腌渍十分钟入味,锅里放油，七八成热下鸡丁炒变白。","锅里加上水烧开，把肉放进去戳一下，主要是洗洗肉还有去掉肉沫子；锅里加入生抽老抽，生抽多放点，老抽少放点，老抽放多了做出来的肉很难看；把刚刚戳好的肉放进去煮，大火15分钟转小火2小时。中途要经常观看，这东西容易焦","全部裹好后，往上面加上一些枸杞点缀。上蒸锅，水开后大火转中火蒸20分钟。关火出锅。"};





    private int stepImg[]={R.drawable.eggbing,R.drawable.jisiliangmian,R.drawable.yangrouc};
    private int stepBack[]={R.mipmap.nav_return,0,0};
    private String stepNumber[]={"1","2","3"};
    private GuideFragmentAdapter mAdapter;
  //  private EditText mResultText;
    private String context;

    @SuppressLint("ShowToast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//没有标题栏

        initintent();
       // requestRecommandData();

        setFullScreen();
        setContentView(R.layout.activity_progress);
        viewpage= (ViewPager) findViewById(R.id.viewpage);
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源



        mIat = SpeechRecognizer.createRecognizer(ProgressActivity.this, mInitListener);
        mIatDialog = new RecognizerDialog(ProgressActivity.this, mInitListener);
    //    mResultText = ((EditText) findViewById(R.id.editext));
        ButterKnife.bind(this);

        initrescourse();//加载讯飞的资源

        listen();
        initData();


    }

    private void initintent() {
        String a,b,c;
        Intent intent = getIntent();
        getname = intent.getStringExtra(NAME);
        stepText[0]=intent.getStringExtra(STEP1);
        stepText[1]=intent.getStringExtra(STEP2);
        stepText[2]=intent.getStringExtra(STEP3);
    }

    private void click() /////////////////////////10.17
    {
//        FragmentTransaction far=getSupportFragmentManager().beginTransaction();
//        GuideFragmentnew fa=new GuideFragmentnew();
//        far.add(R.id.viewpage,fa,GuideFragmentnew.class.getName());/////////////////////R.id后面的不知道对不对
//        far.commit();



  //setViewn();//坐标模拟点击
    //   setView();//控件模拟点击
    }

    private void setViewn(){


        Button a= (Button) getSupportFragmentManager().findFragmentById(R.id.viewpage).getView().findViewById(R.id.start);



        int[] location = new int[2];
        a.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];//获取开始按钮的坐标
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();


//        Instrumentation inst = new Instrumentation();
//        inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
//                MotionEvent.ACTION_DOWN, x+2, y+2, 0));
//        inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
//                MotionEvent.ACTION_UP, x+2, y+2, 0));
    }

    private void setView() {

        Button a= (Button) getSupportFragmentManager().findFragmentById(R.id.viewpage).getView().findViewById(R.id.start);
        a.performClick();

   //     Button b= (Button) getSupportFragmentManager().findFragmentById(R.id.viewpage).getView().findViewById(R.id.start);

    }


    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code);
            }
        }
    };

    /**
     * 初始化监听。
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d(TAG, "InitListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败,错误码："+code);
            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };


    private void initrescourse() {
        mTts = SpeechSynthesizer.createSynthesizer(ProgressActivity.this, mTtsInitListener);

        // 云端发音人名称列表
        mCloudVoicersEntries = getResources().getStringArray(R.array.voicer_cloud_entries);
        mCloudVoicersValue = getResources().getStringArray(R.array.voicer_cloud_values);

        mSharedPreferences = getSharedPreferences(TtsSettings.PREFER_NAME, MODE_PRIVATE);
        mToast = Toast.makeText(this,"",Toast.LENGTH_SHORT);

        mInstaller = new  ApkInstaller(ProgressActivity.this);



        ////////////////////////////////////


        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源

        mSharedPreferences = getSharedPreferences(IatSettings.PREFER_NAME,
                Activity.MODE_PRIVATE);
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    private void satrtspeech(String content) {//讯飞的读文字功能

        if( null == mTts ){
            // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
            this.showTip( "创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化" );
            return;
        }

        FlowerCollector.onEvent(ProgressActivity.this, "tts_play");

        String text = content;
        // 设置参数
        setParam();
        int code = mTts.startSpeaking(text, mTtsListener);
//        if( null == mTts ){
//            // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
//            this.showTip( "创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化" );
//            return;
//        }
        if (code != ErrorCode.SUCCESS) {
            if(code == ErrorCode.ERROR_COMPONENT_NOT_INSTALLED){
                //未安装则跳转到提示安装页面
                mInstaller.install();
            }
            else {
                showTip("语音合成失败,错误码: " + code);
            }
        }
    }


    private SynthesizerListener mTtsListener = new SynthesizerListener() {

        @Override
        public void onSpeakBegin() {
            showTip("开始播放");
        }

        @Override
        public void onSpeakPaused() {
            showTip("暂停播放");
            listen();
        }

        @Override
        public void onSpeakResumed() {
            showTip("继续播放");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {
            // 合成进度
            mPercentForBuffering = percent;
            showTip(String.format(getString(R.string.tts_toast_format),
                    mPercentForBuffering, mPercentForPlaying));
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
            mPercentForPlaying = percent;
            showTip(String.format(getString(R.string.tts_toast_format),
                    mPercentForBuffering, mPercentForPlaying));
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                showTip("播放完成");
            } else if (error != null) {
                showTip(error.getPlainDescription(true));
            }
            listen();
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };

    private void setParamn(){

        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

        this.mTranslateEnable = mSharedPreferences.getBoolean( this.getString(R.string.pref_key_translate), false );
        if( mTranslateEnable ){
            Log.i( TAG, "translate enable" );
            mIat.setParameter( SpeechConstant.ASR_SCH, "1" );
            mIat.setParameter( SpeechConstant.ADD_CAP, "translate" );
            mIat.setParameter( SpeechConstant.TRS_SRC, "its" );
        }

        String lag = mSharedPreferences.getString("iat_language_preference",
                "mandarin");
        if (lag.equals("en_us")) {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
            mIat.setParameter(SpeechConstant.ACCENT, null);

            if( mTranslateEnable ){
                mIat.setParameter( SpeechConstant.ORI_LANG, "en" );
                mIat.setParameter( SpeechConstant.TRANS_LANG, "cn" );
            }
        } else {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, lag);

            if( mTranslateEnable ){
                mIat.setParameter( SpeechConstant.ORI_LANG, "cn" );
                mIat.setParameter( SpeechConstant.TRANS_LANG, "en" );
            }
        }


        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
//        mIat.setParameter(SpeechConstant.VAD_BOS, mSharedPreferences.getString("iat_vadbos_preference", "1000"));
//
//        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
//        mIat.setParameter(SpeechConstant.VAD_EOS, mSharedPreferences.getString("iat_vadeos_preference", "1000"));
//
//        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
//        mIat.setParameter(SpeechConstant.ASR_PTT, mSharedPreferences.getString("iat_punc_preference", "1"));

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/iat.wav");
    }

    private void setParam() {
        mTts.setParameter(SpeechConstant.PARAMS, null);
        // 根据合成引擎设置相应参数
        if(mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
            // 设置在线合成发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicer);
            //设置合成语速
            mTts.setParameter(SpeechConstant.SPEED, mSharedPreferences.getString("speed_preference", "60"));
            //设置合成音调
            mTts.setParameter(SpeechConstant.PITCH, mSharedPreferences.getString("pitch_preference", "50"));
            //设置合成音量
            mTts.setParameter(SpeechConstant.VOLUME, mSharedPreferences.getString("volume_preference", "50"));
        }
        else {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
            // 设置本地合成发音人 voicer为空，默认通过语记界面指定发音人。
            mTts.setParameter(SpeechConstant.VOICE_NAME, "");
            /**
             * TODO 本地合成不设置语速、音调、音量，默认使用语记设置
             * 开发者如需自定义参数，请参考在线合成参数设置
             */
    }
        mTts.setParameter(SpeechConstant.STREAM_TYPE, mSharedPreferences.getString("stream_preference", "3"));
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/tts.wav");
    }

    private void initData() {
        imageView=(ImageView)findViewById(R.id.personalcenter_icon_set);
        imageView.setOnClickListener(this);

        back= (ImageView) findViewById(R.id.back_view);
        back.setOnClickListener(this);


        ///////////////////////////////////////////////
        List<Fragment> fragments=new ArrayList<>();
        int length=stepText.length;
        Log.d("length",""+length);
        for (int i=0;i<length;i++){
            Log.d("i",""+i);
            fragments.add(GuideFragmentnew.newInstance(stepImg[i],stepText[i],stepBack[i],stepNumber[i]+"/"+length));
        }
        mAdapter=new GuideFragmentAdapter(getSupportFragmentManager());




        mAdapter.setmFragments(fragments);
        viewpage.setCurrentItem(0);
        viewpage.setOffscreenPageLimit(3);//用于设置viwepage后台默认需要加载的fargment页数
        viewpage.setAdapter(mAdapter);
        GuideFragmentnew.setViewPager(viewpage);

        viewsetlistener(viewpage);
        try {
            // 通过class文件获取mScroller属性
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mScroller = new FixedSpeedScroll(viewpage.getContext(), new AccelerateInterpolator());
            mField.set(viewpage, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewsetlistener(ViewPager pager) {
        ViewPager viewpager=pager;
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }//viewpager滑动中调用

            @Override
            public void onPageSelected(int arg0) {
                // arg0是当前选中的页面的Position
                mTts.stopSpeaking();
              //  listen();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }


    int ret = 0;//函数调用返回值
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.button:
//            listen();
//            break;
            case R.id.personalcenter_icon_set:
                Intent intents = new Intent(ProgressActivity.this, IatSettings.class);
                startActivity(intents);
                break;
//            case R.id.bt2:
//
//                clicknextbutton();
//                break;

            case R.id.back_view:
                this.finish();
                break;
        }
    }
    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
                public void onResult(RecognizerResult results, boolean isLast) {
                    if( mTranslateEnable ){
                        printTransResult( results );
                    }else{
                printResult(results);
            }

        }
        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            if(mTranslateEnable && error.getErrorCode() == 14002) {
                showTip( error.getPlainDescription(true)+"\n请确认是否已开通翻译功能" );
            } else {
                showTip(error.getPlainDescription(true));
            }
        }

    };


    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            showTip("开始说话");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
            if(mTranslateEnable && error.getErrorCode() == 14002) {
                showTip( error.getPlainDescription(true)+"\n请确认是否已开通翻译功能" );
            } else {
            //    showTip(error.getPlainDescription(true));
                if(state==0) {
               //     listen();///////////////////////////////////////////这里自己加的
                }
            }
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            showTip("结束说话");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            if( mTranslateEnable ){
                printTransResult( results );
            }else{
                printResult(results);
            }

            if (isLast) {
                // TODO 最后的结果
            }
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            showTip("当前正在说话，音量大小：" + volume);
            Log.d(TAG, "返回音频数据："+data.length);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };



    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());
        int currentitem;
        FragmentManager manager=getSupportFragmentManager();
//        TextView tv1=(TextView) manager.findFragmentByTag(getFragmentTag(0)).getView().findViewById(R.id.start);
//        tv1.setOnClickListener(this);
//        TextView tv2=(TextView) manager.findFragmentByTag(getFragmentTag(1)).getView().findViewById(R.id.start);
//        tv2.setOnClickListener(this);
//        TextView tv3=(TextView) manager.findFragmentByTag(getFragmentTag(2)).getView().findViewById(R.id.start);
//        tv3.setOnClickListener(this);

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

       // mResultText.setText(resultBuffer.toString());
     //   mResultText.setSelection(mResultText.length());
        context=resultBuffer.toString();
        if(context.equals("下一步"))
        {

            currentitem=viewpage.getCurrentItem()+1;
            select(currentitem);
        }
        if(context.equals("返回"))
        {
            currentitem=viewpage.getCurrentItem()-1;
            select(currentitem);
        }
        if(context.equals("开始")||context.equals("暂停"))
        {
//            String speakcontent;
//            speakcontent=getResources().getString(stepText[viewpage.getCurrentItem()]);
//            startspeaking(speakcontent);
           clickstartbutton();
        }
        if(context.equals("再一次")){
            clicknextbutton();
        }


        else {
            listen();
            //showTip("指令结束");
        }
    }



    private void printTransResult (RecognizerResult results) {
        String trans  = JsonParser.parseTransResult(results.getResultString(),"dst");
        String oris = JsonParser.parseTransResult(results.getResultString(),"src");

        if( TextUtils.isEmpty(trans)||TextUtils.isEmpty(oris) ){
            showTip( "解析结果失败，请确认是否已开通翻译功能。" );
        }else{
           // mResultText.setText( "原始语言:\n"+oris+"\n目标语言:\n"+trans );///////////////////////////////////////////////////////////////////////////////////////
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if( null != mTts ){
            mTts.stopSpeaking();
            // 退出时释放连接
            mTts.destroy();
        }
        if( null != mIat ){
            // 退出时释放连接
            mIat.cancel();
            mIat.destroy();
        }
        state=0;//可以说话
    }

    @Override
    public void onResume() {
        //移动数据统计分析
        FlowerCollector.onResume(this);
        FlowerCollector.onPageStart(TAG);
        super.onResume();
        state=1;//不能说话
    }
    @Override
    public void onPause() {
        //移动数据统计分析
        FlowerCollector.onPageEnd(TAG);
        FlowerCollector.onPause(ProgressActivity.this);
        super.onPause();
        state=0;//可以说话
    }

    @Override
    public void startspeaking(String string) {
        state=1;
        satrtspeech(string);//讯飞的读文字功能
    }
    public void pausespeaking(){
        mTts.pauseSpeaking();
    }
    public void restartspeaking(){
        mTts.resumeSpeaking();
    }
    public void  stopspeaking(){
        mTts.stopSpeaking();
    }

    private void select(int i){


        mScroller.setmDuration(4 * 100);// 切换时间，毫秒值
        viewpage.setCurrentItem(i);

    }
    public void listen(){
        FlowerCollector.onEvent(ProgressActivity.this, "iat_recognize");

        //   mResultText.setText(null);// 清空显示内容
        context=null;
        mIatResults.clear();
        // 设置参数
        setParamn();
        boolean isShowDialog = mSharedPreferences.getBoolean(
                getString(R.string.pref_key_iat_show), false);
        if (isShowDialog) {
            // 显示听写对话框
            mIatDialog.setListener(mRecognizerDialogListener);
            mIatDialog.show();
            showTip(getString(R.string.text_begin));
        } else {
            // 不显示听写对话框
            ret = mIat.startListening(mRecognizerListener);
            if (ret != ErrorCode.SUCCESS) {
                showTip("听写失败,错误码：" + ret);
            } else {
                showTip(getString(R.string.text_begin));
            }
        }

    }


    private String getFragmentTag(int position){
//看源代码 android.support.v4.app.FragmentPagerAdapter
        return "android:switcher:"+R.id.pager+":"+position;
    }

    public void sendlocation(int x,int y){
        this.x=x;
        this.y=y;
        Toast toast=Toast.makeText(getApplicationContext(), "x="+x+"y="+y, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void clickstartbutton(){
        new Thread () {
            public void run () {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                            MotionEvent.ACTION_DOWN, 592+2, 1423+5, 0));
                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                            MotionEvent.ACTION_UP, 592+2, 1423+5, 0));

                } catch(Exception e) {

                }
            }
        }.start();
    }
    public void clicknextbutton(){

        new Thread () {
            public void run () {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                            MotionEvent.ACTION_DOWN, 201+2, 1491+5, 0));
                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                            MotionEvent.ACTION_UP, 201+2, 1491+5, 0));

                } catch(Exception e) {

                }
            }
        }.start();
    }





    //发送推荐产品请求
    private void requestRecommandData() {

        RequestCenter.requeststep(getname,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                model = (HotvegetableModel) responseObj;
                //更新UI
                showSuccessView();

            }

            @Override
            public void onFailure(Object reasonObj) {
                //显示请求失败View
                showErrorView();

            }
        });




    }
    private void showSuccessView() {

//        String nu=model.result.get(0).img;
//
//        HotvegetablenModel modeln=(HotvegetablenModel) model.result.get(0);

//        String uu=model.result.get(0).material;
//        Toast.makeText(getApplicationContext(), uu,
//                Toast.LENGTH_SHORT).show();
        if(model !=null){
            for(int i=0;i<3;i++){
                stepText[i]=model.result.get(0).step.get(i).stepName;
                Toast.makeText(getApplicationContext(), stepText[i],
                        Toast.LENGTH_SHORT).show();
            }


        }
        else {
            showErrorView();
        }

    }

    private void showErrorView() {
        Log.d("lodd", "showSuccessView: hjfj91919515");

    }






}
