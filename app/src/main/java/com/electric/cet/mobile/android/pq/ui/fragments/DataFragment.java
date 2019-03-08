package com.electric.cet.mobile.android.pq.ui.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.Bean.DeviceBean;
import com.electric.cet.mobile.android.pq.Bean.RealTimeBean;
import com.electric.cet.mobile.android.pq.Bean.TrendBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.db.SQLhelper_Device;
import com.electric.cet.mobile.android.pq.model.DataTrend;
import com.electric.cet.mobile.android.pq.ui.adapter.BasePagerAdapter;
import com.electric.cet.mobile.android.pq.ui.view.GraphicalUtils;
import com.electric.cet.mobile.android.pq.ui.view.GraphicalView;
import com.electric.cet.mobile.android.pq.utils.Constans;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//数据
public class DataFragment extends BaseFragment implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private RadioGroup dataRadioGroup;
    private RadioButton countRB;
    private RadioButton realtimeRB;
    private RadioButton trendRB;
    private LinearLayout tabLineLayout;
    private ImageView tablineImg;

    private RadioGroup trendRadioGroup;
    private RadioButton voltageRB;
    private RadioButton currentRB;
    private RadioButton powerRB;
    private TextView bdate_tv;
    private TextView adate_tv;
    private boolean isbdate;

    private List<View> views;
    private ViewPager viewPager;
    private BasePagerAdapter pagerAdapter;

    private TextView avo_tv;

    private TextView data_address;
    private TextView voltageRegulateTime;
    private TextView voltageRegulate;
    private TextView reactiveCompensation;
    private TextView manufacture;
    private TextView model;
    private TextView capacity;
    private TextView phrase;
    private TextView circuit;
    private TextView location;

    private TextView cet_realtime_input_avoltage;
    private TextView cet_realtime_out_avoltage;
    private TextView cet_realtime_input_bvoltage;
    private TextView cet_realtime_out_bvoltage;
    private TextView cet_realtime_input_cvoltage;
    private TextView cet_realtime_out_cvoltage;
    private TextView cet_realtime_input_acurrent;
    private TextView cet_realtime_out_acurrent;
    private TextView cet_realtime_input_bcurrent;
    private TextView cet_realtime_out_bcurrent;
    private TextView cet_realtime_input_ccurrent;
    private TextView cet_realtime_out_ccurrent;
    private TextView cet_realtime_input_apower;
    private TextView cet_realtime_out_apower;
    private TextView cet_realtime_input_bpower;
    private TextView cet_realtime_out_bpower;
    private TextView cet_realtime_input_cpower;
    private TextView cet_realtime_out_cpower;
    private TextView cet_realtime_voltage_adjust;
    private TextView cet_realtime_nopower;

    private DataBean dataBean;
    private DeviceBean deviceBean;


