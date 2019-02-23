package com.electric.cet.mobile.android.pq.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.model.EquipmentCollectModel;
import com.electric.cet.mobile.android.pq.model.EquipmentWorkingModel;
import com.electric.cet.mobile.android.pq.ui.activity.EquipmentCollectAddActivity;
import com.electric.cet.mobile.android.pq.ui.activity.EquipmentDetailActivity;
import com.electric.cet.mobile.android.pq.ui.adapter.BasePagerAdapter;
import com.electric.cet.mobile.android.pq.ui.adapter.EquipmentCollectAdapter;
import com.electric.cet.mobile.android.pq.ui.adapter.EquipmentWorkingAdapter;

import java.util.ArrayList;
import java.util.List;

//设备
public class EquipmentFragment extends BaseFragment implements ViewPager.OnPageChangeListener,RadioGroup.OnCheckedChangeListener,View.OnClickListener {
    private RadioGroup equipmentRadioGroup;
    private RadioButton collectRB;
    private RadioButton workingRB;

    private LinearLayout tabLineLayout;
    private ImageView tablineImg;

    private List<View> views;
    private ViewPager viewPager;
    private BasePagerAdapter pagerAdapter;

    private ImageView add_tv;
    private ImageView edit_tv;
    private ImageView del_tv;
    private CheckBox all_cb;

    private ListView collect_lv;
    private EquipmentCollectAdapter collectAdapter;
    private List<EquipmentCollectModel> collectList = new ArrayList<>();

    private ListView working_lv;
    private EquipmentWorkingAdapter workingAdapter;
    private List<EquipmentWorkingModel> workingList = new ArrayList<>();

