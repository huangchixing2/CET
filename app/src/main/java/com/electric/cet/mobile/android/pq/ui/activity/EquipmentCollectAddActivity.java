package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.Bean.OptionBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.db.SQLhelper_Device;
import com.electric.cet.mobile.android.pq.ui.view.wheel.WheelView;
import com.electric.cet.mobile.android.pq.ui.view.wheel.adapter.CityAdapter2;
import com.electric.cet.mobile.android.pq.utils.Constans;
import com.electric.cet.mobile.android.pq.utils.NetWorkUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.electric.cet.mobile.android.pq.utils.OkHttpUtils.response;

public class EquipmentCollectAddActivity extends Activity implements View.OnClickListener {
    private LinearLayout back_iv;
    private TextView save_tv;

    private OptionBean optionBean;
    private DataBean dataBean;

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

    /**
     * 类型Pop
     **/
    private PopupWindow typePop;
    private WheelView typeWV;
    private CityAdapter2 typeAdapter;
    private int typePos = -1;

    private TextView type;
    private RelativeLayout type_rl;
    private List<String> type_list = new ArrayList<>();

    /**
     * sim卡 Pop
     **/
    private PopupWindow simPop;
    private WheelView simWV;
    private CityAdapter2 simAdapter;
    private int simPos = -1;

    private TextView sim;
    private RelativeLayout sim_rl;
    private List<String> sim_list = new ArrayList<>();

    private EditText routeEt;  //线路
    private EditText zoneareaEt; //台区
    private EditText vendorEt; //厂家
    private EditText modelEt; //型号
    private EditText phraseEt; //相数
    private EditText capacityEt; //容量
    private EditText voltageRegulatingRangeEt; //调压范围
    private EditText spanEt; //档距
    private EditText reactivePowerCapacityEt; //无功容量
    private EditText reactiveGroupsEt; //无功组数

