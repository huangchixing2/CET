package com.electric.cet.mobile.android.pq.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.ui.fragments.CockpitFragment;
import com.electric.cet.mobile.android.pq.ui.fragments.DataFragment;
import com.electric.cet.mobile.android.pq.ui.fragments.EquipmentFragment;
import com.electric.cet.mobile.android.pq.ui.fragments.MyFragment;
import com.electric.cet.mobile.android.pq.utils.Constans;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener {
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private int currentFragPos = 0;
    private RadioGroup mRadioGroup;
    private RadioButton cockpitRb;
    private RadioButton dataRb;
    private RadioButton equipmentRb;
    private RadioButton myRb;
    private RadioButton selectRb;


    private DataFragment data_fragment;
    private EquipmentFragment equipment_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initData();
        initView();
        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);
    }

    private void initFragments(){
        //添加驾驶舱，数据，设备，我的
        fragments.add(new CockpitFragment());
        data_fragment = new DataFragment();
        fragments.add(data_fragment);
        equipment_fragment = new EquipmentFragment();
        fragments.add(equipment_fragment);
        fragments.add(new MyFragment());
    }

    private void initData(){

    }

    private void initView(){
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radio);
        cockpitRb = (RadioButton) findViewById(R.id.main_cockpit_tab);
        dataRb = (RadioButton) findViewById(R.id.main_data_tab);
        equipmentRb = (RadioButton) findViewById(R.id.main_equipment_tab);
        myRb = (RadioButton) findViewById(R.id.main_my_tab);
        selectRb = cockpitRb;
        // 显示第一个tab
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content, fragments.get(0),
                        fragments.get(0).getClass().getName())
                .commitAllowingStateLoss();

        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_cockpit_tab:
                selectRb = cockpitRb;
                currentFragPos = 0;
                onTabchecked(0);
                break;
            case R.id.main_data_tab:
                selectRb = dataRb;
                currentFragPos = 1;
                onTabchecked(1);
                break;
            case R.id.main_equipment_tab:
                selectRb = equipmentRb;
                currentFragPos = 2;
                onTabchecked(2);
                break;
            case R.id.main_my_tab:
                selectRb = myRb;
                currentFragPos = 3;
                onTabchecked(3);
                break;
            default:
                break;
        }
    }

    /**
     * 切换tab
     *
     * @param pos
     *            tab的位置
     */
    private void onTabchecked(int pos) {
        Fragment fragment = fragments.get(pos);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            fragment.onResume();
        } else {
            ft.add(R.id.main_content, fragment, fragment.getClass().getName());
        }
        showTab(pos);
        ft.commitAllowingStateLoss();
    }

    /**
     * 显示指定的tab，隐藏其他tab
     *
     * @param pos
     */
    private void showTab(int pos) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            if (pos == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commitAllowingStateLoss();
        }
    }

    //onActivityResult方法用于启动指定 Activity，而且期望获取指定 Activity 返回的结果。
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("MainActivity","requestCode->"+requestCode+"resultCode->"+resultCode);
//        if(data == null){
//            Log.i("devicesId",data.getIntExtra("devicesId",-1)+"null");
//        }
//        Log.i("MainActivity","search --" +data.getStringExtra("deviceID"));
        //如果数据为空，直接返回
        if(data == null){
            Log.e("MainActivity","data-> null");
            return;
        }
        if(resultCode == 1002){
            dataRb.setChecked(true);
            data_fragment.getHandler().sendEmptyMessage(1002);
//            Handler datahandler = data_fragment.getHandler();
//            Message message = datahandler.obtainMessage();
//            message.what = 1002;
//            message.getData().putInt("devicesId",data.getIntExtra("devicesId",-1));
//            Log.i("devicesId",data.getIntExtra("devicesId",-1)+"mainactivity");
//            datahandler.sendMessage(message);
//            data_fragment.getHandler().sendEmptyMessage(1002);
            ;
        } else if(resultCode == Constans.COUNT_CODE){
            Message message = data_fragment.getHandler().obtainMessage();
            message.what = Constans.COUNT_CODE;
            message.getData().putString("deviceID",data.getStringExtra("deviceID"));
            data_fragment.getHandler().sendMessage(message);
        }else if(resultCode == Constans.REALTIME_CODE){
            Message message = data_fragment.getHandler().obtainMessage();
            message.what = Constans.REALTIME_CODE;
            message.getData().putString("deviceID",data.getStringExtra("deviceID"));
            data_fragment.getHandler().sendMessage(message);
        }else if(resultCode == Constans.TREND_CODE){
            Message message = data_fragment.getHandler().obtainMessage();
            message.what = Constans.TREND_CODE;
            message.getData().putString("deviceID",data.getStringExtra("deviceID"));
            data_fragment.getHandler().sendMessage(message);
        }
        else if (requestCode == Constans.COLLECT_CODE){
            Message message = equipment_fragment.getHandler().obtainMessage();
            message.what = Constans.COLLECT_CODE;
            message.getData().putString("deviceID", data.getStringExtra("deviceID"));
        }
        else if (requestCode == Constans.WORKING_CODE){
            Message message = equipment_fragment.getHandler().obtainMessage();
            message.what = Constans.WORKING_CODE;
            message.getData().putString("deviceID", data.getStringExtra("deviceID"));
        }
    }
}
