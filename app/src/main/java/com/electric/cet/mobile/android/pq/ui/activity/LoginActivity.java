package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.electric.cet.mobile.android.pq.R;

import java.io.InputStream;

/**
 *
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Button login_bt;
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
        username_et = (EditText) findViewById(R.id.login_username);
        psw_et = (EditText) findViewById(R.id.login_psw);
        login_bt.setOnClickListener(this);
    }


    /**
     * @param v
     *  add login logic
     *  author huangchixing
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_login:
                if(TextUtils.isEmpty(username_et.getText())){
                    Toast.makeText(LoginActivity.this, R.string.phone_num_can_not_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(psw_et.getText())) {
                    Toast.makeText(LoginActivity.this, R.string.passwd_can_not_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }




                finish();
                break;
             default:
                 break;
        }
    }


/**
 * Created by huangchixing
 * use
 */
    public String getToken(String userName, String pwd, String token)
    {
        //自定义方法
        return token;
    }
    public String getAuth(InputStream is, String token)
    {
        String result = null;
        return token;
    }


}
