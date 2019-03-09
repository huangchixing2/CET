package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.R;

public class WorkingDetailActivity extends Activity {
    private LinearLayout back;
    private TextView install;
    private TextView online;
    private TextView usbable;
    private TextView sim;
    private TextView function;
    private TextView power;
    private TextView adjusttime;
    private TextView voltage;
    private TextView reactive_compensation;
    private TextView phase;
    private TextView capacity;

    private DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_working_detail);
        dataBean = (DataBean) getIntent().getSerializableExtra("data");
        initView();
        initData();
    }
    private void initView(){
        back = (LinearLayout) findViewById(R.id.equipment_working_detail_back_ll);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        install = (TextView) findViewById(R.id.equipment_working_detail_install_tv);
        online = (TextView) findViewById(R.id.equipment_working_detail_online_tv);
        usbable = (TextView) findViewById(R.id.equipment_working_detail_usbable_tv);
        sim = (TextView) findViewById(R.id.equipment_working_detail_sim_tv);
        function = (TextView) findViewById(R.id.equipment_working_detail_function_tv);
        power = (TextView) findViewById(R.id.equipment_working_detail_power_tv);
        adjusttime = (TextView) findViewById(R.id.equipment_working_detail_adjusttime_tv);
        voltage = (TextView) findViewById(R.id.equipment_working_detail_voltage_tv);
        reactive_compensation = (TextView) findViewById(R.id.equipment_working_detail_reactive_compensation_tv);
        phase = (TextView) findViewById(R.id.equipment_working_detail_phases_tv);
        capacity = (TextView) findViewById(R.id.equipment_working_detail_capacity_tv);
    }


    private void initData(){
        install.setText(dataBean.getInstalled()?"已安装":"未安装");
        online.setText(dataBean.getOnline()?"在线":"下线");
        usbable.setText(dataBean.getUsable()?"可用":"");
        sim.setText(dataBean.getSIMCardOnline()?"在线":"下线");
        function.setText(dataBean.getAbnormal()?"可用":"不可用");
        power.setText(dataBean.getPowerFailure()?"供电":"停电");
        adjusttime.setText(dataBean.getAdjustTime()+"");
        voltage.setText(dataBean.getVoltageRegulateNormal()?"正常":"异常");
        reactive_compensation.setText(dataBean.getReactiveCompensationNormal()?"正常":"异常");
        phase.setText(dataBean.getPhaseTypeId() + "");
        capacity.setText(dataBean.getCapacity() + "");
    }
}
