package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.electric.cet.mobile.android.pq.Bean.LoginBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.utils.Constans;
import com.electric.cet.mobile.android.pq.utils.MD5Utils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.electric.cet.mobile.android.pq.utils.OkHttpUtils.response;

/**
 *
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Button login_bt;
    private EditText username_et;
    private EditText psw_et;
    private Context context = LoginActivity.this;
    private SharedPreferences sp;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"登录成功!",Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initView();
    }

    private void initView() {

        sp = context.getSharedPreferences("TokenData", Context.MODE_PRIVATE);
        login_bt = (Button) findViewById(R.id.login_login);
        username_et = (EditText) findViewById(R.id.login_username);
        psw_et = (EditText) findViewById(R.id.login_psw);

        //判断登录状态
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (sp.getBoolean("isLogin", false)) {
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


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


    //用户登录
    public void login() {
        final String UserName = username_et.getText().toString().trim();
        final String passWd = psw_et.getText().toString().trim();
        final String EncryptPwd = MD5Utils.getDigest(passWd);
        System.out.println("加密后的密码为 " + EncryptPwd);


        //发起post请求给服务器
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("UserName", UserName).add("EncryptPwd ", EncryptPwd).build();

        final Request request = new Request.Builder().url(Constans.URL_LOGIN).post(formBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
                if (!response.isSuccessful()) {
                    Log.i("请求情况：", "请求失败");
                    Looper.prepare();
                    //提示用户无网络
                    Toast.makeText(LoginActivity.this, "没有网络", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    Log.i("响应状态", "响应成功");
                    String loginBody = response.body().string();
                    Gson gson = new Gson();
                    LoginBean loginData = gson.fromJson(loginBody, LoginBean.class);

                    int loginResultCode = loginData.getCode();
                    Log.i("resultcode", loginResultCode + "");
                    int ResponseCode = response.code();

                    //无法获取token
                    //响应成功,判断状态码
                    if (ResponseCode == 200) {
                        Log.i("登录状态", "登录成功");
                        String data = loginData.getData().toString(); //这个就是token
                        //保存token
                        //sp保存数据
                        SharedPreferences sp = context.getSharedPreferences("TokenData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("UserName", loginBody);
                        editor.putString("passWd", passWd);
                        editor.putString("EncryptPwd", EncryptPwd);
                        editor.putBoolean("isLogin", true);
                        editor.apply();

                        handler.sendEmptyMessage(100);


                    }
                }
            }
        });

//        OkHttpUtils.postLogin(context, Constans.URL_LOGIN, request, handler);


    }

    //点击两次返回键退出
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


    /**
     * @param v add login logic
     *          author huangchixing
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login:

                //密码长度为6位数，且不能为空
                if (TextUtils.isEmpty(username_et.getText())) {
                    Toast.makeText(LoginActivity.this, R.string.phone_num_can_not_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(psw_et.getText())) {
                    Toast.makeText(LoginActivity.this, R.string.passwd_can_not_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }
                //用户名长度是否小于6
                if (username_et.getText().toString().length() < 6) {
                    username_et.setText("");
                    Toast.makeText(LoginActivity.this, "用户名不能少于6位", Toast.LENGTH_SHORT).show();
                    return;
                }

                //用户名长度是否不能大于16
                if (username_et.getText().toString().length() > 16) {
                    username_et.setText("");
                    Toast.makeText(LoginActivity.this, "用户名不能超过16位", Toast.LENGTH_SHORT).show();
                    return;
                }

                //密码长度是否小于6
                if (psw_et.getText().toString().length() < 6) {
                    psw_et.setText("");
                    Toast.makeText(LoginActivity.this, "密码格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                login();  //处理登录事件
                break;
            default:
                break;
        }
    }

}
