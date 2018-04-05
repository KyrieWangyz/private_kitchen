package com.youdu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.youdu.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kyrie on 2017/5/12.
 */

public class LoginAdActivity extends Activity {


    private int recLen = 3;
    private TextView txtView;
    Timer timer = new Timer();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginad);

        final Intent localIntent = new Intent(this, EnterActivity.class);
        Timer timer = new Timer();
        TimerTask tast = new TimerTask() {
            @Override
            public void run() {
                startActivity(localIntent);
                finish();
            }
        };
        timer.schedule(tast, 3000);
    }
}