    private int requestCode = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    Intent intent = new Intent();
                    setResult(requestCode, intent);
                    Toast.makeText(EquipmentCollectAddActivity.this, "设备增加成功", Toast.LENGTH_SHORT).show();
                    //插入数据到数据库
                    SQLhelper_Device.Instance(EquipmentCollectAddActivity.this).addDeviceInfo(dataBean);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_collect_add);
        initView();
        initData();
        requestCode = getIntent().getIntExtra("requestCode", -1);
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

        //类型
        type = (TextView) findViewById(R.id.equipment_collect_add_type_tv);
        type_rl = (RelativeLayout) findViewById(R.id.equipment_collect_add_type_rl);
        type_rl.setOnClickListener(this);
        //sim卡
        sim = (TextView) findViewById(R.id.equipment_collect_add_sim_tv);
        sim_rl = (RelativeLayout) findViewById(R.id.equipment_collect_add_sim_rl);
        sim_rl.setOnClickListener(this);

        routeEt = (EditText) findViewById(R.id.equipment_collect_add_route_et);
        zoneareaEt = (EditText) findViewById(R.id.equipment_collect_add_zonearea_et);
        vendorEt = (EditText) findViewById(R.id.equipment_collect_add_vender_et);
        modelEt = (EditText) findViewById(R.id.equipment_collect_add_model_et);

        phraseEt = (EditText) findViewById(R.id.equipment_collect_add_phases_et);
        capacityEt = (EditText) findViewById(R.id.equipment_collect_add_capacity_et);
        voltageRegulatingRangeEt = (EditText) findViewById(R.id.equipment_collect_add_voltage_regulating_range_et);
        spanEt = (EditText) findViewById(R.id.equipment_collect_add_span_et);
        reactivePowerCapacityEt = (EditText) findViewById(R.id.equipment_collect_add_reactive_power_capacity_et);
        reactiveGroupsEt = (EditText) findViewById(R.id.equipment_collect_add_reactive_groups_et);

    }

    private void initData() {
        dataBean = new DataBean();
        //获取节点tree数据
        SharedPreferences sp = getSharedPreferences("treeData", MODE_PRIVATE);
        String str_tree = sp.getString("str_Tree", "");
        Gson gson = new Gson();
        //将json字符串转为dataBean对象
        optionBean = gson.fromJson(str_tree, OptionBean.class);
    }


    //点击保存按钮提交数据到服务器
    private void doPost() {
        //发起post请求给服务器
        OkHttpClient client = new OkHttpClient();

        //固定假信息
        dataBean.setDeviceName("设备" + new Random().nextInt(999));
        dataBean.setInstalled(false);
        dataBean.setOnline(false);
        dataBean.setUsable(false);
        dataBean.setAbnormal(false);
        dataBean.setPowerFailure(false);
        dataBean.setLongitude(new Random().nextDouble());
        dataBean.setLatitude(new Random().nextDouble());
        dataBean.setAdjustTime(7);
        dataBean.setVoltageRegulateNormal(false);
        dataBean.setReactiveCompensationNormal(false);
//        dataBean.setManufacture("华为");
//        dataBean.setModel("型号1");
        dataBean.setPhaseTypeId(new Random().nextInt(999));
        dataBean.setCapacity(new Random().nextInt(999));
        dataBean.setCircuitNormal(false);
        dataBean.setInstallAddress("北京");
        dataBean.setState(false);
        dataBean.setManufactureNormal(false);
        dataBean.setLocation("北京海淀区西四环");
        dataBean.setSIMCardOnline(false);

        final String routeData = routeEt.getText().toString().trim(); //线路输入
        final String zoneData = zoneareaEt.getText().toString().trim();//台区输入
        final String vendorData = vendorEt.getText().toString().trim(); //厂家输入
        final String modelData = modelEt.getText().toString().trim();//型号输入
        RequestBody formBody = new FormBody.Builder().
                add("CityId", String.valueOf(dataBean.getCityId())).
                add("CountryId", String.valueOf(dataBean.getCountyId())).
                add("PowerSupplyId", String.valueOf(dataBean.getPowerSupplyId())).
                add("DeviceTypeId", String.valueOf(dataBean.getDeviceTypeId())).
                add("CircuitId", TextUtils.isEmpty(routeData) ? "11" : routeData).
                add("Courts", TextUtils.isEmpty(zoneData) ? "11" : zoneData).
                add("IsSIMCardOnline", String.valueOf(dataBean.getSIMCardOnline())).
                add("Model", TextUtils.isEmpty(modelData) ? "11" : modelData).
                add("Manufacture", TextUtils.isEmpty(vendorData) ? "11" : vendorData).

                add("IsInstalled ", String.valueOf(dataBean.getInstalled())).
                add("IsAbnormal", String.valueOf(dataBean.getAbnormal())).
                add("IsPowerFailure", String.valueOf(dataBean.getPowerFailure())).
                add("Longitude", String.valueOf(dataBean.getLatitude())).
                add("Latitude", String.valueOf(dataBean.getLongitude())).
                add("AdjustTime", String.valueOf(dataBean.getAdjustTime())).
                add("IsVoltageRegulateNormal", String.valueOf(dataBean.getVoltageRegulateNormal())).
                add("IsReactiveCompensationNormal", String.valueOf(dataBean.getReactiveCompensationNormal())).


                add("PhaseTypeId", String.valueOf(dataBean.getPhaseTypeId())).
                add("Capacity", String.valueOf(dataBean.getCapacity())).
                add("IsCircuitNormal", String.valueOf(dataBean.getCircuitNormal())).
                add("InstallAddress", dataBean.getInstallAddress()).
                add("State", String.valueOf(dataBean.getState())).
                add("IsManufactureNormal", String.valueOf(dataBean.getManufactureNormal())).
                add("Location", dataBean.getLocation()).
                add("DeviceName", dataBean.getDeviceName()).
                build();
        final Request request = new Request.Builder().url(Constans.URL_ADD).post(formBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
                if (!response.isSuccessful()) {
                    Log.i("请求情况：", "请求失败");
                    Looper.prepare();
                    //提示用户无网络
                    Toast.makeText(EquipmentCollectAddActivity.this, "没有网络", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i("响应状态", "响应成功");
                    int ResponseCode = response.code();
                    //无法获取token
                    //响应成功,判断状态码
                    if (ResponseCode == 200) {
                        Log.i("EquipmentCollect", "提交数据成功");
                        //返回选中的数据
                        handler.sendEmptyMessage(100);
                        sendBroadCast();
                    }
                }
            }
        });
    }

    //添加成功，发送广播
    private void sendBroadCast() {
        Intent intent = new Intent();
        intent.setAction(Constans.ACTION_EQUIPMENT_ADD_SUCCESS);
        sendBroadcast(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.equipment_collect_add_back_ll:
                finish();
                break;
            case R.id.equipment_collect_add_save:
                if (!NetWorkUtil.isNetworkAvailable(EquipmentCollectAddActivity.this)) {
                    Toast.makeText(EquipmentCollectAddActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
                    return;
                }
                //上传服务器
                if (!TextUtils.isEmpty(city.getText()) && !TextUtils.isEmpty(country.getText()) && !TextUtils.isEmpty(power.getText()) && !TextUtils.isEmpty(type.getText())) {

                    doPost();
                } else {
                    Toast.makeText(EquipmentCollectAddActivity.this, "前四项为必填项", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.equipment_collect_add_city_rl:
                showCityPop(getCollectCities());
                break;
            case R.id.equipment_collect_add_country_rl:
                if (cityPos != -1) {
                    showCountryPop(getCollectCountries());
                }
                break;
            case R.id.equipment_collect_add_powersupply_rl:
                if (countryPos != -1) {
                    showPowerPop(getCollectPowers());
                }
                break;
            case R.id.equipment_collect_add_type_rl:
//                if (powerPos != -1) {
                showTypePop(getCollectTypes());
//                }
                break;
//            case R.id.equipment_collect_add_route_rl:
//                break;
//            case R.id.equipment_collect_add_zonearea_rl:
//                break;
            case R.id.equipment_collect_add_sim_rl:
                showSimPop(getCollectSims());
                break;
            case R.id.equipment_collect_add_phases_rl:
//                showPhasesPop(getCollectTypes());
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

    private List<String> getCollectTypes() {
        type_list.clear();
        for (int i = 0; i < optionBean.getData().getDeviceType().size(); i++) {
            type_list.add(optionBean.getData().getDeviceType().get(i).getName());
        }
        return type_list;
    }

    private List<String> getCollectSims() {
        sim_list.clear();
        for (int i = 0; i < optionBean.getData().getSIMCardState().size(); i++) {
            sim_list.add(optionBean.getData().getSIMCardState().get(i).getName());
        }
        return sim_list;
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
                //判断选择的内容和外面显示的内容是否相同
                if (cities_list.get(cityPos).equalsIgnoreCase(city.getText().toString())) {
                    cityPop.dismiss();
                } else {
                    city.setText(cities_list.get(cityPos));
                    dataBean.setCityId(cityPos + 1);
                    //找到所有的控件，并清空内容
                    country.setText("");
                    power.setText("");
                    cityPop.dismiss();
                }
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

                if (countries_list.get(cityPos).equalsIgnoreCase(country.getText().toString())) {
                    cityPop.dismiss();
                } else {
                    country.setText(countries_list.get(countryPos));
                    dataBean.setCountyId(countryPos + 1);
                    //清空
                    power.setText("");
                    countryPop.dismiss();
                }
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
                dataBean.setPowerSupplyId(powerPos + 1); //设定选择的值
                powerPop.dismiss();
            }
        });

    }

    private void showTypePop(List<String> list) {
        initTypePop(list);
        typePop.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);
    }

    private void showSimPop(List<String> list) {
        initSimPop(list);
        simPop.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);
    }

    private void showPhasesPop(List<String> list) {
        initPhasesPop(list);
//        typePop.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 显示类型pop
     *
     * @param list
     */
    private void initTypePop(List<String> list) {
        View cityView = LayoutInflater.from(this).inflate(R.layout.city_pop_layout, null);
        TextView cancleBtn = (TextView) cityView.findViewById(R.id.add_bank_city_cancel);
        TextView confirmBtn = (TextView) cityView.findViewById(R.id.add_bank_city_complete);
        typeWV = (WheelView) cityView.findViewById(R.id.add_bank_city_wheel);
        typeWV.setVisibleItems(7);
        typePop = new PopupWindow(cityView, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        typePop.setOutsideTouchable(true);
        typePop.setBackgroundDrawable(new BitmapDrawable());
//        cityPop.setAnimationStyle(R.style.PopAnimation);
        typeAdapter = new CityAdapter2(this, list);
        typeWV.setViewAdapter(typeAdapter);
        typeWV.setCurrentItem(0);
        cancleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                typePop.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                typePos = typeWV.getCurrentItem();
                type.setText(type_list.get(typePos));
                dataBean.setDeviceTypeId(typePos + 1);
                typePop.dismiss();
            }
        });
    }

    /**
     * 显示Sim卡pop
     *
     * @param list
     */
    private void initSimPop(List<String> list) {
        View cityView = LayoutInflater.from(this).inflate(R.layout.city_pop_layout, null);
        TextView cancleBtn = (TextView) cityView.findViewById(R.id.add_bank_city_cancel);
        TextView confirmBtn = (TextView) cityView.findViewById(R.id.add_bank_city_complete);
        simWV = (WheelView) cityView.findViewById(R.id.add_bank_city_wheel);
        simWV.setVisibleItems(7);
        simPop = new PopupWindow(cityView, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        simPop.setOutsideTouchable(true);
        simPop.setBackgroundDrawable(new BitmapDrawable());
//        cityPop.setAnimationStyle(R.style.PopAnimation);
        simAdapter = new CityAdapter2(this, list);
        simWV.setViewAdapter(simAdapter);
        simWV.setCurrentItem(0);
        cancleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                simPop.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                simPos = simWV.getCurrentItem();
                sim.setText(sim_list.get(simPos));
                dataBean.setSIMCardOnline(simPos == 1);
                simPop.dismiss();
            }
        });
    }

    /**
     * 显示相数pop
     *
     * @param list
     */
    private void initPhasesPop(List<String> list) {
        View cityView = LayoutInflater.from(this).inflate(R.layout.city_pop_layout, null);
        TextView cancleBtn = (TextView) cityView.findViewById(R.id.add_bank_city_cancel);
        TextView confirmBtn = (TextView) cityView.findViewById(R.id.add_bank_city_complete);
        typeWV = (WheelView) cityView.findViewById(R.id.add_bank_city_wheel);
        typeWV.setVisibleItems(7);
        typePop = new PopupWindow(cityView, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        typePop.setOutsideTouchable(true);
        typePop.setBackgroundDrawable(new BitmapDrawable());
//        cityPop.setAnimationStyle(R.style.PopAnimation);
        typeAdapter = new CityAdapter2(this, list);
        typeWV.setViewAdapter(typeAdapter);
        typeWV.setCurrentItem(0);
        cancleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                typePop.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                typePos = typeWV.getCurrentItem();
                type.setText(type_list.get(typePos));
                dataBean.setDeviceTypeId(typePos + 1);
                typePop.dismiss();
            }
        });
    }

}
