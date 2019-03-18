package com.electric.cet.mobile.android.pq.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.Bean.DeviceBean;
import com.electric.cet.mobile.android.pq.Bean.OptionBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.db.SQLhelper_Device;
import com.electric.cet.mobile.android.pq.model.CockpitGridViewItem;
import com.electric.cet.mobile.android.pq.ui.activity.MapViewActivity;
import com.electric.cet.mobile.android.pq.ui.adapter.CockpitGridviewAdapter;
import com.electric.cet.mobile.android.pq.utils.Constans;
import com.electric.cet.mobile.android.pq.utils.NetWorkUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// 驾驶舱
public class CockpitFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout rlNoData;
    private LinearLayout llContent;
    private GridView gridview;
    private RelativeLayout install_rl;
    private RelativeLayout online_rl;
    private RelativeLayout usable_rl;
    private RelativeLayout sim_rl;
    private RelativeLayout dysfunction_rl;
    private RelativeLayout power_rl;
    private String json = null;

    private TextView install_tv;
    private TextView online_tv;
    private TextView usable_tv;
    private TextView sim_tv;
    private TextView dysfunction_tv;
    private TextView power_tv;
    private SQLhelper_Device dbHelper;
    private DeviceBean deviceBean;

    private Handler handler = new Handler() {
        //驾驶舱数据显示
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    rlNoData.setVisibility(View.GONE);
                    llContent.setVisibility(View.VISIBLE);
                    ArrayList<Integer> list = (ArrayList<Integer>) msg.getData().getBundle("data").get("list");
                    install_tv.setText(list.get(0) + ""); // int转换为string，否则报错
                    online_tv.setText(list.get(1) + "");
                    usable_tv.setText(list.get(2) + "");
                    sim_tv.setText(list.get(3) + "");
                    dysfunction_tv.setText(list.get(4) + "");
                    power_tv.setText(list.get(5) + "");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cockpit, container, false);
        initView(view);
        initAllData();
        initTreeData();

        return view;
    }


    private void initView(View view) {
        rlNoData = (RelativeLayout) view.findViewById(R.id.rl_no_data);
        llContent = (LinearLayout) view.findViewById(R.id.content_ll);
        install_rl = (RelativeLayout) view.findViewById(R.id.cockpit_install_num_rl);
        online_rl = (RelativeLayout) view.findViewById(R.id.cockpit_online_num_rl);
        usable_rl = (RelativeLayout) view.findViewById(R.id.cockpit_usable_num_rl);
        sim_rl = (RelativeLayout) view.findViewById(R.id.cockpit_sim_rl);
        dysfunction_rl = (RelativeLayout) view.findViewById(R.id.cockpit_dysfunction_rl);
        power_rl = (RelativeLayout) view.findViewById(R.id.cockpit_power_cut_rl);
        install_tv = (TextView) view.findViewById(R.id.cockpit_install_num_tv);
        online_tv = (TextView) view.findViewById(R.id.cockpit_online_num_tv);
        usable_tv = (TextView) view.findViewById(R.id.cockpit_usable_num_tv);
        sim_tv = (TextView) view.findViewById(R.id.cockpit_sim_tv);
        dysfunction_tv = (TextView) view.findViewById(R.id.cockpit_dysfunction_tv);
        power_tv = (TextView) view.findViewById(R.id.cockpit_power_cut_tv);
        install_rl.setOnClickListener(this);
        online_rl.setOnClickListener(this);
        usable_rl.setOnClickListener(this);
        sim_rl.setOnClickListener(this);
        dysfunction_rl.setOnClickListener(this);
        power_rl.setOnClickListener(this);


        gridview = (GridView) view.findViewById(R.id.cockpit_gridview);
        CockpitGridviewAdapter adapter = new CockpitGridviewAdapter(getActivity(), getData());
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MapViewActivity.class);
                startActivityForResult(intent, 1002);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initAllData();
    }

    /**
     * 显示所有数据
     */
    private void initAllData() {
        if (!NetWorkUtil.isNetworkAvailable(getActivity())) {
            initNoInternetView();
            return;
        }
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(Constans.URL_DEVICEINFO).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 提示错误信息
                Log.d("allinfo", "allinfo请求失败");
//                Toast.makeText(getActivity(), "无网络", Toast.LENGTH_SHORT).show();
                initNoInternetView();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final String str = response.body().string();
                    json = str;
                    System.out.println("cockpit allinfo打印" + json);
                    System.out.println("---------------test---------");
                    //使用gson解析json数据
                    //new一个Gson对象
                    Gson gson = new Gson();
                    //将json字符串转为dataBean对象
                    deviceBean = gson.fromJson(json, DeviceBean.class);
                    Log.d("COCKPITACTIVITY", "DEVICE ID IS " + deviceBean.getData().get(0).getDeviceName());
                    Log.d("COCKPITACTIVITY", "DEVICE ID IS " + deviceBean.getData().get(1).getDeviceId());
                    Log.d("COCKPITACTIVITY", "------数据解析成功------");
                    //存入数据库,每次清除上一次数据
                    SQLhelper_Device.Instance(getActivity()).clearAllUserInfo();
                    //网络请求到的数据写入数据库
                    SQLhelper_Device.Instance(getActivity()).insertUserInfo(deviceBean.getData());
                    //发送消息给主线程
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList("list", countData(deviceBean.getData()));
                    message.what = 1;
                    message.getData().putBundle("data", bundle);
                    handler.sendMessage(message);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

//        countData(deviceBean.getData());

    }

    //获取tree信息的请求
    public void initTreeData() {
        OkHttpClient client_option = new OkHttpClient();
        Request request = new Request.Builder().url(Constans.URL_OPTION).get().build();
        client_option.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 提示错误信息
                Log.d("optioninfo", "optioninfo请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str_tree = response.body().string();
                System.out.println("cockpit tree 打印" + str_tree);
                Log.d("cockpittree", str_tree);
                //用sp保存json数据，在台账页面初始化时候就解析出来
                SharedPreferences sp = getActivity().getSharedPreferences("treeData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("str_Tree", str_tree);
                editor.apply();


                //使用gson解析json数据
                //new一个Gson对象
                Gson gson = new Gson();
                //将json字符串转为dataBean对象
                OptionBean optionBean = gson.fromJson(str_tree, OptionBean.class);
                Log.d("huangchixingcc", optionBean.getData().getDeviceType().get(0).getName());
                Log.d("huangchixingcc", optionBean.getData().getPhaseType().get(0).getName());
                Log.d("huangchixing2", optionBean.getData().getCities().get(0).getName());
                Log.d("huangchixing2", optionBean.getData().getCities().get(1).getName());

                //存入数据库,每次清除上一次数据
//                SQLhelper_Device.Instance(getActivity()).clearOptionInfo();
                //网络请求到的数据写入数据库
                SQLhelper_Device.Instance(getActivity()).insertOptionInfo(optionBean); //如何传参？


                //存入数据库的另一个表

            }
        });

    }

    //网络异常
    private void initNoInternetView() {
        if (rlNoData == null || llContent == null) return;
        llContent.setVisibility(View.GONE);
        rlNoData.setVisibility(View.VISIBLE);
    }


