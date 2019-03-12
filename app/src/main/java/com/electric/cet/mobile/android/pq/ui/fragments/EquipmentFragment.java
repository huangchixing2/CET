package com.electric.cet.mobile.android.pq.ui.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.db.SQLhelper_Device;
import com.electric.cet.mobile.android.pq.model.EquipmentWorkingModel;
import com.electric.cet.mobile.android.pq.ui.activity.CollectDetailActivity;
import com.electric.cet.mobile.android.pq.ui.activity.EquipmentCollectAddActivity;
import com.electric.cet.mobile.android.pq.ui.activity.WorkingDetailActivity;
import com.electric.cet.mobile.android.pq.ui.adapter.BasePagerAdapter;
import com.electric.cet.mobile.android.pq.ui.adapter.EquipmentCollectAdapter;
import com.electric.cet.mobile.android.pq.ui.adapter.EquipmentWorkingAdapter;

import java.util.ArrayList;
import java.util.List;


public class EquipmentFragment extends BaseFragment implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private RadioGroup equipmentRadioGroup;
    private RadioButton collectRB;
    private RadioButton workingRB;

    private LinearLayout tabLineLayout;
    private ImageView tablineImg;

    private List<View> views = new ArrayList<View>();
    private ViewPager viewPager;
    private BasePagerAdapter pagerAdapter;

    private ImageView add_tv;
    private ImageView edit_tv;
    private ImageView del_tv;
    private CheckBox all_cb;

    private static final int pageCollect = 0;
    private static final int pageWorking = 1;
    //台账
    private ListView collect_lv;
    private EquipmentCollectAdapter collectAdapter;
    private List<DataBean> allDevicesList = new ArrayList<>();
    //工况
    private ListView working_lv;
    private EquipmentWorkingAdapter workingAdapter;
    private List<EquipmentWorkingModel> works = new ArrayList<>();

    private int baseDistance;
    private TextView equipment_collect_title_address;
    private TextView equipment_collect_title_type;
    private TextView equipment_collect_title_statu;
    private Spinner spinner_collect;
    private Spinner spinner_work;
//    private RelativeLayout collect_rl; //台账
    private RelativeLayout work_rl; //工况
    private ArrayAdapter cAdapter;
    private ArrayAdapter wAdapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 100:
                break;
                default:
                    break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        View collectView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_equipment_collect_layout, null);
        initCollectView(collectView);
        View workingView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_equipment_working_layout, null);
        initWorkingView(workingView);
        views.add(collectView);
        views.add(workingView);
        equipmentRadioGroup = (RadioGroup) view.findViewById(R.id.cet_equipment_list_rg);
        collectRB = (RadioButton) view.findViewById(R.id.cet_equipment_collect_tab);
        workingRB = (RadioButton) view.findViewById(R.id.cet_equipment_working_tab);
        equipmentRadioGroup.setOnCheckedChangeListener(this);
        tabLineLayout = (LinearLayout) view.findViewById(R.id.cet_equipment_tab_line_layout);
        tablineImg = (ImageView) view.findViewById(R.id.cet_equipment_list_tab_line_img);
        viewPager = (ViewPager) view.findViewById(R.id.cet_equipment_list_viewpager);

        equipment_collect_title_address = (TextView) view.findViewById(R.id.equipment_collect_title_address);
        equipment_collect_title_type = (TextView) view.findViewById(R.id.equipment_collect_title_type);
        equipment_collect_title_statu = (TextView) view.findViewById(R.id.equipment_collect_title_statu);


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

    private void initData() {
        allDevicesList.clear();
        allDevicesList.addAll(getCollectData());
        collectAdapter.notifyDataSetChanged();
        workingAdapter.notifyDataSetChanged();
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {
        int location = Math.round(v * baseDistance) + i * baseDistance;
        tabLineLayout.scrollTo(-location, 0);
    }

    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case pageCollect:
                collectRB.setChecked(true);
                initCollectView(viewPager);//旋转台账显示
                break;
            case pageWorking:
                workingRB.setChecked(true);
                initWorkingView(viewPager);//选择工况显示
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

    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), CollectDetailActivity.class);
            intent.putExtra("data", allDevicesList.get(arg2));
            startActivity(intent);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    //设备类型定义
    private static final String[] deviceType = {"低压调压器", "无功补偿装置", "静止无功发生器", "混合型无功补偿装置", "中压调压器", "中压静止无功发生器", "中压串补"};

    //台账view显示
    private void initCollectView(View view) {
        collect_lv = (ListView) view.findViewById(R.id.cet_equipment_collect_lv);
        collectAdapter = new EquipmentCollectAdapter(getActivity(), allDevicesList);
        //去掉搜索框
//        cet_working_search_rl = (RelativeLayout)view.findViewById(R.id.cet_device_search_rl);
        spinner_collect = (Spinner) view.findViewById(R.id.spinner_collect);
        //将可选内容与ArrayAdapter连接起来
        cAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,deviceType);

        //设置下拉列表的风格
        cAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        spinner_collect.setAdapter(cAdapter);
        //设置默认值
        spinner_collect.setVisibility(View.VISIBLE);

        //设置spinner监听
        spinner_collect.setOnItemSelectedListener(new SpinnerSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                super.onItemSelected(parent, view, pos, id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                super.onNothingSelected(arg0);
            }
        });

        //去掉搜索框功能
