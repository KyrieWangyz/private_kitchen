package com.youdu.view.fragment.guide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;
import com.youdu.Progress.Music;
import com.youdu.R;
import com.youdu.activity.IatDemo;
import com.youdu.activity.IatSettings;
import com.youdu.okhttp.listener.Pausethevoice;
import com.youdu.okhttp.listener.Resumethevoice;
import com.youdu.okhttp.listener.Sendlocation;
import com.youdu.okhttp.listener.Startlisten;
import com.youdu.okhttp.listener.Startthevoice;
import com.youdu.okhttp.listener.Stopthevoice;
import com.youdu.util.JsonParser;
import com.youdu.view.HorizontalProgressBarWithNumber;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kyrie on 2017/8/28.
 */

public class GuideFragmentnew extends Fragment implements View.OnClickListener {
    private ImageView imageView;
    private SpeechSynthesizer mTts;
    private static String TAG = IatDemo.class.getSimpleName();
    // 语音听写对象
    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    private EditText mResultText;
    private String mResult;
    private Toast mToast;
    private SharedPreferences mSharedPreferences;
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    // 语记安装助手类
//	ApkInstaller mInstaller;

    private boolean mTranslateEnable = false;
    ///////////////////////////////////////////////////////////////////////////////////////////////////


    public static final String IMG_ID = "IMG_ID";
    public static final String TEXT_ID = "TEXT_ID";
    public static final String BACK_ID = "BACK_ID";
    public static final String NUMBER_ID = "NUMBER_ID";


    @BindView(R.id.stepNumber)
    TextView stepNumber;

    private int i = 0;
    private Timer timer = null;
    private TimerTask task = null;
    int time = 30, counttime = 0;
    int time2=20;
    private Music music;
    private void init(View view) {
        imageView= (ImageView) getActivity().findViewById(R.id.personalcenter_icon_set);
        imgView= (ImageView) view.findViewById(R.id.imgView);
        text= (TextView) view.findViewById(R.id.text);
        idProgressbar01= (HorizontalProgressBarWithNumber) view.findViewById(R.id.id_progressbar01);
//        idprogressbar02= (HorizontalProgressBarWithNumber) view.findViewById(R.id.id_progressbar02);
        goIng= (TextView) view.findViewById(R.id.goIng);
        sumTime= (TextView) view.findViewById(R.id.sumTime);
     //   sumTime2=(TextView)view.findViewById(R.id.sumTime2);
        start= (Button) view.findViewById(R.id.start);
        next= (Button) view.findViewById(R.id.next);

        stepNumber= (TextView) view.findViewById(R.id.stepNumber);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Toast.makeText(getActivity(), "开始语音教学", Toast.LENGTH_SHORT).show();

                        btnClickJudge();
                getlocation();


            }
        });
        next.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    private static ViewPager viewPager1;
  //  @BindView(R.id.imgView)
    ImageView imgView;
 //   @BindView(R.id.text)
    TextView text;
 //   @BindView(R.id.rootView)
    LinearLayout rootView;
    Unbinder unbinder;
  //  @BindView(R.id.id_progressbar01)
    HorizontalProgressBarWithNumber idProgressbar01;
//    HorizontalProgressBarWithNumber idprogressbar02;
//    @BindView(R.id.goIng)
    TextView goIng;
//    @BindView(R.id.sumTime)
    TextView sumTime;
    TextView sumTime2;
 //   @BindView(R.id.next)
    Button next;
//    @BindView(R.id.start)
    Button start;

    private View view;

    @Override
    public void onStop() {
        super.onStop();
//        if( null != mTts ){
//            mTts.stopSpeaking();
//            // 退出时释放连接
//            mTts.destroy();
//        }
//        if( null != mIat ){
//            // 退出时释放连接
//            mIat.cancel();
//            mIat.destroy();
//        }

    }

    public static GuideFragmentnew newInstance(int imgResId, String textResId,
                                               int backId, String numberId) {
        GuideFragmentnew fragment = new GuideFragmentnew();
        Bundle bundle = new Bundle();
        bundle.putInt(IMG_ID, imgResId);
        bundle.putString(TEXT_ID, textResId);
        bundle.putInt(BACK_ID, backId);
        bundle.putString(NUMBER_ID, numberId);
        fragment.setArguments(bundle);//用此方法来传递参数而不是构造函数，因为切换横屏时fargment会调用无参构造函数，而使有参的失效
        return fragment;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_guide, container, false);

        unbinder = ButterKnife.bind(this, view);
        init(view);
        initData();
        initrescourse();//加载讯飞的资源
        initIat();



        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getlocation();
