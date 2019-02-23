package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.electric.cet.mobile.android.pq.R;

public class MyAboutActivity extends Activity {
    private LinearLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_about);
        initView();
    }

    private void initView(){
        back = (LinearLayout) findViewById(R.id.my_about_back_ll);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