//    String url_before = "http://192.168.2.102/LowLineSys/device/";
//    String url_after = "/data/realtime";
//    String url_afterTrend = "/data/trend/";

    private int baseDistance;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //实时数据现在在界面
                    RealTimeBean realTimeBean = (RealTimeBean)msg.obj;
                    Log.d("huangchixing",realTimeBean.getData().getAVoltageInput()+ "");
                    Log.d("huangchixing", realTimeBean.getData().getAVoltageOutput()+ "");
                    cet_realtime_input_avoltage.setText("A相电压：" + realTimeBean.getData().getAVoltageInput() + "");
                    cet_realtime_out_avoltage.setText("A相电压：" + realTimeBean.getData().getAVoltageOutput() + "");
                    cet_realtime_input_bvoltage.setText("B相电压：" + realTimeBean.getData().getBVoltageInput() + "");
                    cet_realtime_out_bvoltage.setText("B相电压：" + realTimeBean.getData().getBVoltageOutput() + "");
                    cet_realtime_input_cvoltage.setText("C相电压：" + realTimeBean.getData().getCVoltageInput() + "");
                    cet_realtime_out_cvoltage.setText("C相电压：" + realTimeBean.getData().getCVoltageOutput() + "");
                    cet_realtime_input_acurrent.setText("A相电流：" + realTimeBean.getData().getACurrentInput() + "");
                    cet_realtime_out_acurrent.setText("A相电流：" + realTimeBean.getData().getACurrentOutput() + "");
                    cet_realtime_input_bcurrent.setText("B相电流：" + realTimeBean.getData().getBCurrentInput() + "");
                    cet_realtime_out_bcurrent.setText("B相电流：" + realTimeBean.getData().getBCurrentOutput() + "");
                    cet_realtime_input_ccurrent.setText("C相电流：" + realTimeBean.getData().getCCurrentInput() + "");
                    cet_realtime_out_ccurrent.setText("C相电压：" + realTimeBean.getData().getCCurrentOutput() + "");
                    cet_realtime_input_apower.setText("A相功率因数：" + realTimeBean.getData().getAPowerFactorInput() + "");
                    cet_realtime_out_apower.setText("A相功率因数：" + realTimeBean.getData().getAPowerFactorOutput() + "");
                    cet_realtime_input_bpower.setText("B相功率因数：" + realTimeBean.getData().getBBowerFactorInput() + "");
                    cet_realtime_out_bpower.setText("B相功率因数：" + realTimeBean.getData().getBBowerFactorOutput() + "");
                    cet_realtime_input_cpower.setText("C相功率因数：" + realTimeBean.getData().getCPowerFactorInput() + "");
                    cet_realtime_out_cpower.setText("C相功率因数：" + realTimeBean.getData().getCPowerFactorOutput() + "");
                    cet_realtime_voltage_adjust.setText("调压档位：" + realTimeBean.getData().getVoltageRegulate() + "");
                    cet_realtime_nopower.setText("无功投入：" + realTimeBean.getData().getReactivePowerInput() + "");


                    break;
                case 1002:
                    countRB.setChecked(true);
                    Log.i("devicesId",(int)getActivity().getSharedPreferences("data",0).getLong("deviceId",-1l)+"datahandler");
                    //还原出统计数据存储的数据并显示在ui
                    refreshCountData( (int)getActivity().getSharedPreferences("data",0).getLong("deviceId",-1l));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        initView(view);
        initData();

        return view;
    }

    private void initView(View view) {
        views = new ArrayList<View>();
        View countView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_data_count_layout, null);
//        count_lv = (ListView) countView.findViewById(R.id.cet_data_count_lv);
//        DataCountAdapter dcAdapter = new DataCountAdapter(getActivity(),getData());
//        count_lv.setAdapter(dcAdapter);
        initCountView(countView);
        View realtimeView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_data_realtime_layout, null);
        initRealtimeView(realtimeView);
        View trendView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_data_trend_layout, null);
        initTrendView(trendView);
        views.add(countView);
        views.add(realtimeView);
        views.add(trendView);
        dataRadioGroup = (RadioGroup) view.findViewById(R.id.cet_data_list_rg);
        countRB = (RadioButton) view.findViewById(R.id.cet_data_count_tab);
        realtimeRB = (RadioButton) view.findViewById(R.id.cet_data_realtime_tab);
        trendRB = (RadioButton) view.findViewById(R.id.cet_data_trend_tab);
        dataRadioGroup.setOnCheckedChangeListener(this);
        tabLineLayout = (LinearLayout) view.findViewById(R.id.cet_data_tab_line_layout);
        tablineImg = (ImageView) view.findViewById(R.id.cet_data_list_tab_line_img);
        viewPager = (ViewPager) view.findViewById(R.id.cet_data_list_viewpager);



        DisplayMetrics metrics = new DisplayMetrics();
        metrics = getResources().getDisplayMetrics();
        baseDistance = (int) Math.round(metrics.widthPixels / 3.0);
        tablineImg.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams tablineParams = tablineImg.getLayoutParams();
        tablineParams.width = baseDistance;
        tabLineLayout.setPadding(10, 0, 10, 0);
        pagerAdapter = new BasePagerAdapter(views);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    private void initData() {
//
//        initRealtimeData();
//        initTrendData();
        refreshCountData(-1);


    }

private void refreshCountData(int deviceId){
    //查询数据库，并显示统计数据
    dataBean = SQLhelper_Device.Instance(getActivity()).queryDeviceInfo(deviceId); //deviceId如何传参？
    Log.d("statics",  dataBean.getInstallAddress() );
    data_address.setText("台区: " + dataBean.getInstallAddress()); // int转换为string，否则报错

    voltageRegulateTime.setText("调压次数: "+ dataBean.getAdjustTime() + "");
    //做一个判断，true显示正常，false显示异常
    if(dataBean.getVoltageRegulateNormal()){
        voltageRegulate.setText("调压: "+ getResources().getString(R.string.cet_count_nor));
    }else{
        voltageRegulate.setText("调压: "+ getResources().getString(R.string.cet_count_abnor));
    }
//    voltageRegulate.setText("调压: "+ dataBean.getVoltageRegulateNormal()); //怎么做判断正常和异常
    if(dataBean.getReactiveCompensationNormal()){
        reactiveCompensation.setText("无功补偿: " + getResources().getString(R.string.cet_count_nor));
    }else{
        reactiveCompensation.setText("无功补偿: " + getResources().getString(R.string.cet_count_abnor));
    }
//    reactiveCompensation.setText("无功补偿: " + dataBean.getReactiveCompensationNormal() + "");
    manufacture.setText("厂家: "+dataBean.getManufacture() + "");
    model.setText("型号: "+dataBean.getModel() + "");
    capacity.setText("容量: "+dataBean.getCapacity() + " KG");
    phrase.setText("相数: "+dataBean.getPhaseTypeId() + "");
    circuit.setText("线路: "+dataBean.getCircuitId() + "");
    location.setText("位置: "+dataBean.getLocation());
}



