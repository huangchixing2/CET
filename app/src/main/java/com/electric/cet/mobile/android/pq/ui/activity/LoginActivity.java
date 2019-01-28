package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.R;

public class LoginActivity extends Activity implements View.OnClickListener {
    private Button login_bt;
    private TextView regist_tv;
    private TextView forget_tv;
    private EditText username_et;
    private EditText psw_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initView();
    }

    private void initView(){
        login_bt = (Button) findViewById(R.id.login_login);
        regist_tv = (TextView) findViewById(R.id.login_regist);
        forget_tv = (TextView) findViewById(R.id.login_forget_psw);
        username_et = (EditText) findViewById(R.id.login_username);
        psw_et = (EditText) findViewById(R.id.login_psw);
        login_bt.setOnClickListener(this);
        regist_tv.setOnClickListener(this);
        forget_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_login:
                finish();
                break;
            case R.id.login_regist:
                break;
            case R.id.login_forget_psw:
                break;
             default:
                 break;
        }
    }
}
