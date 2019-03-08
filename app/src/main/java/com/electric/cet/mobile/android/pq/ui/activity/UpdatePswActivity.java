package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.R;

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

    private void initView() {
        back = (LinearLayout) findViewById(R.id.my_account_updatepsw_back_ll);
        complete_tv = (TextView) findViewById(R.id.my_account_manage_complete);
        complete_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击完成后向服务器发出请求，并且加密

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
