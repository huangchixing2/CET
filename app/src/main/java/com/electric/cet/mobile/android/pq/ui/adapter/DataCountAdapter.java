package com.electric.cet.mobile.android.pq.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.model.DataCountModel;

import java.util.List;

/**
 * Created by xushasha
 * use
 */
public class DataCountAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<DataCountModel> list;

    public DataCountAdapter(Context context, List<DataCountModel> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.data_count_adapter_item_layout, null);
            holder = new ViewHolder();
            holder.address = (TextView) convertView.findViewById(R.id.data_count_item_address);
            holder.num = (TextView) convertView.findViewById(R.id.data_count_item_num);
            holder.statu = (TextView) convertView.findViewById(R.id.data_count_item_statu);
            holder.svc = (TextView) convertView.findViewById(R.id.data_count_item_svc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.address.setText(list.get(position).getAddress());
        holder.num.setText(context.getResources().getString(R.string.cet_count_num,String.valueOf(list.get(position).getNum())));
        String vStatu = "";
        if(list.get(position).isStatu()){
            vStatu = context.getResources().getString(R.string.cet_count_nor);
        }else{
            vStatu = context.getResources().getString(R.string.cet_count_abnor);
            holder.statu.setTextColor(context.getResources().getColor(R.color.red));
        }
        holder.statu.setText(context.getResources().getString(R.string.cet_count_statu,vStatu));
        String svc = "";
        if(list.get(position).isSvc()){
            vStatu = context.getResources().getString(R.string.cet_count_nor);
        }else{
            vStatu = context.getResources().getString(R.string.cet_count_abnor);
            holder.svc.setTextColor(context.getResources().getColor(R.color.red));
        }
        holder.svc.setText(context.getResources().getString(R.string.cet_count_svc,vStatu));
        return convertView;
    }

    class ViewHolder {
        private TextView address;
        private TextView num;
        private TextView statu;
        private TextView svc;
    }
}
