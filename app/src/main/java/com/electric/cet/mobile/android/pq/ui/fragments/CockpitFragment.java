package com.electric.cet.mobile.android.pq.ui.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.model.CockpitGridViewItem;
import com.electric.cet.mobile.android.pq.ui.activity.MapViewActivity;
import com.electric.cet.mobile.android.pq.ui.adapter.CockpitGridviewAdapter;
import com.electric.cet.mobile.android.pq.utils.WebServiceTools;

import org.json.JSONArray;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// 驾驶舱
public class CockpitFragment extends BaseFragment implements View.OnClickListener {
    private GridView gridview;
    private RelativeLayout install_rl;
    private RelativeLayout online_rl;
    private RelativeLayout usable_rl;
    private RelativeLayout sim_rl;
    private RelativeLayout dysfunction_rl;
    private RelativeLayout power_rl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cockpit, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view){
        install_rl = (RelativeLayout) view.findViewById(R.id.cockpit_install_num_rl);
        online_rl = (RelativeLayout) view.findViewById(R.id.cockpit_online_num_rl);
        usable_rl = (RelativeLayout) view.findViewById(R.id.cockpit_usable_num_rl);
        sim_rl = (RelativeLayout) view.findViewById(R.id.cockpit_sim_rl);
        dysfunction_rl = (RelativeLayout) view.findViewById(R.id.cockpit_dysfunction_rl);
        power_rl = (RelativeLayout) view.findViewById(R.id.cockpit_power_cut_rl);
        install_rl.setOnClickListener(this);
        online_rl.setOnClickListener(this);
        usable_rl.setOnClickListener(this);
        sim_rl.setOnClickListener(this);
        dysfunction_rl.setOnClickListener(this);
        power_rl.setOnClickListener(this);
        gridview = (GridView) view.findViewById(R.id.cockpit_gridview);
        CockpitGridviewAdapter adapter = new CockpitGridviewAdapter(getActivity(),getData());
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),MapViewActivity.class);
                startActivityForResult(intent,1002);
            }
        });
    }

    /**
     *  显示数据
     */
    private void initData(){
        QueryAddressTask queryAddressTask = new QueryAddressTask();
        queryAddressTask.execute();
    }

    class QueryAddressTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            // 查询手机号码（段）信息*/
            try {
                WebServiceTools.getWether();

            } catch (Exception e) {
                e.printStackTrace();
            }
            //将结果返回给onPostExecute方法
            return "";
        }

        @Override
        //此方法可以在主线程改变UI
        protected void onPostExecute(String result) {
            // 将WebService返回的结果显示在TextView中
            //resultView.setText(result);
        }
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cockpit_install_num_rl:
                Intent install_intent = new Intent();
                install_intent.setClass(getActivity(),MapViewActivity.class);
                install_intent.putExtra("title",getResources().getString(R.string.cet_cockpit_install_num));
                startActivityForResult(install_intent,1002);
                break;
            case R.id.cockpit_online_num_rl:
                Intent online_intent = new Intent();
                online_intent.putExtra("title",getResources().getString(R.string.cet_cockpit_online_num));
                online_intent.setClass(getActivity(),MapViewActivity.class);
                startActivityForResult(online_intent,1002);
                break;
            case R.id.cockpit_usable_num_rl:
                Intent usable_intent = new Intent();
                usable_intent.putExtra("title",getResources().getString(R.string.cet_cockpit_usable_num));
                usable_intent.setClass(getActivity(),MapViewActivity.class);
                startActivityForResult(usable_intent,1002);
                break;
            case R.id.cockpit_sim_rl:
                Intent sim_intent = new Intent();
                sim_intent.putExtra("title",getResources().getString(R.string.cet_cockpit_sim_arrearage));
                sim_intent.setClass(getActivity(),MapViewActivity.class);
                startActivityForResult(sim_intent,1002);
                break;
            case R.id.cockpit_dysfunction_rl:
                Intent dysfunction_intent = new Intent();
                dysfunction_intent.putExtra("title",getResources().getString(R.string.cet_cockpit_dysfunction));
                dysfunction_intent.setClass(getActivity(),MapViewActivity.class);
                startActivityForResult(dysfunction_intent,1002);
                break;
            case R.id.cockpit_power_cut_rl:
                Intent power_intent = new Intent();
                power_intent.putExtra("title",getResources().getString(R.string.cet_cockpit_power_cut));
                power_intent.setClass(getActivity(),MapViewActivity.class);
                startActivityForResult(power_intent,1002);
                break;
                default:
                    break;
        }
    }
}
