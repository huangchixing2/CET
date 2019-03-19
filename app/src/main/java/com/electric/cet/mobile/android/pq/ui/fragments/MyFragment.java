package com.electric.cet.mobile.android.pq.ui.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.ui.activity.AccountManageActivity;
import com.electric.cet.mobile.android.pq.ui.activity.MyAboutActivity;
import com.electric.cet.mobile.android.pq.utils.Constans;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//我的
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout account_rl;
    private RelativeLayout abount_rl;
    private Button loginout_bt;
    private TextView name_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        account_rl = (RelativeLayout) view.findViewById(R.id.cet_my_account_mangage);
        abount_rl = (RelativeLayout) view.findViewById(R.id.cet_my_abount);
        loginout_bt = (Button) view.findViewById(R.id.cet_my_login_out);
        name_tv = (TextView) view.findViewById(R.id.cet_my_name);
        account_rl.setOnClickListener(this);
        abount_rl.setOnClickListener(this);
        loginout_bt.setOnClickListener(this);
    }

    private void initData() {

    }

    //退出登录请求GET
    public void logOut(final String token) {
        OkHttpClient client_option = new OkHttpClient();
        Request request = new Request.Builder().url(Constans.URL_LOGINOUT).get().build();
        client_option.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 无网络时候提示用户
                Looper.prepare();
                Toast.makeText(getActivity(), "无网络无法退出", Toast.LENGTH_SHORT).show();
                Looper.loop();
                Log.d("logout", "退出登录请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                SharedPreferences sp = getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.remove(token);
//                editor.apply();
                SharedPreferences sp;
                if (null!=getContext()){
                    sp = getContext().getSharedPreferences("TokenData", Context.MODE_PRIVATE);
                    sp.edit().clear().apply();
                }
                Log.d("logout","退出登录成功");
                getActivity().onBackPressed();
        }
    });

}

    private static String token;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cet_my_account_mangage:
                Intent accountIntent = new Intent();
                accountIntent.setClass(getActivity(), AccountManageActivity.class);
                startActivity(accountIntent);
                break;
            case R.id.cet_my_abount:
                Intent abountIntent = new Intent();
                abountIntent.setClass(getActivity(), MyAboutActivity.class);
                startActivity(abountIntent);
                break;
            case R.id.cet_my_login_out:
                //删除数据时提示用户，避免误操作
                dialog();
                break;
            default:
                break;
        }
    }

    //退出登录对话框提示用户
    private void dialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //删除
                logOut(token);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setMessage("确定要退出登录吗？");
        dialog.setTitle("提示");
        dialog.show();
    }

}
