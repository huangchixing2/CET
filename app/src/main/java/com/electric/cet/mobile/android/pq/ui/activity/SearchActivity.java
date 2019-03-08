package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.db.SQLhelper_Device;
import com.electric.cet.mobile.android.pq.ui.adapter.SearchListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity implements AdapterView.OnItemClickListener {
    private EditText search_et;
    private ListView listView;
    private SearchListAdapter adapter;
    private List<String> list = new ArrayList<>();
    private List<DataBean> dataBeans = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
    }

    private void initView() {
        search_et = (EditText) findViewById(R.id.cet_search_et);
        listView = (ListView) findViewById(R.id.search_list);
    }

    private void initData() {
        adapter = new SearchListAdapter(SearchActivity.this, list);
        listView.setAdapter(adapter);
        search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dataBeans = SQLhelper_Device.Instance(SearchActivity.this).queryDeviceListByName(s.toString());
                refreshList(dataBeans);
            }
        });
        listView.setOnItemClickListener(this);
    }

    private void refreshList(List<DataBean> dataBeans) {
        if (dataBeans != null) {
            list.clear();
            for (int i = 0; i < dataBeans.size(); i++) {
                list.add(dataBeans.get(i).getDeviceName());
            }
            handler.sendEmptyMessage(100);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("deviceID",dataBeans.get(position).getDeviceId()+"");
        setResult(1001,intent);
        //返回选中的数据
        finish();
    }
}
