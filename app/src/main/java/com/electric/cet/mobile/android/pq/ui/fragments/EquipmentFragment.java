package com.electric.cet.mobile.android.pq.ui.fragments;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.electric.cet.mobile.android.pq.Bean.OptionBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.db.SQLhelper_Device;
import com.electric.cet.mobile.android.pq.model.EquipmentWorkingModel;
import com.electric.cet.mobile.android.pq.ui.activity.CollectDetailActivity;
import com.electric.cet.mobile.android.pq.ui.activity.EquipmentCollectAddActivity;
import com.electric.cet.mobile.android.pq.ui.activity.EquipmentCollectEditActivity;
import com.electric.cet.mobile.android.pq.ui.activity.WorkingDetailActivity;
import com.electric.cet.mobile.android.pq.ui.adapter.BasePagerAdapter;
import com.electric.cet.mobile.android.pq.ui.adapter.EquipmentCollectAdapter;
import com.electric.cet.mobile.android.pq.ui.adapter.EquipmentWorkingAdapter;
import com.electric.cet.mobile.android.pq.utils.ChangeTypeUtil;
import com.electric.cet.mobile.android.pq.utils.Constans;
import com.electric.cet.mobile.android.pq.utils.NetWorkUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.electric.cet.mobile.android.pq.utils.OkHttpUtils.response;


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
    //设备类型定义
    private static final String[] deviceType = {"全部类型", "低压调压器", "无功补偿装置", "静止无功发生器", "混合型无功补偿装置", "中压调压器", "中压静止无功发生器", "中压串补"};
    private RelativeLayout rlNoData;
    private LinearLayout llContent;
    View collectView;
    View workingView;
    DataBean dataBean;
    List<DataBean> deleteList = new ArrayList<>();
    List<Long> deleteIdList = new ArrayList<>();


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    break;
                case Constans.collect_spanner:
                    rlNoData.setVisibility(View.GONE);
                    llContent.setVisibility(View.VISIBLE);
                    int collectType = msg.getData().getInt("collectType", 0);
                    List<DataBean> devicesList = SQLhelper_Device.Instance(getActivity()).queryDeviceListByType(collectType); //参数如何传递
                    allDevicesList.clear();
                    allDevicesList.addAll(devicesList);
                    collectAdapter.notifyDataSetChanged();
                    break;
                case Constans.work_spanner:
                    rlNoData.setVisibility(View.GONE);
                    llContent.setVisibility(View.VISIBLE);
                    int workType = msg.getData().getInt("workType", 0);
                    List<DataBean> workList = SQLhelper_Device.Instance(getActivity()).queryDeviceListByType(workType); //参数如何传递
                    allDevicesList.clear();
                    allDevicesList.addAll(workList);
                    workingAdapter.notifyDataSetChanged();
                    break;
                case 201:
                    Toast.makeText(getActivity(), "数据删除成功", Toast.LENGTH_SHORT).show();
                    //delete stayList in db
                    allDevicesList.removeAll(deleteList);
                    collectAdapter.notifyDataSetChanged();
                    SQLhelper_Device.Instance(getActivity()).delDevices(deleteIdList);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment, container, false);
        registerBroadcast();
        initView(view);
        initData(0);
        return view;
    }

    private void initView(View view) {
        collectView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_equipment_collect_layout, null);
        initCollectView(collectView);
        workingView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_equipment_working_layout, null);
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

    private void initData(int position) {
        if (!NetWorkUtil.isNetworkAvailable(getActivity())) {
            initNoInternetView();
        }
        //解析出节点数据
        SharedPreferences sp = getActivity().getSharedPreferences("treeData", MODE_PRIVATE);
        final String str_tree = sp.getString("str_Tree", ""); //获取原始登录的密码
        Gson gson = new Gson();
        //将json字符串转为dataBean对象
        OptionBean optionBean = gson.fromJson(str_tree, OptionBean.class);

//        allDevicesList.clear();
//        allDevicesList.addAll(getCollectData());
//        collectAdapter.notifyDataSetChanged();
//        workingAdapter.notifyDataSetChanged();

        //处理网络切换问题
        switch (position) {
            case 0:
                initCollectView(collectView);
                if (NetWorkUtil.isNetworkAvailable(getActivity())) {
                    if (llContent != null && llContent.getVisibility() != View.VISIBLE) {
                        llContent.setVisibility(View.VISIBLE);
                        if (allDevicesList.size() == 0) {
                            allDevicesList.clear();
                            allDevicesList.addAll(getCollectData());
                            collectAdapter.notifyDataSetChanged();
                        }
                    }
                    if (rlNoData != null && rlNoData.getVisibility() == View.VISIBLE) {
                        rlNoData.setVisibility(View.GONE);
                    }
                } else {
                    initNoInternetView();
                }
                break;
            case 1:
                initWorkingView(workingView);
                if (NetWorkUtil.isNetworkAvailable(getActivity())) {
                    if (llContent != null && llContent.getVisibility() != View.VISIBLE) {
                        llContent.setVisibility(View.VISIBLE);
                        if (allDevicesList.size() == 0) {
                            allDevicesList.clear();
                            allDevicesList.addAll(getCollectData());
                            workingAdapter.notifyDataSetChanged();
                        }
                    }
                    if (rlNoData != null && rlNoData.getVisibility() == View.VISIBLE) {
                        rlNoData.setVisibility(View.GONE);
                    }
                } else {
                    initNoInternetView();
                }
                break;
            default:
                break;
        }

    }

    protected void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constans.ACTION_EQUIPMENT_ADD_SUCCESS);
        if (null == getActivity()) return;
        getActivity().registerReceiver(receiver, intentFilter);
    }

    protected BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(Constans.ACTION_EQUIPMENT_ADD_SUCCESS, intent.getAction())) {
                initData(0);
            }
        }
    };

    //网络异常
    private void initNoInternetView() {
        if (rlNoData == null || llContent == null) return;
        llContent.setVisibility(View.GONE);
        rlNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null == getActivity()) return;
        getActivity().unregisterReceiver(receiver);
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
//                initCollectView(viewPager);//旋转台账显示
                initData(0);
                break;
            case pageWorking:
                workingRB.setChecked(true);
