package com.electric.cet.mobile.android.pq.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.model.EquipmentWorkingModel;
import com.electric.cet.mobile.android.pq.ui.fragments.EquipmentFragment;

import java.util.List;

/**
 * Created by xushasha
 * use
 */
public class EquipmentWorkingAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<DataBean> list;
    public EquipmentWorkingAdapter(Context context, List<DataBean> list) {
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
            convertView = inflater.inflate(R.layout.equipment_working_adapter_item_layout, null);
            holder = new ViewHolder();
            holder.address = (TextView) convertView.findViewById(R.id.equipment_working_item_address);
            holder.type = (TextView) convertView.findViewById(R.id.equipment_working_item_type);
            holder.status = (ImageView) convertView.findViewById(R.id.equipment_working_item_status);
            holder.communication = (TextView) convertView.findViewById(R.id.equipment_working_item_communication);
            holder.sim = (TextView) convertView.findViewById(R.id.equipment_working_item_sim);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.address.setText(list.get(position).getCityId()+""+list.get(position).getCountyId()+""+list.get(position).getPowerSupplyId()+""+list.get(position).getDeviceName()+"");
        holder.type.setText(list.get(position).getDeviceTypeId()+"");  //此字段服务器未提供
        if(list.get(position).getState()){
            holder.status.setImageResource(R.mipmap.equipment_online);
        }else{
            holder.status.setImageResource(R.mipmap.equipment_offline);
        }
        holder.communication.setText(list.get(position).getOnline()?"在线":"退出");
        holder.sim.setText(list.get(position).getSIMCardOnline()?"正常":"异常");
        return convertView;
    }
    class ViewHolder{
        TextView address;
        TextView type;
        ImageView status;
        TextView communication;
        TextView sim;
    }
}