//                /**
//                 *要执行的操作
//                 */
//            }
//        }, 500);
    }


    private void initIat() {
        mIat = SpeechRecognizer.createRecognizer(getActivity(), mInitListener);

        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(getActivity(), mInitListener);

//        mSharedPreferences = getSharedPreferences(IatSettings.PREFER_NAME,
//                Activity.MODE_PRIVATE);
        mToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
        //mResultText = ((EditText) view.findViewById(R.id.iat_text));
    }


    private void initData() {
        music=new Music(getContext());
        initBundle();
        initProgress();


    }

    private void initProgress() {
        idProgressbar01.setMax(time);
        sumTime.setText(setTime(time));
        idProgressbar01.setProgress(0);

//        idprogressbar02.setMax(time2);
//        sumTime2.setText(setTime(time2));
//        idprogressbar02.setProgress(0);
    }

    private void initBundle() {
        Log.d("initBundle","initBundle111111");
        Bundle args = getArguments();
        int imgId = args.getInt(IMG_ID);
       // int textId = args.getInt(TEXT_ID);//////////////////////////////////////////////先注释掉测试下
        String textId=args.getString(TEXT_ID);
        int backId = args.getInt(BACK_ID);
        String numberId = args.getString(NUMBER_ID);
        imgView.setImageResource(imgId);
        text.setText(textId);

        stepNumber.setText(numberId);

    }

    public static void setViewPager(ViewPager viewPager2) {
        viewPager1 = viewPager2;
    }

    ;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

//        if( null != mTts ){
//            mTts.stopSpeaking();
//            // 退出时释放连接
//            mTts.destroy();
//        }
//        if( null != mIat ){
//            // 退出时释放连接
//            mIat.cancel();
//            mIat.destroy();
//        }

    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            int j=time-1;
            int progress = idProgressbar01.getProgress();
            idProgressbar01.setProgress(++progress);
            goIng.setText(setTime(msg.arg1));
            startTime();
            if (msg.arg1 == time) {
                start.setEnabled(false);
                stopTime();
                ((Startlisten)getActivity()).listen();
            }
//            if(msg.arg1==1||msg.arg1==2){
//
////                try {
////                    music.playMusic(true);
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//            }
            if(msg.arg1==3){
                String string=(String)text.getText();
                ((Startthevoice) getActivity()).startspeaking(string);
            }
            if(msg.arg2==2){
                ((Resumethevoice) getActivity()).restartspeaking();
            }


        }
    };

    public void btnClickJudge() {
        String startText = start.getText().toString();
        if (startText.equals("开始")) {
            start.setText("暂停");
            //获取控件坐标并发给activity
        //    Toast.makeText(getActivity(), "11", Toast.LENGTH_SHORT).show();
            startTime();
        }
        if(startText.equals("暂停")){
            start.setText("重启");
            pauseTime();
        }
        if(startText.equals("重启")){
            start.setText("暂停");
            Toast.makeText(getActivity(), "11", Toast.LENGTH_SHORT).show();
            restartTime();
        }
    }

////////////////////////////
    private void startTimefrombegain(){//重新计时，从头开始念
        i=0;
        idProgressbar01.setProgress(0);
        goIng.setText(setTime(0));
        timer.cancel();
       startTime();
    }
    private void restartTime(){
        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                i++;
                Message message = mHandler.obtainMessage();
                message.arg1 = i;
                message.arg2=2;//二表示从上一次暂停的地方继续
                mHandler.sendMessage(message);

            }
        };

        timer.schedule(task, 1000);

    }



    //////////////////////////



    private void startTime() {
        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                i++;
                Message message = mHandler.obtainMessage();
                message.arg1 = i;
                message.arg2=1;//一表示不是继续，是从头开始
                mHandler.sendMessage(message);

            }
        };

        timer.schedule(task, 1000);
    }

    public void stopTime() {
        ((Stopthevoice) getActivity()).stopspeaking();///////////////////////////////////////////暂停语音
        timer.cancel();
    }




    public void pauseTime(){
        ((Pausethevoice) getActivity()).pausespeaking();///////////////////////////////////////////暂停语音
        timer.cancel();
    }


    private String setTime(int sumtime) {//将一个数字转换为B
        int minute = sumtime / 60;
        int second = sumtime % 60;
        String sum = null;
        if (minute == 0) {
            if (second >= 10) {
                sum = "00:" + second;
            } else {
                sum = "00:0" + second;
            }
        } else {
            if (minute >= 10) {
                if (second >= 10) {
                    sum = minute + ":" + second;
                } else {
                    sum = minute + ":0" + second;
                }
            } else {
                if (second >= 10) {
                    sum = "0" + minute + ":" + second;
                } else {
                    sum = "0" + minute + ":0" + second;
                }
            }

        }
        return sum;
    }



    public void onViewClicked() {
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }
    /**
     * 参数设置
     *
     * @param
     * @return
     */
    public void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

        //this.mTranslateEnable = mSharedPreferences.getBoolean( this.getString(R.string.pref_key_translate), false );
        if( mTranslateEnable ){
            Log.i( TAG, "translate enable" );
            mIat.setParameter( SpeechConstant.ASR_SCH, "1" );
            mIat.setParameter( SpeechConstant.ADD_CAP, "translate" );
            mIat.setParameter( SpeechConstant.TRS_SRC, "its" );
        }