    private int baseDistance;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view){
        views = new ArrayList<View>();
        View collectView = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_equipment_collect_layout, null);
        initCollectView(collectView);
        View workingView = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_equipment_working_layout, null);
        initWorkingView(workingView);
        views.add(collectView);
        views.add(workingView);
        equipmentRadioGroup = (RadioGroup) view.findViewById(R.id.cet_equipment_list_rg);
        collectRB = (RadioButton) view
                .findViewById(R.id.cet_equipment_collect_tab);
        workingRB = (RadioButton) view
                .findViewById(R.id.cet_equipment_working_tab);
        equipmentRadioGroup.setOnCheckedChangeListener(this);
        tabLineLayout = (LinearLayout) view
                .findViewById(R.id.cet_equipment_tab_line_layout);
        tablineImg = (ImageView) view.findViewById(R.id.cet_equipment_list_tab_line_img);
        viewPager = (ViewPager) view.findViewById(R.id.cet_equipment_list_viewpager);
        DisplayMetrics metrics = new DisplayMetrics();
        metrics = getResources().getDisplayMetrics();
        baseDistance = (int) Math.round(metrics.widthPixels / 2.0);
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
                collectRB.setChecked(true);
                break;
            case 1:
                workingRB.setChecked(true);
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
            case R.id.cet_equipment_collect_tab:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.cet_equipment_working_tab:
                viewPager.setCurrentItem(1, true);
                break;
            default:
                break;
        }
    }

    private void initCollectView(View view){
        collect_lv = (ListView) view.findViewById(R.id.cet_equipment_collect_lv);
        collectAdapter = new EquipmentCollectAdapter(getActivity(),getCollectData());
        collect_lv.setAdapter(collectAdapter);
        collect_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent();
                intent.setClass(getActivity(),EquipmentDetailActivity.class);
                startActivity(intent);
            }
        });
        add_tv = (ImageView) view.findViewById(R.id.cet_equipment_collect_add);
        edit_tv = (ImageView) view.findViewById(R.id.cet_equipment_collect_edit);
        del_tv = (ImageView) view.findViewById(R.id.cet_equipment_collect_del);
        all_cb = (CheckBox) view.findViewById(R.id.cet_equipment_collect_all_cb);
        add_tv.setOnClickListener(this);
        edit_tv.setOnClickListener(this);
        del_tv.setOnClickListener(this);
        all_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                allSelectedFreshen(isChecked);
            }
        });
    }

    private  void initWorkingView(View view){
        working_lv = (ListView) view.findViewById(R.id.cet_equipment_working_lv);
        workingAdapter = new EquipmentWorkingAdapter(getActivity(),getWorkingData());
        working_lv.setAdapter(workingAdapter);
        working_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent();
                intent.setClass(getActivity(),EquipmentDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<EquipmentCollectModel> getCollectData(){
        collectList.clear();
        EquipmentCollectModel equipmentCollectModel = new EquipmentCollectModel();
        equipmentCollectModel.setSle(true);
        equipmentCollectModel.setAddress("武汉花山台区1");
        equipmentCollectModel.setType("调压器");
        equipmentCollectModel.setStatu(true);
        collectList.add(equipmentCollectModel);
        equipmentCollectModel = new EquipmentCollectModel();
        equipmentCollectModel.setSle(false);
        equipmentCollectModel.setAddress("武汉花山台区2");
        equipmentCollectModel.setType("调压器");
        equipmentCollectModel.setStatu(true);
        collectList.add(equipmentCollectModel);
        equipmentCollectModel = new EquipmentCollectModel();
        equipmentCollectModel.setSle(false);
        equipmentCollectModel.setAddress("武汉花山台区3");
        equipmentCollectModel.setType("调压器");
        equipmentCollectModel.setStatu(true);
        collectList.add(equipmentCollectModel);
        equipmentCollectModel = new EquipmentCollectModel();
        equipmentCollectModel.setSle(false);
        equipmentCollectModel.setAddress("武汉花山台区4");
        equipmentCollectModel.setType("调压器");
        equipmentCollectModel.setStatu(true);
        collectList.add(equipmentCollectModel);
        equipmentCollectModel = new EquipmentCollectModel();
        equipmentCollectModel.setSle(false);
        equipmentCollectModel.setAddress("武汉花山台区5");
        equipmentCollectModel.setType("调压器");
        equipmentCollectModel.setStatu(false);
        collectList.add(equipmentCollectModel);
        return collectList;
    }

    private List<EquipmentWorkingModel> getWorkingData(){
        workingList.clear();
        EquipmentWorkingModel equipmentWorkingModel = new EquipmentWorkingModel();
        equipmentWorkingModel.setSle(true);
        equipmentWorkingModel.setAddress("武汉花山台区1");
        equipmentWorkingModel.setType("调压器");
        equipmentWorkingModel.setStatu(true);
        workingList.add(equipmentWorkingModel);
        equipmentWorkingModel = new EquipmentWorkingModel();
        equipmentWorkingModel.setSle(false);
        equipmentWorkingModel.setAddress("武汉花山台区2");
        equipmentWorkingModel.setType("调压器");
        equipmentWorkingModel.setStatu(true);
        workingList.add(equipmentWorkingModel);
        equipmentWorkingModel = new EquipmentWorkingModel();
        equipmentWorkingModel.setSle(false);
        equipmentWorkingModel.setAddress("武汉花山台区3");
        equipmentWorkingModel.setType("调压器");
        equipmentWorkingModel.setStatu(true);
        workingList.add(equipmentWorkingModel);
        equipmentWorkingModel = new EquipmentWorkingModel();
        equipmentWorkingModel.setSle(false);
        equipmentWorkingModel.setAddress("武汉花山台区4");
        equipmentWorkingModel.setType("调压器");
        equipmentWorkingModel.setStatu(true);
        workingList.add(equipmentWorkingModel);
        equipmentWorkingModel = new EquipmentWorkingModel();
        equipmentWorkingModel.setSle(false);
        equipmentWorkingModel.setAddress("武汉花山台区5");
        equipmentWorkingModel.setType("调压器");
        equipmentWorkingModel.setStatu(false);
        workingList.add(equipmentWorkingModel);
        return workingList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cet_equipment_collect_add:
                Intent addIntent = new Intent();
                addIntent.setClass(getActivity(),EquipmentCollectAddActivity.class);
                startActivity(addIntent);
                break;
            case R.id.cet_equipment_collect_edit:
                Intent editIntent = new Intent();
                editIntent.setClass(getActivity(),EquipmentCollectAddActivity.class);
                startActivity(editIntent);
                break;
            case R.id.cet_equipment_collect_del:
                if(!isSelectedItem()){
                    Toast.makeText(getActivity(),"请选择后删除",Toast.LENGTH_SHORT).show();
                }else{
                    deleteSelectedData();
                }
                break;
               default:
                   break;
        }
    }

    private void deleteSelectedData(){
        List<EquipmentCollectModel> stayList = new ArrayList<>();
        for(int i = 0;i<collectList.size();i++){
            if(!collectList.get(i).isSle()){
                stayList.add(collectList.get(i));
            }
        }
        collectList.clear();
        collectList.addAll(stayList);
        collectAdapter.notifyDataSetChanged();
    }

    private boolean isSelectedItem(){
        boolean isSelected = false;
        for(int i = 0;i<collectList.size();i++){
            if(collectList.get(i).isSle()){
                isSelected = true;
                continue;
            }
        }
        return isSelected;
    }

    private void allSelectedFreshen(boolean isSelected){
        for(int i = 0;i<collectList.size();i++){
            collectList.get(i).setSle(isSelected);
        }
        collectAdapter.notifyDataSetChanged();
    }
}
