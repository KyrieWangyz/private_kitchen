package com.youdu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.youdu.R;

/**
 * Created by kyrie on 2017/5/10.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private ImageView ima;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmessage);
        inti();
    }

    public void inti() {
        ed1 = (EditText) findViewById(R.id.editkdd);
        ed2 = (EditText) findViewById(R.id.edittime);
        ed3 = (EditText) findViewById(R.id.editdeadline);
        ima = (ImageView) findViewById(R.id.input);
        ima.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.input:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }

    }
}
