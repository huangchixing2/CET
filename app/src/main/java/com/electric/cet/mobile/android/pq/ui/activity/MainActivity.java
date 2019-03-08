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
        fragments.add(new CockpitFragment());
        data_fragment = new DataFragment();
        fragments.add(data_fragment);
        fragments.add(new EquipmentFragment());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("MainActivity","requestCode->"+requestCode+"resultCode->"+resultCode);
//        if(data == null){
//            Log.i("devicesId",data.getIntExtra("devicesId",-1)+"null");
//        }
        Log.i("MainActivity","search --" +data.getStringExtra("deviceID"));
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
        } else if(resultCode == 1001){
            Message message = data_fragment.getHandler().obtainMessage();
            message.what = 1001;
            message.getData().putString("deviceID",data.getStringExtra("deviceID"));
            data_fragment.getHandler().sendMessage(message);
        }
    }
}