//        collect_rl.setOnClickListener((new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(),SearchActivity.class);
//                intent.putExtra("requestCode",Constans.COLLECT_CODE);
//                getActivity().startActivityForResult(intent,Constans.COLLECT_CODE);
//            }
//        }));


        collect_lv.setAdapter(collectAdapter);
        collect_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CollectDetailActivity.class);
                intent.putExtra("data", allDevicesList.get(position));
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
                selectAllItem(isChecked);
            }
        });
    }

    //工况view显示
    private void initWorkingView(View view) {
        working_lv = (ListView) view.findViewById(R.id.cet_equipment_working_lv);
        workingAdapter = new EquipmentWorkingAdapter(getActivity(), allDevicesList);

        spinner_work = (Spinner) view.findViewById(R.id.spinner_work);
        //将可选内容与ArrayAdapter连接起来
        wAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,deviceType);

        //设置下拉列表的风格
        wAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        spinner_work.setAdapter(wAdapter);
        //设置默认值
        spinner_work.setVisibility(View.VISIBLE);

        //设置spinner监听
        spinner_work.setOnItemSelectedListener(new SpinnerSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                super.onItemSelected(parent, view, pos, id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                super.onNothingSelected(arg0);
            }
        });

        //去掉搜索框功能
//        work_rl = (RelativeLayout)view.findViewById(R.id.cet_working_search_rl);
//        work_rl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(),SearchActivity.class);
//                intent.putExtra("requestCode",Constans.WORKING_CODE);
//                getActivity().startActivityForResult(intent,Constans.WORKING_CODE);
//            }
//        });

        working_lv.setAdapter(workingAdapter);
        working_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), WorkingDetailActivity.class);
                intent.putExtra("data", allDevicesList.get(position));
                startActivity(intent);
            }
        });
    }

    //台账信息查询数据库
    private List<DataBean> getCollectData() {
        List<DataBean> devicesList = SQLhelper_Device.Instance(getActivity()).queryDeviceList(); //参数如何传递
        return devicesList;
    }

    //工况信息查询
    private List<EquipmentWorkingModel> getWorkingData() {
        works.clear();
        allDevicesList.clear();
        allDevicesList = SQLhelper_Device.Instance(getActivity()).queryDeviceList(); //参数如何传递
//            Log.d("guol","devicesList.size:"+collectList.size());//为何只有一条数据？
        for (int i = 0; i < allDevicesList.size(); i++) {
            EquipmentWorkingModel equipmentWorkingModel = new EquipmentWorkingModel();
            equipmentWorkingModel.setSle(false); //默认设置为不勾选
            equipmentWorkingModel.setAddress(allDevicesList.get(i).getInstallAddress());
            equipmentWorkingModel.setType("调压器");//需要确认对应关系？
            if (allDevicesList.get(i).getState()) {
                equipmentWorkingModel.setStatu(true);
            } else {
                equipmentWorkingModel.setStatu(false);
            }
            works.add(equipmentWorkingModel);
        }
        return works;
    }

    //删除数据时提示用户，避免误操作
    private void dialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            //删除
                deleteSelectedData();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setMessage("确定要删除此设备吗？");
        dialog.setTitle("提示");
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cet_equipment_collect_add:
                Intent addIntent = new Intent();
                addIntent.setClass(getActivity(), EquipmentCollectAddActivity.class);
                startActivity(addIntent);
                break;
            case R.id.cet_equipment_collect_edit:
                Intent editIntent = new Intent();
                editIntent.setClass(getActivity(), EquipmentCollectAddActivity.class);
                startActivity(editIntent);
                break;
            case R.id.cet_equipment_collect_del:
                if (!isSelectedItem()) {
                    Toast.makeText(getActivity(), "请先选择删除项", Toast.LENGTH_SHORT).show();
                } else {
                    dialog();//删除数据时提示用户，避免误操作
                }
                break;
            default:
                break;
        }
    }

    private void deleteSelectedData() {
        List<DataBean> deleteList = new ArrayList<>();
        for (int i = 0; i < allDevicesList.size(); i++) {
            if (allDevicesList.get(i).isSle()) {
                deleteList.add(allDevicesList.get(i));
            }
        }
        allDevicesList.removeAll(deleteList);
        collectAdapter.notifyDataSetChanged();
        //delete stayList in db
    }

    private boolean isSelectedItem() {
        boolean isSelected = false;
        for (int i = 0; i < allDevicesList.size(); i++) {
            if (allDevicesList.get(i).isSle()) {
                isSelected = true;
                break;
            }
        }
        return isSelected;
    }

    private void selectAllItem(boolean isSelected) {
        for (int i = 0; i < allDevicesList.size(); i++) {
            allDevicesList.get(i).setSle(isSelected);
        }
        collectAdapter.notifyDataSetChanged();
    }

    public Handler getHandler() {
        return handler;
    }

}
