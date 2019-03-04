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
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.db.SQLhelper_Device;
import com.electric.cet.mobile.android.pq.model.DataTrend;
import com.electric.cet.mobile.android.pq.ui.adapter.BasePagerAdapter;
import com.electric.cet.mobile.android.pq.ui.view.GraphicalUtils;
import com.electric.cet.mobile.android.pq.ui.view.GraphicalView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.electric.cet.mobile.android.pq.utils.OkHttpUtils.doGET;

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

    private DataBean dataBean;

    String url_realTime = "http://192.168.2.106/LowLineSys/device/2/data/realtime";
    String url_trend = "http://192.168.2.106/LowLineSys/device/3/data/trend/2019-02-22/2019-02-28";



    private int baseDistance;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1002:
                    countRB.setChecked(true);
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
         dataBean = SQLhelper_Device.Instance(getActivity()).queryUserInfo();
//        initRealtimeData();
//        initTrendData();
    }

private SQLhelper_Device dbHelper;


    //请求趋势数据
    public void initTrendData() {
        OkHttpClient client = new OkHttpClient();
        String DeviceId = null;

        RequestBody formBody = new FormBody.Builder().add("deviceId","2" ) //deviceId如何传入？
                .add("startTime", "2019-02-22")  //参数如何填入？
//        bdate_tv.getText().toString().trim().replace("/","-")
                .add("endTime", "2019-02-28").build();
        Request request = new Request.Builder().url(url_trend).get().build();
        doGET(url_trend, request);
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
//                //使用gson解析json数据
//                //new一个Gson对象
//                Gson gson = new Gson();
//                //将json字符串转为dataBean对象
//                RealTimeBean realTimeBean = gson.fromJson(jsonData, RealTimeBean.class);
//                Log.d("DataFragment", "-------开始解析实时数据-------------");
//                Log.d("DataFragment", "DEVICE ID IS " + realTimeBean.getRealTimeData().get(0).getDeviceId());
//                Log.d("DataFragment", "DEVICE NAME IS" + realTimeBean.getRealTimeData().get(1).getDeviceName());
//                Log.d("DataFragment", "BVoltageInput" + realTimeBean.getRealTimeData().get(1).getBVoltageInput());
//                Log.d("DataFragment", "-------实时数据解析成功-------------");

            }
        });
    }


    //请求实时数据
    public void initRealtimeData() {
        OkHttpClient client = new OkHttpClient();
        String deviceid = null;
        RequestBody formBody = new FormBody.Builder().add("deviceId", "2").build(); //参数如何填入？
        Request request = new Request.Builder().url(url_realTime).get().build();
        doGET(url_realTime, request);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 提示错误信息
                Log.d("DataFrament", "实时数据请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                String jsonData = str;
                Log.d("DataFrament", "实时数据请求打印" + jsonData);
                Log.d("DataFrament", "实时数据打印成功");
//                //使用gson解析json数据
//                //new一个Gson对象
//                Gson gson = new Gson();
//                //将json字符串转为dataBean对象
//                RealTimeBean realTimeBean = gson.fromJson(jsonData, RealTimeBean.class);
//                Log.d("DataFragment", "DEVICE ID IS " + realTimeBean.getRealTimeData().get(1).getDeviceId() + "");
//                Log.d("DataFragment","DEVICE NAME IS" + realTimeBean.getRealTimeData().get(1).getDeviceName() + "");
//                Log.d("DataFragment", "BVoltageInput" + realTimeBean.getRealTimeData().get(1).getBVoltageInput() + "");
//                Log.d("DataFragment", "-------实时数据解析成功-------------");
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

    }

    private void initRealtimeView(View view) {
        avo_tv = (TextView) view.findViewById(R.id.cet_realtime_input_avoltage);
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
