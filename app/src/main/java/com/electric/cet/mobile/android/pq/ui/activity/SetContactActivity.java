package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.R;

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

    private void initView(){
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
                if(s.length() > 0){
                    complete_tv.setEnabled(true);
                    complete_tv.setBackgroundResource(R.drawable.cet_equipment_collect_edit);
                }else{
                    complete_tv.setEnabled(false);
                    complete_tv.setBackgroundResource(R.drawable.cet_my_complete);
                }
            }
        });
    }
}