////请求统计数据
//    public void initStatisticsData(){
//
//      dataBean = SQLhelper_Device.Instance(getActivity()).queryUserInfo();
//
//
//        Log.d("statistics", dataBean.getInstallAddress());
//        System.out.println("statistics"+dataBean.getInstallAddress());
//    }


    //Get方式请求趋势数据
    public void initTrendData() {
        OkHttpClient client = new OkHttpClient();
        String DeviceId = null;

//        RequestBody formBody = new FormBody.Builder().add("deviceId","3" ) //deviceId如何传入？
//                .add("startTime", "2019-02-22")  //参数如何填入？
////        bdate_tv.getText().toString().trim().replace("/","-")
//                .add("endTime", "2019-02-28").build();
        String url_trend = Constans.URL_BEFORE + dataBean.getDeviceId() + Constans.URL_AFTERTREND + bdate_tv.getText().toString().trim().replace("/", "-") + "/" + adate_tv.getText().toString().trim().replace("/", "-");
        Request request = new Request.Builder().url(url_trend).get().build();
//        doGET(url_trend, request);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 提示错误信息
                Log.d("DataFrament", "趋势数据请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                String jsonData = str;
                Log.d("DataFrament", "趋势数据请求打印" + jsonData);
                Log.d("DataFrament", "趋势数据打印成功");
                //使用gson解析json数据
                //new一个Gson对象
                Gson gson = new Gson();
                //将json字符串转为dataBean对象
                TrendBean trendBean = gson.fromJson(jsonData, TrendBean.class);
                Log.d("huangchixing22",trendBean.getData().getDeviceId() + "");
                Log.d("huangchixing22",trendBean.getData().getVoltData().get(1).getPhaseType() + "");
                Log.d("huangchixing22", trendBean.getData().getCurrData().get(0).getDataList().get(0).getRecordTime());


            }
        });
    }

    private SQLhelper_Device dbHelper;

    //请求实时数据
    public void initRealtimeData() {
        OkHttpClient client = new OkHttpClient();
        String deviceid = null;


//        RequestBody formBody = new FormBody.Builder().add("deviceId", "98").build(); //参数如何填入？
        Log.i("deviceid", dataBean.getDeviceId() + "");
        String url_realTime = Constans.URL_BEFORE + dataBean.getDeviceId() + Constans.URL_AFTER;
        Request request = new Request.Builder().url(url_realTime).get().build();
//        doGET(url_realTime, request);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 提示错误信息
                Log.d("DataFrament", "实时数据请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    final String str = response.body().string();
                    String jsonData = str;
                    Log.d("DataFrament", "实时数据请求打印" + jsonData);
                    Log.d("DataFrament", "实时数据打印成功");
               //使用gson解析json数据
                    Gson gson = new Gson();
                    //将json字符串转为dataBean对象
                    RealTimeBean realTimeBean = gson.fromJson(jsonData, RealTimeBean.class);
//                Log.d("DataFragment", "-------开始解析实时数据-------------");
//                Log.d("DataFragment", "DEVICE ID IS " + realTimeBean.getRealTimeData().get(0).getDeviceId());
//                Log.d("DataFragment", "DEVICE NAME IS" + realTimeBean.getRealTimeData().get(0).getDeviceId());
//                    Log.d("DataFragment", "-------实时数据解析成功-------------");
//                    Log.d("DataFragment",realTimeBean.getData().getAVoltageInput() + "");
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = realTimeBean;
                    message.sendToTarget();
                }catch (IOException e1){
                    e1.printStackTrace();
                }



//                cet_realtime_input_avoltage.setText("A相电压：" + realTimeBean.getData().getAVoltageInput() + "");
//                cet_realtime_out_avoltage.setText("A相电压：" + realTimeBean.getData().getAVoltageOutput() + "");
//                cet_realtime_input_bvoltage.setText("B相电压：" + realTimeBean.getData().getBVoltageInput() + "");
//                cet_realtime_out_bvoltage.setText("B相电压：" + realTimeBean.getData().getBVoltageOutput() + "");
//                cet_realtime_input_cvoltage.setText("C相电压：" + realTimeBean.getData().getCVoltageInput() + "");
//                cet_realtime_out_cvoltage.setText("C相电压：" + realTimeBean.getData().getCVoltageOutput() + "");
//                cet_realtime_input_acurrent.setText("A相电流：" + realTimeBean.getData().getACurrentInput() + "");
//                cet_realtime_out_acurrent.setText("A相电流：" + realTimeBean.getData().getACurrentOutput() + "");
//                cet_realtime_input_bcurrent.setText("B相电流：" + realTimeBean.getData().getBCurrentInput() + "");
//                cet_realtime_out_bcurrent.setText("B相电流：" + realTimeBean.getData().getBCurrentOutput() + "");
//                cet_realtime_input_ccurrent.setText("C相电流：" + realTimeBean.getData().getCCurrentInput() + "");
//                cet_realtime_out_ccurrent.setText("C相电压：" + realTimeBean.getData().getCCurrentOutput() + "");
//                cet_realtime_input_apower.setText("A相功率因数：" + realTimeBean.getData().getAPowerFactorInput() + "");
//                cet_realtime_out_apower.setText("A相功率因数：" + realTimeBean.getData().getAPowerFactorOutput() + "");
//                cet_realtime_input_bpower.setText("B相功率因数：" + realTimeBean.getData().getBBowerFactorInput() + "");
//                cet_realtime_out_bpower.setText("B相功率因数：" + realTimeBean.getData().getBBowerFactorOutput() + "");
//                cet_realtime_input_cpower.setText("C相功率因数：" + realTimeBean.getData().getCPowerFactorInput() + "");
//                cet_realtime_out_cpower.setText("C相功率因数：" + realTimeBean.getData().getCPowerFactorOutput() + "");
//                cet_realtime_voltage_adjust.setText("调压档位：" + realTimeBean.getData().getVoltageRegulate() + "");
//                cet_realtime_nopower.setText("无功投入：" + realTimeBean.getData().getReactivePowerInput() + "");
            }
        });
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        int location = Math.round(v * baseDistance) + i * baseDistance;
        tabLineLayout.scrollTo(-location, 0);
    }

    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case 0:
                countRB.setChecked(true);
