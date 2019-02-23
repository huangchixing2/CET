package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.R;

public class EquipmentCollectAddActivity extends Activity implements View.OnClickListener {
    private LinearLayout back_iv;
    private TextView save_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_collect_add);
        initView();
        initData();
    }

    private void  initView(){
        back_iv = (LinearLayout) findViewById(R.id.equipment_collect_add_back_ll);
        save_tv = (TextView) findViewById(R.id.equipment_collect_add_save);
        back_iv.setOnClickListener(this);
        save_tv.setOnClickListener(this);
    }

    private void initData(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.equipment_collect_add_back_ll:
                finish();
                break;
            case R.id.equipment_collect_add_save:
                finish();
                break;
            default:
                break;
        }
    }
}
