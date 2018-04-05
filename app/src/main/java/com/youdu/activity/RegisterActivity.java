package com.youdu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.youdu.R;

/**
 * Created by kyrie on 2017/5/10.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name;
    private EditText  school;
    private EditText mail;
    private EditText status;
    private EditText pass;
    private EditText phone;
    private EditText verify;
    private ImageView input;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_layout);

        // getWindow().setStatusBarColor(0xFF00a9FF);

        //添加默认要显示的fragment
        init();


    }

    public void init(){
        name=(EditText) findViewById(R.id.edittext_name);
        mail=(EditText) findViewById(R.id.edittext_mail);
        pass=(EditText)findViewById(R.id.edittext_pass);
        status=(EditText)findViewById(R.id.edittext_status);
        phone=(EditText) findViewById(R.id.edittext_phonenumber);
        verify=(EditText)findViewById(R.id.edittext_verify);
        school=(EditText)findViewById(R.id.edittext_school);
        input=(ImageView)findViewById(R.id.verify_input_pressed);
        input.setOnClickListener(this);
    }
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.verify_input_pressed:
                if (name.getText().toString() == "") {
                    name.setTextColor(Color.RED);
                    name.setText("此项不能为空");
                }


                if (mail.getText().toString() == "") {
                    mail.setTextColor(Color.RED);
                    mail.setText("此项不能为空");
                }

                if (phone.getText().toString() == "") {
                    phone.setTextColor(Color.RED);
                    phone.setText("此项不能为空");
                }

                if (pass.getText().toString() == "") {
                    pass.setTextColor(Color.RED);
                    pass.setText("此项不能为空");
                }

                if (school.getText().toString() == "") {
                    school.setTextColor(Color.RED);
                    school.setText("此项不能为空");
                }

                if (status.getText().toString() == "") {
                    status.setTextColor(Color.RED);
                    status.setText("此项不能为空");
                }

                if (verify.getText().toString() == "") {
                    verify.setTextColor(Color.RED);
                    verify.setText("此项不能为空");
                }




                else {
                    Intent intent1 = new Intent(this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
                }

        }
    }



}
