package com.electric.cet.mobile.android.pq.ui.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.model.DataCountModel;
import com.electric.cet.mobile.android.pq.model.DataTrend;
import com.electric.cet.mobile.android.pq.ui.adapter.BasePagerAdapter;
import com.electric.cet.mobile.android.pq.ui.adapter.DataCountAdapter;
import com.electric.cet.mobile.android.pq.ui.view.GraphicalUtils;
import com.electric.cet.mobile.android.pq.ui.view.GraphicalView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//数据
public class DataFragment extends BaseFragment implements ViewPager.OnPageChangeListener,RadioGroup.OnCheckedChangeListener ,View.OnClickListener {
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

    private ListView count_lv;

    private int baseDistance;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view){
        views = new ArrayList<View>();
        View countView = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_data_count_layout, null);
        count_lv = (ListView) countView.findViewById(R.id.cet_data_count_lv);
        DataCountAdapter dcAdapter = new DataCountAdapter(getActivity(),getData());
        count_lv.setAdapter(dcAdapter);
        View realtimeView = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_data_realtime_layout, null);

        View trendView = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_data_trend_layout, null);
        initTrendView(trendView);
        views.add(countView);
        views.add(realtimeView);
        views.add(trendView);
        dataRadioGroup = (RadioGroup) view.findViewById(R.id.cet_data_list_rg);
        countRB = (RadioButton) view
                .findViewById(R.id.cet_data_count_tab);
        realtimeRB = (RadioButton) view
                .findViewById(R.id.cet_data_realtime_tab);
        trendRB = (RadioButton) view
                .findViewById(R.id.cet_data_trend_tab);
        tabLineLayout = (LinearLayout) view
                .findViewById(R.id.cet_data_tab_line_layout);
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

    private void initData(){

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
                break;
            case 2:
                trendRB.setChecked(true);
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

    private List<DataTrend> getTrendData(){
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

    private List<DataCountModel> getData(){
        List<DataCountModel> list = new ArrayList<>();
        DataCountModel dataCountModel = new DataCountModel();
        dataCountModel.setAddress("武汉花山台区1");
        dataCountModel.setNum(10);
        dataCountModel.setStatu(true);
        dataCountModel.setSvc(true);
        list.add(dataCountModel);
        dataCountModel = new DataCountModel();
        dataCountModel.setAddress("武汉花山台区2");
        dataCountModel.setNum(20);
        dataCountModel.setStatu(true);
        dataCountModel.setSvc(false);
        list.add(dataCountModel);
        dataCountModel = new DataCountModel();
        dataCountModel.setAddress("武汉花山台区3");
        dataCountModel.setNum(8);
        dataCountModel.setStatu(true);
        dataCountModel.setSvc(true);
        list.add(dataCountModel);
        dataCountModel = new DataCountModel();
        dataCountModel.setAddress("武汉花山台区4");
        dataCountModel.setNum(18);
        dataCountModel.setStatu(false);
        dataCountModel.setSvc(true);
        list.add(dataCountModel);
        dataCountModel = new DataCountModel();
        dataCountModel.setAddress("武汉花山台区5");
        dataCountModel.setNum(13);
        dataCountModel.setStatu(false);
        dataCountModel.setSvc(false);
        list.add(dataCountModel);
        dataCountModel = new DataCountModel();
        dataCountModel.setAddress("武汉花山台区6");
        dataCountModel.setNum(10);
        dataCountModel.setStatu(true);
        dataCountModel.setSvc(true);
        list.add(dataCountModel);
        return list;
    }

    private void initCountView(){

    }

    private void initRealtimeView(){

    }

    private void initTrendView(View view) {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.cet_data_trend_layout);
        layout.addView(new GraphicalView(getActivity(),GraphicalUtils.drawDataTrend(getTrendData(),220,0)));
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

    private void showDataPickerView(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (isbdate) {
                    bdate_tv.setText(year + "/" + (month+1) + "/" + dayOfMonth);
                } else {
                    adate_tv.setText(year + "/" + (month+1) + "/" + dayOfMonth);
                }
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
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
}
