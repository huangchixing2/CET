package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.Bean.OptionBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.ui.view.wheel.WheelView;
import com.electric.cet.mobile.android.pq.ui.view.wheel.adapter.CityAdapter2;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class EquipmentCollectAddActivity extends Activity implements View.OnClickListener {
    private LinearLayout back_iv;
    private TextView save_tv;

    private OptionBean optionBean;

    /**
     * 市Pop
     **/
    private PopupWindow cityPop;
    private WheelView cityWV;
    private CityAdapter2 cityAdapter;
    private int cityPos = -1;
    private LinearLayout parentLayout;

    private TextView city;
    private RelativeLayout city_rl;
    private List<String> cities_list = new ArrayList<>();

    /**
     * 区县Pop
     **/
    private PopupWindow countryPop;
    private WheelView countryWV;
    private CityAdapter2 countryAdapter;
    private int countryPos = -1;

    private TextView country;
    private RelativeLayout country_rl;
    private List<String> countries_list = new ArrayList<>();

    /**
     * 供电所Pop
     **/
    private PopupWindow powerPop;
    private WheelView powerWV;
    private CityAdapter2 powerAdapter;
    private int powerPos = -1;

    private TextView power;
    private RelativeLayout power_rl;
    private List<String> power_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_collect_add);
        initView();
        initData();
    }

    private void initView() {
        back_iv = (LinearLayout) findViewById(R.id.equipment_collect_add_back_ll);
        save_tv = (TextView) findViewById(R.id.equipment_collect_add_save);
        back_iv.setOnClickListener(this);
        save_tv.setOnClickListener(this);
        parentLayout = (LinearLayout) findViewById(R.id.equipment_collect_add_parent_ll);
        city = (TextView) findViewById(R.id.equipment_collect_add_city_tv);
        city_rl = (RelativeLayout) findViewById(R.id.equipment_collect_add_city_rl);
        city_rl.setOnClickListener(this);
        country = (TextView) findViewById(R.id.equipment_collect_add_country_tv);
        country_rl = (RelativeLayout) findViewById(R.id.equipment_collect_add_country_rl);
        country_rl.setOnClickListener(this);
        power = (TextView) findViewById(R.id.equipment_collect_add_powersupply_tv);
        power_rl = (RelativeLayout) findViewById(R.id.equipment_collect_add_powersupply_rl);
        power_rl.setOnClickListener(this);
    }

    private void initData() {
        //获取节点tree数据
        SharedPreferences sp = getSharedPreferences("treeData", MODE_PRIVATE);
        String str_tree = sp.getString("str_Tree", "");
        Gson gson = new Gson();
        //将json字符串转为dataBean对象
        optionBean = gson.fromJson(str_tree, OptionBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.equipment_collect_add_back_ll:
                finish();
                break;
            case R.id.equipment_collect_add_save:
                finish();
                break;
            case R.id.equipment_collect_add_city_rl:
                showCityPop(getCollectCities());
                break;
            case R.id.equipment_collect_add_country_rl:
                if(cityPos != -1){
                    showCountryPop(getCollectCountries());
                }
                break;
            case R.id.equipment_collect_add_powersupply_rl:
                if(countryPos != -1){
                    showPowerPop(getCollectPowers());
                }
                break;
            default:
                break;
        }
    }

    private List<String> getCollectCities() {
        cities_list.clear();
        for (int i = 0; i < optionBean.getData().getCities().size(); i++) {
            cities_list.add(optionBean.getData().getCities().get(i).getName());
        }
        return cities_list;
    }

    private List<String> getCollectCountries() {
        countries_list.clear();
        for (int i = 0; i < optionBean.getData().getCities().get(cityPos).getCounties().size(); i++) {
            countries_list.add(optionBean.getData().getCities().get(cityPos).getCounties().get(i).getName());
        }
        return countries_list;
    }

    private List<String> getCollectPowers() {
        power_list.clear();
        for (int i = 0; i < optionBean.getData().getCities().get(cityPos).getCounties().get(countryPos).getPowerSupply().size(); i++) {
            power_list.add(optionBean.getData().getCities().get(cityPos).getCounties().get(countryPos).getPowerSupply().get(i).getName());
        }
        return power_list;
    }

    /**
     * 显示市Pop
     */
    private void showCityPop(List<String> list) {
        initCityPop(list);
        cityPop.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);

    }

    private void initCityPop(List<String> list) {
        View cityView = LayoutInflater.from(this).inflate(R.layout.city_pop_layout, null);
        TextView cancleBtn = (TextView) cityView.findViewById(R.id.add_bank_city_cancel);
        TextView confirmBtn = (TextView) cityView.findViewById(R.id.add_bank_city_complete);
        cityWV = (WheelView) cityView.findViewById(R.id.add_bank_city_wheel);
        cityWV.setVisibleItems(7);
        cityPop = new PopupWindow(cityView, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        cityPop.setOutsideTouchable(true);
        cityPop.setBackgroundDrawable(new BitmapDrawable());
//        cityPop.setAnimationStyle(R.style.PopAnimation);
        cityAdapter = new CityAdapter2(this, list);
        cityWV.setViewAdapter(cityAdapter);
        cityWV.setCurrentItem(0);
        cancleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cityPop.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cityPos = cityWV.getCurrentItem();
                city.setText(cities_list.get(cityPos));
                cityPop.dismiss();
            }
        });

    }

    /**
     * 显示区县Pop
     */
    private void showCountryPop(List<String> list) {
        initCountryPop(list);
        countryPop.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);

    }

    private void initCountryPop(List<String> list) {
        View cityView = LayoutInflater.from(this).inflate(R.layout.city_pop_layout, null);
        TextView cancleBtn = (TextView) cityView.findViewById(R.id.add_bank_city_cancel);
        TextView confirmBtn = (TextView) cityView.findViewById(R.id.add_bank_city_complete);
        countryWV = (WheelView) cityView.findViewById(R.id.add_bank_city_wheel);
        countryWV.setVisibleItems(7);
        countryPop = new PopupWindow(cityView, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        countryPop.setOutsideTouchable(true);
        countryPop.setBackgroundDrawable(new BitmapDrawable());
//        cityPop.setAnimationStyle(R.style.PopAnimation);
        countryAdapter = new CityAdapter2(this, list);
        countryWV.setViewAdapter(countryAdapter);
        countryWV.setCurrentItem(0);
        cancleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                countryPop.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                countryPos = countryWV.getCurrentItem();
                country.setText(countries_list.get(countryPos));
                countryPop.dismiss();
            }
        });

    }

    /**
     * 显示供电所Pop
     */
    private void showPowerPop(List<String> list) {
        initPowerPop(list);
        powerPop.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);

    }

    private void initPowerPop(List<String> list) {
        View cityView = LayoutInflater.from(this).inflate(R.layout.city_pop_layout, null);
        TextView cancleBtn = (TextView) cityView.findViewById(R.id.add_bank_city_cancel);
        TextView confirmBtn = (TextView) cityView.findViewById(R.id.add_bank_city_complete);
        powerWV = (WheelView) cityView.findViewById(R.id.add_bank_city_wheel);
        powerWV.setVisibleItems(7);
        powerPop = new PopupWindow(cityView, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        powerPop.setOutsideTouchable(true);
        powerPop.setBackgroundDrawable(new BitmapDrawable());
//        cityPop.setAnimationStyle(R.style.PopAnimation);
        powerAdapter = new CityAdapter2(this, list);
        powerWV.setViewAdapter(powerAdapter);
        powerWV.setCurrentItem(0);
        cancleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                powerPop.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                powerPos = powerWV.getCurrentItem();
                power.setText(power_list.get(powerPos));
                powerPop.dismiss();
            }
        });

    }
}
