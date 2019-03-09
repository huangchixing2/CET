package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.utils.Constans;
import com.electric.cet.mobile.android.pq.utils.MD5Utils;
import com.electric.cet.mobile.android.pq.utils.OkHttpUtils;

import java.io.InputStream;

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
//    public static String url_login  = "http://192.168.2.102/LowLineSys/user/login";


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


//    public void validata(){
//
//        if(TextUtils.isEmpty(username_et.getText())){
//                    Toast.makeText(LoginActivity.this, R.string.phone_num_can_not_be_empty, Toast.LENGTH_LONG).show();
//                    return ;
//                }
//        if (TextUtils.isEmpty(psw_et.getText())) {
//                    Toast.makeText(LoginActivity.this, R.string.passwd_can_not_be_empty, Toast.LENGTH_LONG).show();
//                    return ;
//                }
//
//    }

    //用户登录
    public void login() {
        final String UserName = username_et.getText().toString().trim();
        final String passWd = psw_et.getText().toString().trim();
        final String EncryptPwd = MD5Utils.getDigest(passWd);
        System.out.println("加密后的密码为 " + EncryptPwd);
//        //执行用户名密码校验
        if (!TextUtils.isEmpty(UserName) && !TextUtils.isEmpty(passWd)){
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder().add("UserName", UserName).add("EncryptPwd ", EncryptPwd).build();

            final Request request = new Request.Builder().url(Constans.URL_LOGIN).post(formBody).build();
            OkHttpUtils.postLogin(context,Constans.URL_LOGIN, request);
        }



//               postRequest(username,EncryptPwd );


    }

    /**
     * @param v add login logic
     *          author huangchixing
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login:
//                if(TextUtils.isEmpty(username_et.getText())){
//                    Toast.makeText(LoginActivity.this, R.string.phone_num_can_not_be_empty, Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(psw_et.getText())) {
//                    Toast.makeText(LoginActivity.this, R.string.passwd_can_not_be_empty, Toast.LENGTH_LONG).show();
//                    return;
//                }

//                login(username_et.getText().toString(), psw_et.getText().toString());
                //执行用户名密码校验
//                validata();
                login();  //处理登录事件
//                final String UserName = username_et .getText().toString().trim();
//                final String EncryptPwd  = MD5Utils.getDigest(psw_et.getText().toString());
//                System.out.println("加密后的密码为 " + EncryptPwd );
//
////               postRequest(username,EncryptPwd );
//                 RequestBody formBody = new FormBody.Builder()
//                .add("UserName",UserName)
//                .add("EncryptPwd ",EncryptPwd )
//                .build();
//
//                final Request request = new Request.Builder()
//                        .url(Constans.URL_LOGIN)
//                        .post(formBody)
//                        .build();
//                OkHttpUtils.postLogin(Constans.URL_LOGIN,request);

//                Intent intent = new Intent();
//                intent.setClass(this,MainActivity.class);
//                startActivity(intent);


                /**
                 * 调用MD5工具类
                 *
                 */
//                String EncryptPwd = MD5Utils.getDigest("111");  //把密码写死
//              Intent intent = new Intent();  //页面是否需要跳转？
//              intent.setClass(LoginActivity.this, MainActivity.class);
                finish();
                break;
            default:
                break;
        }
    }


//    /**
//     * post请求后台
//     * @param UserName
//     * @param EncryptPwd
//     */
//    private void postRequest(String UserName,String EncryptPwd )  {
//        //建立请求表单，添加上传服务器的参数
//        RequestBody formBody = new FormBody.Builder()
//                .add("UserName",UserName)
//                .add("EncryptPwd ",EncryptPwd )
//                .build();
//        //pc与客户端需要在同一个局域网，指定ip为PC的ip
//        //发起请求
//        final Request request = new Request.Builder()
//                .url("http://192.168.2.102/LowLineSys/user/login")
//                .post(formBody)
//                .build();
//        //新建一个线程，用于得到服务器响应的参数
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Response response = null;
//                try {
//                    //回调
//                    response = client.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
//
//                        String result = "请求结果："+response.body().string();
//                        System.out.println("result结果是 ：---" + result);
//
//                        System.out.println("登录成功");
//                        Message msg = Message.obtain();
//                        msg.obj = result;
//                        msg.what = 1;
//                        mHandler.sendMessage(msg);
//                    } else {
//                        throw new IOException("Unexpected code:" + response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


//    public void login(String UserName, String EncryptPwd) {
//        if(TextUtils.isEmpty(username_et.getText())){
//            Toast.makeText(LoginActivity.this, R.string.phone_num_can_not_be_empty, Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (TextUtils.isEmpty(psw_et.getText())) {
//            Toast.makeText(LoginActivity.this, R.string.passwd_can_not_be_empty, Toast.LENGTH_LONG).show();
//            return;
//        }
//        EncryptPwd = MD5Utils.getDigest(psw_et.getText().toString());
//        System.out.println("加密后的密码EncryptPwd为：" + EncryptPwd);
//    }


    /**
     * Created by huangchixing
     * use
     */
    public String getToken(String userName, String pwd, String token) {
        //自定义方法
        return token;
    }

    public String getAuth(InputStream is, String token) {
        String result = null;
        System.out.print("huangchixing");
        System.out.print("huangchixing2");
        return token;
    }


}
