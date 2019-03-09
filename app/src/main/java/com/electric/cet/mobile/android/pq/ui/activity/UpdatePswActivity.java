package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.utils.Constans;
import com.electric.cet.mobile.android.pq.utils.MD5Utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdatePswActivity extends Activity {
    private LinearLayout back;
    private TextView complete_tv;
    private EditText user_et_old;
    private EditText user_et_new;
    private EditText user_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_psw);
        initView();
    }

    //修改密码后上传到服务器
    public void postPwdData(){

//发出提交账号请求
        String old_Pwd = user_et_old.getText().toString().trim();
        String new_Pwd = user_et_new.getText().toString().trim();
        String confirm_Pwd = user_confirm.getText().toString().trim();

        if(!TextUtils.isEmpty(old_Pwd)){
            if(!TextUtils.isEmpty(new_Pwd)){
                if(new_Pwd.equals(confirm_Pwd)){
                    //加密新密码
                    final String NewEncryptPwd = MD5Utils.getDigest(new_Pwd);
                    //提交服务器
                    OkHttpClient client = new OkHttpClient();
                    RequestBody formBody = new FormBody.Builder().add("NewEncryptPwd", NewEncryptPwd).build();

                    final Request request = new Request.Builder().url(Constans.URL_USERDATA).post(formBody).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("HUANGCHIXING", "用户数据请求失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                final String str = response.body().string();
                                String jsonData = str;
                                Log.d("huangchixinguu", "用户信息提交打印" + jsonData);
                                Log.d("huangchixinguu", "用户信息提交成功");
                                if(response.code()==200){
                                    Log.d("passwd","密码修改成功");
                                }

                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }


                    });
                }
                else{

                }
            } else {

            }
        }


    }

    private void initView() {
        back = (LinearLayout) findViewById(R.id.my_account_updatepsw_back_ll);
        complete_tv = (TextView) findViewById(R.id.my_account_manage_complete);
        complete_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击完成后向服务器发出请求，并且加密
                postPwdData();

                finish();
            }
        });
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置旧密码
        user_et_old = (EditText) findViewById(R.id.cet_my_old_psw_et);
        user_et_old.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    complete_tv.setEnabled(true);
                    complete_tv.setBackgroundResource(R.drawable.cet_equipment_collect_edit);
                } else {
                    complete_tv.setEnabled(false);
                    complete_tv.setBackgroundResource(R.drawable.cet_my_complete);
                }
            }
        });
        //设置新密码
        user_et_new = (EditText) findViewById(R.id.cet_my_new_psw_et);
        user_et_new.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    complete_tv.setEnabled(true);
                    complete_tv.setBackgroundResource(R.drawable.cet_equipment_collect_edit);
                } else {
                    complete_tv.setEnabled(false);
                    complete_tv.setBackgroundResource(R.drawable.cet_my_complete);
                }
            }
        });
        //确认密码
        user_confirm = (EditText) findViewById(R.id.cet_my_confirm_new_psw_et);
        user_confirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    complete_tv.setEnabled(true);
                    complete_tv.setBackgroundResource(R.drawable.cet_equipment_collect_edit);
                } else {
                    complete_tv.setEnabled(false);
                    complete_tv.setBackgroundResource(R.drawable.cet_my_complete);
                }
            }
        });

    }
}