//                initStatisticsData();
                break;
            case 1:
                realtimeRB.setChecked(true);
                initRealtimeData();
                break;
            case 2:
                trendRB.setChecked(true);
                initTrendData();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.cet_data_count_tab:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.cet_data_realtime_tab:
                viewPager.setCurrentItem(1, true);
                break;
            case R.id.cet_data_trend_tab:
                viewPager.setCurrentItem(2, true);
                break;
            case R.id.cet_data_trend_voltage_rb:
                voltageRB.setChecked(true);
                break;
            case R.id.cet_data_trend_current_rb:
                currentRB.setChecked(true);
                break;
            case R.id.cet_data_trend_power_rb:
                powerRB.setChecked(true);
                break;
            default:
                break;
        }
    }

    private List<DataTrend> getTrendData() {
        List<DataTrend> list = new ArrayList<>();
        DataTrend dataTrend = new DataTrend();
        dataTrend.setData("1-15");
        dataTrend.setVoltage("100");
        dataTrend.setCurrent("60");
        dataTrend.setPower("150");
        list.add(dataTrend);
        dataTrend = new DataTrend();
        dataTrend.setData("1-16");
        dataTrend.setVoltage("120");
        dataTrend.setCurrent("50");
        dataTrend.setPower("130");
        list.add(dataTrend);
        dataTrend = new DataTrend();
        dataTrend.setData("1-17");
        dataTrend.setVoltage("150");
        dataTrend.setCurrent("90");
        dataTrend.setPower("180");
        list.add(dataTrend);
        dataTrend = new DataTrend();
        dataTrend.setData("1-18");
        dataTrend.setVoltage("130");
        dataTrend.setCurrent("100");
        dataTrend.setPower("200");
        list.add(dataTrend);
        dataTrend = new DataTrend();
        dataTrend.setData("1-19");
        dataTrend.setVoltage("90");
        dataTrend.setCurrent("10");
        dataTrend.setPower("20");
        list.add(dataTrend);
        return list;
    }

    private void initCountView(View view) {
        data_address = (TextView) view.findViewById(R.id.data_address);
        voltageRegulateTime = (TextView) view.findViewById(R.id.voltageRegulateTime);
        voltageRegulate = (TextView) view.findViewById(R.id.voltageRegulate);
        reactiveCompensation = (TextView) view.findViewById(R.id.reactiveCompensation);
        manufacture = (TextView) view.findViewById(R.id.manufacture);
        model = (TextView) view.findViewById(R.id.model);
        capacity = (TextView) view.findViewById(R.id.capacity);
        phrase = (TextView) view.findViewById(R.id.phrase);
        circuit = (TextView) view.findViewById(R.id.circuit);
        location = (TextView) view.findViewById(R.id.location);
    }

    private void initRealtimeView(View view) {
//        avo_tv = (TextView) view.findViewById(R.id.cet_realtime_input_avoltage);

        cet_realtime_input_avoltage = (TextView) view.findViewById(R.id.cet_realtime_input_avoltage);
        cet_realtime_out_avoltage = (TextView) view.findViewById(R.id.cet_realtime_out_avoltage);
        cet_realtime_input_bvoltage = (TextView) view.findViewById(R.id.cet_realtime_input_bvoltage);
        cet_realtime_out_bvoltage = (TextView) view.findViewById(R.id.cet_realtime_out_bvoltage);
        cet_realtime_input_cvoltage = (TextView) view.findViewById(R.id.cet_realtime_input_cvoltage);
        cet_realtime_out_cvoltage = (TextView) view.findViewById(R.id.cet_realtime_out_cvoltage);
        cet_realtime_input_acurrent = (TextView) view.findViewById(R.id.cet_realtime_input_acurrent);
        cet_realtime_out_acurrent = (TextView) view.findViewById(R.id.cet_realtime_out_acurrent);
        cet_realtime_input_bcurrent = (TextView) view.findViewById(R.id.cet_realtime_input_bcurrent);
        cet_realtime_out_bcurrent = (TextView) view.findViewById(R.id.cet_realtime_out_bcurrent);
        cet_realtime_input_ccurrent = (TextView) view.findViewById(R.id.cet_realtime_input_ccurrent);
        cet_realtime_out_ccurrent = (TextView) view.findViewById(R.id.cet_realtime_out_ccurrent);
        cet_realtime_input_apower = (TextView) view.findViewById(R.id.cet_realtime_input_apower);
        cet_realtime_out_apower = (TextView) view.findViewById(R.id.cet_realtime_out_apower);
        cet_realtime_input_bpower = (TextView) view.findViewById(R.id.cet_realtime_input_bpower);
        cet_realtime_out_bpower = (TextView) view.findViewById(R.id.cet_realtime_out_bpower);
        cet_realtime_input_cpower = (TextView) view.findViewById(R.id.cet_realtime_input_cpower);
        cet_realtime_out_cpower = (TextView) view.findViewById(R.id.cet_realtime_out_cpower);
        cet_realtime_voltage_adjust = (TextView) view.findViewById(R.id.cet_realtime_voltage_adjust);
        cet_realtime_nopower = (TextView) view.findViewById(R.id.cet_realtime_nopower);

    }

    private void initTrendView(View view) {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.cet_data_trend_layout);
        layout.addView(new GraphicalView(getActivity(), GraphicalUtils.drawDataTrend(getTrendData(), 220, 0)));
        trendRadioGroup = (RadioGroup) view.findViewById(R.id.cet_data_trend_rg);
        voltageRB = (RadioButton) view.findViewById(R.id.cet_data_trend_voltage_rb);
        currentRB = (RadioButton) view.findViewById(R.id.cet_data_trend_current_rb);
        powerRB = (RadioButton) view.findViewById(R.id.cet_data_trend_power_rb);
        trendRadioGroup.setOnCheckedChangeListener(this);
        bdate_tv = (TextView) view.findViewById(R.id.cet_data_trend_date_before);
        adate_tv = (TextView) view.findViewById(R.id.cet_data_trend_date_after);
        bdate_tv.setOnClickListener(this);
        adate_tv.setOnClickListener(this);
    }

    private void showDataPickerView() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (isbdate) {
                    bdate_tv.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                } else {
                    adate_tv.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cet_data_trend_date_before:
                isbdate = true;
                showDataPickerView();
                break;
            case R.id.cet_data_trend_date_after:
                isbdate = false;
                showDataPickerView();
                break;
            default:
                break;
        }
    }

    public Handler getHandler() {
        return handler;
    }
}