//        String lag = mSharedPreferences.getString("iat_language_preference",
//                "mandarin");
//        if (lag.equals("en_us")) {
//            // 设置语言
//            mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
//            mIat.setParameter(SpeechConstant.ACCENT, null);
//
//            if( mTranslateEnable ){
//                mIat.setParameter( SpeechConstant.ORI_LANG, "en" );
//                mIat.setParameter( SpeechConstant.TRANS_LANG, "cn" );
//            }
//        } else {
//            // 设置语言
//            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
//            // 设置语言区域
//            mIat.setParameter(SpeechConstant.ACCENT, lag);
//
//            if( mTranslateEnable ){
//                mIat.setParameter( SpeechConstant.ORI_LANG, "cn" );
//                mIat.setParameter( SpeechConstant.TRANS_LANG, "en" );
//            }
//        }

//        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
//        mIat.setParameter(SpeechConstant.VAD_BOS, mSharedPreferences.getString("iat_vadbos_preference", "100000"));
//
//        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
//        mIat.setParameter(SpeechConstant.VAD_EOS, mSharedPreferences.getString("iat_vadeos_preference", "1000"));
//
//        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
//        mIat.setParameter(SpeechConstant.ASR_PTT, mSharedPreferences.getString("iat_punc_preference", "1"));
//
//        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
//        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
//        mIat.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
//        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/iat.wav");
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
    private void printTransResult (RecognizerResult results) {
        String trans  = JsonParser.parseTransResult(results.getResultString(),"dst");
        String oris = JsonParser.parseTransResult(results.getResultString(),"src");

        if( TextUtils.isEmpty(trans)||TextUtils.isEmpty(oris) ){
            showTip( "解析结果失败，请确认是否已开通翻译功能。" );
        }else{
            mResult= "原始语言:\n"+oris+"\n目标语言:\n"+trans ;
        }

    }
    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

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

        mResult=resultBuffer.toString();
//        mResultText.setText(resultBuffer.toString());
//        mResultText.setSelection(mResultText.length());

    }
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
                showTip(error.getPlainDescription(true));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personalcenter_icon_set:
            Intent intents = new Intent(getActivity(), IatSettings.class);
            startActivity(intents);
                break;
            case R.id.next:
                startTimefrombegain();

                getlocationbutton1();
        }
    }
    private void initrescourse(){//加载讯飞的资源
        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源

//        mSharedPreferences = getSharedPreferences(IatSettings.PREFER_NAME,
//                Activity.MODE_PRIVATE);
        mToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
    }
    private void speak(){
        int ret=0;
        FlowerCollector.onEvent(getActivity(), "iat_recognize");

        mResult=null;// 清空显示内容
        mIatResults.clear();
        // 设置参数
        setParam();
//                boolean isShowDialog = mSharedPreferences.getBoolean(
//                        getString(R.string.pref_key_iat_show),false);
        boolean isShowDialog=false;
        if (isShowDialog) {
            // 显示听写对话框
            mIatDialog.setListener(mRecognizerDialogListener);
            mIatDialog.show();
            showTip(getString(R.string.text_begin));
        }
        else {
            // 不显示听写对话框
            ret = mIat.startListening(mRecognizerListener);
            if (ret != ErrorCode.SUCCESS) {
                showTip("听写失败,错误码：" + ret);
            }
            else {
                showTip(getString(R.string.text_begin));
            }
        }
    }

    public void getlocation() {
        int[] location = new int[2];

        start.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
//        if (x == 0 || y == 0) {
//            Rect rect = new Rect();
//            start.getGlobalVisibleRect(rect);
//            x = rect.left;
//            y = rect.top;
//        }


        ((Sendlocation)getActivity()).sendlocation(x,y);
    }
    public void getlocationbutton1(){
        int[] location = new int[2];

        next.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        ((Sendlocation)getActivity()).sendlocation(x,y);
    }
}
