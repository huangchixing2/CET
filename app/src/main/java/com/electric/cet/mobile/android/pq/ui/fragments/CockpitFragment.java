package com.electric.cet.mobile.android.pq.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.model.CockpitGridViewItem;
import com.electric.cet.mobile.android.pq.ui.activity.MapViewActivity;
import com.electric.cet.mobile.android.pq.ui.adapter.CockpitGridviewAdapter;

import java.util.ArrayList;
import java.util.List;

// 驾驶舱
public class CockpitFragment extends BaseFragment {
    private GridView gridview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cockpit, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view){
        gridview = (GridView) view.findViewById(R.id.cockpit_gridview);
        CockpitGridviewAdapter adapter = new CockpitGridviewAdapter(getActivity(),getData());
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),MapViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData(){

    }

    private List<CockpitGridViewItem> getData(){
        List<CockpitGridViewItem> list = new ArrayList<>();
        CockpitGridViewItem item = new CockpitGridViewItem();
        item.setNum("99");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_install_num));
        list.add(item);
        item = new CockpitGridViewItem();
        item.setNum("80");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_online_num));
        list.add(item);
        item = new CockpitGridViewItem();
        item.setNum("81");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_usable_num));
        list.add(item);
        item = new CockpitGridViewItem();
        item.setNum("10");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_sim_arrearage));
        list.add(item);
        item = new CockpitGridViewItem();
        item.setNum("20");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_dysfunction));
        list.add(item);
        item = new CockpitGridViewItem();
        item.setNum("10");
        item.setTitle(getActivity().getString(R.string.cet_cockpit_power_cut));
        list.add(item);
        return  list;
    }
}