//    private void ParseJsonWithGson(String json){
//        //new一个Gson对象
//        Gson gson = new Gson();
//        //将json字符串转为dataBean对象
//        DeviceBean deviceBean= gson.fromJson(json, DeviceBean.class);
//        Log.d("COCKPITACTIVITY", "DEVICE ID IS " + deviceBean.getData().get(0).getDeviceName());
//        Log.d("COCKPITACTIVITY", "DEVICE ID IS " + deviceBean.getData().get(1).getDeviceId());
//        Log.d("COCKPITACTIVITY", "------数据解析成功------" + deviceBean.getData());
//
//    }


    //计数的方法，返回list
    private ArrayList<Integer> countData(List<DataBean> data) {
        int install = 0;
        int online = 0;
        int usable = 0;
        int sim = 0;
        int syn = 0;
        int power = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getInstalled()) {
                install++;
                System.out.println("安装数量 " + install);
            }
            if (data.get(i).getOnline()) {
                online++;
                System.out.println("在线数量 " + online);
            }
            if (data.get(i).getUsable()) {
                usable++;
                System.out.println("可用数量 " + usable);
            }
            if (data.get(i).getSIMCardOnline()) {
                sim++;
                System.out.println("sim卡在线数量 " + sim);
            }
            if (data.get(i).getAbnormal()) {
                syn++;
                System.out.println("功能异常数量 " + syn);
            }
            if (data.get(i).getPowerFailure()) {
                power++;
                System.out.println("停电数量 " + power);
            }
        }
        list.add(install);
        list.add(online);
        list.add(usable);
        list.add(sim);
        list.add(syn);
        list.add(power);
        return list;
    }

    private List<CockpitGridViewItem> getData() {
        List<CockpitGridViewItem> list = new ArrayList<>();
        CockpitGridViewItem item = new CockpitGridViewItem();
        item.setNum("0");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_install_num));
        list.add(item);
        item = new CockpitGridViewItem();
        item.setNum("0");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_online_num));
        list.add(item);
        item = new CockpitGridViewItem();
        item.setNum("0");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_usable_num));
        list.add(item);
        item = new CockpitGridViewItem();
        item.setNum("0");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_sim_arrearage));
        list.add(item);
        item = new CockpitGridViewItem();
        item.setNum("0");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_dysfunction));
        list.add(item);
        item = new CockpitGridViewItem();
        item.setNum("0");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_power_cut));
        list.add(item);
        return list;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cockpit_install_num_rl:
                toDetailList(Constans.INSTALL_NUM, getResources().getString(R.string.cet_cockpit_install_num), 1002);
                break;
            case R.id.cockpit_online_num_rl:
                toDetailList(Constans.ONLINE_NUM, getResources().getString(R.string.cet_cockpit_online_num), 1002);
                break;
            case R.id.cockpit_usable_num_rl:
                toDetailList(Constans.USABLE_NUM, getResources().getString(R.string.cet_cockpit_usable_num), 1002);
                break;
            case R.id.cockpit_sim_rl:
                toDetailList(Constans.SIM_NUM, getResources().getString(R.string.cet_cockpit_sim_arrearage), 1002);
                break;
            case R.id.cockpit_dysfunction_rl:
                toDetailList(Constans.EXCEPT_NUM, getResources().getString(R.string.cet_cockpit_dysfunction), 1002);
                break;
            case R.id.cockpit_power_cut_rl:
                toDetailList(Constans.POWEROFF_NUM, getResources().getString(R.string.cet_cockpit_power_cut), 1002);
                break;
            default:
                break;
        }
    }

    private void toDetailList(int sourceFlag, String title, int requestCode) {
        Intent power_intent = new Intent();
        power_intent.putExtra("source_flag", sourceFlag);
        Log.d("huangchixing33", "===============");
        Log.d("huangchixing33", sourceFlag + "");
        power_intent.putExtra("title", title);
        power_intent.setClass(getActivity(), MapViewActivity.class);
        startActivityForResult(power_intent, requestCode);
    }
}
