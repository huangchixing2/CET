package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class MyPwdActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    private EditText et_orgin;
    private EditText et_new;
    private EditText et_confirm;
    private TextView tv_confirm;
    private LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_psw);
        initView();
        initData();
    }


    //修改密码后上传到服务器
    public void postPwdData() {

        String old_Pwd = et_orgin.getText().toString().trim();
        String new_Pwd = et_new.getText().toString().trim();
        String confirm_Pwd = et_confirm.getText().toString().trim();
        final String NewEncryptPwd = MD5Utils.getDigest(new_Pwd);
        //提交服务器
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("NewEncryptPwd",NewEncryptPwd).build();

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
                    if (response.code() == 200) {
                        Log.d("passwd", "密码修改成功");
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }


        });
    }

    private ProgressDialog dialog;
    @Override public void onClick (View v){
        if (checkNull()) {
            return;
        }
        //新密码与确认密码是否一致
        if (!et_confirm.getText().toString().equals(et_new.getText().toString())) {
            et_confirm.setText("");
            requstFocus(et_confirm, "两次密码不一致", Color.RED, true);
            return;
        }
        dialog = ProgressDialog.show(this, "", "修改中,请稍后...", true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断旧密码是否正确
                //旧密码需要从sp中恢复
                if (!"123456".equals(et_orgin.getText().toString())) {
                    et_orgin.setText("");
                    requstFocus(et_orgin, "原密码错误", Color.RED, true);
                } else {
                    Toast.makeText(MyPwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        }, 3000);
        postPwdData();
    }


    private void initView() {
        et_orgin = (EditText) findViewById(R.id.cet_my_old_psw_et);
        et_confirm = (EditText) findViewById(R.id.cet_my_confirm_new_psw_et);
        et_new = (EditText) findViewById(R.id.cet_my_new_psw_et);
        tv_confirm = (TextView) findViewById(R.id.my_account_manage_complete);
        et_confirm.addTextChangedListener(this);
        et_orgin.addTextChangedListener(this);
        et_new.addTextChangedListener(this);
        tv_confirm.setSelected(false);
        tv_confirm.setOnClickListener(this);

        tv_confirm.setOnClickListener(this);




        back = (LinearLayout) findViewById(R.id.my_account_updatepsw_back_ll);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_orgin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                clearAll();
                return false;
            }
        });
        et_new.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                clearAll();
                return false;
            }
        });
        et_confirm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                clearAll();
                return false;
            }
        });
    }

    private void initData() {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        /**
         * 当三个EditText的内容都不为空的时候，
         * Button为蓝色，否则为灰色通过
         * bt_confirm.setSelected(true)实现蓝色，
         *  bt_confirm.setSelected(false);实现灰色
         */
        if (!TextUtils.isEmpty(et_confirm.getText().toString()) && !TextUtils.isEmpty(et_orgin.getText().toString()) && !TextUtils.isEmpty(et_new.getText().toString())) {
            tv_confirm.setSelected(true);
        } else {
            tv_confirm.setSelected(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

//    private ProgressDialog dialog;
//    @Override
//    public void onClick(View v) {
//        if(checkNull()){
//            return;
//        }
//        if(!et_confirm.getText().toString().equals(et_new.getText().toString())){
//            et_confirm.setText("");
//            requstFocus(et_confirm, "两次密码不一致", Color.RED,true);
//            return;
//        }
//        dialog=ProgressDialog.show(this,"","修改中,请稍后...",true);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(!"123456".equals(et_orgin.getText().toString())){
//                    et_orgin.setText("");
//                    requstFocus(et_orgin, "原密码错误", Color.RED, true);
//                }else{
//                    Toast.makeText(MyPwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                }
//                dialog.dismiss();
//            }
//        },3000);
//    }

    private boolean checkNull() {
        //还需要判断原始密码是否正确？

        //原始密码是否为空
        if (TextUtils.isEmpty(et_orgin.getText().toString())) {
            requstFocus(et_orgin, null, Color.GRAY, true);
            return true;
        }
        //原始密码长度是否小于6
        if (et_orgin.getText().toString().length() < 6) {
            et_orgin.setText("");
            requstFocus(et_orgin, "原密码格式错误", Color.RED, true);
            return true;
        }
        //新密码是否正确
        if (TextUtils.isEmpty(et_new.getText().toString())) {
            requstFocus(et_new, null, Color.GRAY, true);
            return true;
        }
        //新密码长度是否小于6
        if (et_new.getText().toString().length() < 6) {
            et_new.setText("");
            requstFocus(et_new, "新密码格式错误", Color.RED, true);
            return true;
        }
        //确认密码是否为空
        if (TextUtils.isEmpty(et_confirm.getText().toString())) {
            requstFocus(et_confirm, null, Color.GRAY, true);
            return true;
        }
        return false;
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

    public void clearAll() {
        requstFocus(et_orgin, null, Color.GRAY, false);
        requstFocus(et_new, null, Color.GRAY, false);
        requstFocus(et_confirm, null, Color.GRAY, false);
    }


}
