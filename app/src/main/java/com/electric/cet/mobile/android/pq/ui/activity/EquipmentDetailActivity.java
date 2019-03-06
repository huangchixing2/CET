package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.R;

public class EquipmentDetailActivity extends Activity {
    private LinearLayout back;
    private TextView city;
    private TextView country;
    private TextView powersupply;
    private TextView type;
    private TextView route;
    private TextView zonearea;
    private TextView sim;
    private TextView vendor;
    private TextView model;
    private TextView phase;
    private TextView capacity;


    private DataBean dataBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_detail);
        dataBean = (DataBean) getIntent().getSerializableExtra("data");
        initView();
        initData();
    }
    private void initView(){
        back = (LinearLayout) findViewById(R.id.equipment_collect_detail_back_ll);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        city = (TextView) findViewById(R.id.equipment_collect_detail_city_tv);
        country = (TextView) findViewById(R.id.equipment_collect_detail_country_tv);
        powersupply = (TextView) findViewById(R.id.equipment_collect_detail_powersupply_tv);
        type = (TextView) findViewById(R.id.equipment_collect_detail_type_tv);
        route = (TextView) findViewById(R.id.equipment_collect_detail_route_tv);
        zonearea = (TextView) findViewById(R.id.equipment_collect_detail_zonearea_tv);
        sim = (TextView) findViewById(R.id.equipment_collect_detail_sim_tv);
        vendor = (TextView) findViewById(R.id.equipment_collect_detail_vender);
        model = (TextView) findViewById(R.id.equipment_collect_detail_model_tv);
        phase = (TextView) findViewById(R.id.equipment_collect_detail_phases_tv);
        capacity = (TextView) findViewById(R.id.equipment_collect_detail_capacity_tv);
    }

    private void initData(){
        city.setText(dataBean.getCityId()+"");
        country.setText(dataBean.getCountyId() + "");
        powersupply.setText(dataBean.getPowerSupplyId() + "");
        type.setText(dataBean.getDeviceTypeId() + "");
        route.setText(dataBean.getCircuitId() + "");
        zonearea.setText(dataBean.getCourts()); //未获取到数据？
        //true-正常，false-异常
        if(dataBean.getSIMCardOnline()){
            sim.setText(getResources().getString(R.string.cet_count_nor));
        }else{
            sim.setText(getResources().getString(R.string.cet_count_abnor));
        }
        if(dataBean.getManufactureNormal())
        {
            vendor.setText(getResources().getString(R.string.cet_count_nor));
        }else{
            vendor.setText(getResources().getString(R.string.cet_count_abnor));
        }
        model.setText(dataBean.getModel());
        phase.setText(dataBean.getPhaseTypeId() + "");
        capacity.setText(dataBean.getCapacity() + "");
    }
}