//                initWorkingView(viewPager);//选择工况显示
                initData(1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        initData(i);
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

//    //使用数组形式操作
//    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
//
//        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//            Intent intent = new Intent();
//            intent.setClass(getActivity(), CollectDetailActivity.class);
//            intent.putExtra("data", allDevicesList.get(arg2));
//            startActivity(intent);
//        }
//
//        public void onNothingSelected(AdapterView<?> arg0) {
//        }
//    }


    //台账view显示
    private void initCollectView(View view) {
        rlNoData = (RelativeLayout) view.findViewById(R.id.rl_no_data);
        llContent = (LinearLayout) view.findViewById(R.id.content_ll);
        collect_lv = (ListView) view.findViewById(R.id.cet_equipment_collect_lv);
        collectAdapter = new EquipmentCollectAdapter(getActivity(), allDevicesList);
        //去掉搜索框
//        cet_working_search_rl = (RelativeLayout)view.findViewById(R.id.cet_device_search_rl);
        spinner_collect = (Spinner) view.findViewById(R.id.spinner_collect);
        //将可选内容与ArrayAdapter连接起来
        cAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, deviceType);

        //设置下拉列表的风格
        cAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        spinner_collect.setAdapter(cAdapter);
        //设置默认值
        spinner_collect.setVisibility(View.VISIBLE);
        spinner_collect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Message message = handler.obtainMessage();
                message.what = Constans.collect_spanner;
                message.getData().putInt("collectType", ChangeTypeUtil.changS2I(deviceType[position]));
                handler.sendMessage(message);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        rlNoData = (RelativeLayout) view.findViewById(R.id.rl_no_data);
        llContent = (LinearLayout) view.findViewById(R.id.content_ll);
        working_lv = (ListView) view.findViewById(R.id.cet_equipment_working_lv);
        workingAdapter = new EquipmentWorkingAdapter(getActivity(), allDevicesList);

        spinner_work = (Spinner) view.findViewById(R.id.spinner_work);
        //将可选内容与ArrayAdapter连接起来
        wAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, deviceType);

        //设置下拉列表的风格
        wAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        spinner_work.setAdapter(wAdapter);
        //设置默认值
        spinner_work.setVisibility(View.VISIBLE);


        spinner_work.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Message message = handler.obtainMessage();
                message.what = Constans.work_spanner;
                message.getData().putInt("workType", ChangeTypeUtil.changS2I(deviceType[position]));
                Log.d("huangcc", ChangeTypeUtil.changS2I(deviceType[position]) + "");
                handler.sendMessage(message);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
    private void dialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doDelete();
                //删除
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
                //请求服务器


                //如果选中大于0，弹框提示
                List<DataBean> addList = new ArrayList<>();
                for (int i = 0; i < allDevicesList.size(); i++) {
                    if (allDevicesList.get(i).isSle()) {
                        addList.add(allDevicesList.get(i));
                    }
                }
                if (addList.size() > 0) {
                    Toast.makeText(getActivity(), "选中数据时无法添加", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent addIntent = new Intent();
                addIntent.setClass(getActivity(), EquipmentCollectAddActivity.class);
                startActivity(addIntent);
                break;
            case R.id.cet_equipment_collect_edit:
                List<DataBean> selectList = new ArrayList<>();
                //如果选中大于1，弹框提示
                for (int i = 0; i < allDevicesList.size(); i++) {
                    if (allDevicesList.get(i).isSle()) {
                        selectList.add(allDevicesList.get(i));
                    }
                }
                if (selectList.size() ==0) {
                    Toast.makeText(getActivity(), "请选择一条数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectList.size() > 1) {
                    Toast.makeText(getActivity(), "最多选择一条数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent editIntent = new Intent();
                editIntent.setClass(getActivity(), EquipmentCollectEditActivity.class);
                editIntent.putExtra("updateData", selectList.get(0));
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


    //点击保存按钮提交数据到服务器
    private void doDelete() {
        //发起post请求给服务器
        OkHttpClient client = new OkHttpClient();


        RequestBody formBody = new FormBody.Builder().
                add("deviceIds", String.valueOf(deleteIdList)).build();
        final Request request = new Request.Builder().url(Constans.URL_ADD).delete(formBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
                if (!response.isSuccessful()) {
                    Log.i("请求情况：", "请求失败");
                    Looper.prepare();
                    //提示用户无网络
                    Toast.makeText(getActivity(), "没有网络", Toast.LENGTH_SHORT).show();
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
                        Log.i("EquipmentCollect", "删除数据成功");

                        deleteSelectedData();

                        handler.sendEmptyMessage(201);
                    }
                }
            }
        });
    }


    private void deleteSelectedData() {
        dataBean = new DataBean();

        deleteList.clear();
        deleteIdList.clear();
        for (int i = 0; i < allDevicesList.size(); i++) {
            if (allDevicesList.get(i).isSle()) {
                deleteList.add(allDevicesList.get(i));
                deleteIdList.add(allDevicesList.get(i).getDeviceId());
            }
        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("search", "resultCode --" + resultCode);
        Log.i("search", "search --" + data.getStringExtra("search"));
        if (resultCode == 1001) {

        }
    }


    public Handler getHandler() {
        return handler;
    }

}
