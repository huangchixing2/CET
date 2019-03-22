package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.utils.Constans;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SetContactActivity extends Activity {
    private LinearLayout back;
    private TextView complete_tv;
    private EditText user_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_contact);
        initView();
    }


    public void postUserData() {
        //发出提交账号请求
        String userName = user_et.getText().toString().trim();
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("UserName", userName).build();

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
                        Log.d("contact","用户名修改成功");
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }


        });
    }

    private void initView() {
        back = (LinearLayout) findViewById(R.id.my_account_manage_back_ll);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        complete_tv = (TextView) findViewById(R.id.my_account_manage_complete);
        complete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户名长度是否不能大于16
                if (user_et.getText().toString().length() > 16) {
                    user_et.setText("");
                    Toast.makeText(SetContactActivity.this, "用户名不能超过16位", Toast.LENGTH_SHORT).show();
                    return;
                }
                postUserData();
                finish();
            }
        });
        user_et = (EditText) findViewById(R.id.my_set_contact_et);
        user_et.addTextChangedListener(new TextWatcher() {
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