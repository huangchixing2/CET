package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.utils.Constans;
import com.electric.cet.mobile.android.pq.utils.MD5Utils;
import com.electric.cet.mobile.android.pq.utils.OkHttpUtils;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 *
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Button login_bt;
    private EditText username_et;
    private EditText psw_et;
    private Context context = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initView();
    }

    private void initView() {
        login_bt = (Button) findViewById(R.id.login_login);
        username_et = (EditText) findViewById(R.id.login_username);
        psw_et = (EditText) findViewById(R.id.login_psw);
        login_bt.setOnClickListener(this);

    }


    public void requstFocus(EditText et, String hint, int hintColor, boolean needFocus) {
        if (hint == null) {
            hint = "请输入六位密码";
        }
        et.setHint(hint);
        et.setHintTextColor(hintColor);
        if (needFocus) {
            et.requestFocus();
        }
    }

//判空检查
    private boolean checkNull() {

        //用户名是否为空
        if (TextUtils.isEmpty(username_et.getText().toString())) {
            Toast.makeText(LoginActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
        }
        //用户名长度是否小于6
        if (username_et.getText().toString().length() < 6) {
            username_et.setText("");
            Toast.makeText(LoginActivity.this,"用户名格式错误",Toast.LENGTH_SHORT).show();
            return true;
        }
        //密码是否为空
        if (TextUtils.isEmpty(psw_et.getText().toString())) {
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }
        //密码长度是否小于6
        if (psw_et.getText().toString().length() < 6) {
            psw_et.setText("");
            Toast.makeText(LoginActivity.this,"密码格式错误",Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }


    //用户登录
    public void login() {
        final String UserName = username_et.getText().toString().trim();
        final String passWd = psw_et.getText().toString().trim();
        final String EncryptPwd = MD5Utils.getDigest(passWd);
        System.out.println("加密后的密码为 " + EncryptPwd);

        //sp保存数据
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("UserName", UserName);
        editor.putString("passWd", passWd);
        editor.putString("EncryptPwd", EncryptPwd);
        editor.apply();

        if (checkNull()) {
            return;
        }


//        //执行用户名密码校验
//        if (!TextUtils.isEmpty(UserName) && !TextUtils.isEmpty(passWd)){
            OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("UserName", UserName).add("EncryptPwd ", EncryptPwd).build();

        final Request request = new Request.Builder().url(Constans.URL_LOGIN).post(formBody).build();
        OkHttpUtils.postLogin(context, Constans.URL_LOGIN, request);

//        }


    }

    /**
     * @param v add login logic
     *          author huangchixing
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login:
                login();  //处理登录事件
                finish();
                break;
            default:
                break;
        }
    }

}
