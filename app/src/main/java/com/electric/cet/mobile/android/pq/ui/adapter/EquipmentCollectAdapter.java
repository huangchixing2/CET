package com.electric.cet.mobile.android.pq.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.utils.ChangeTypeUtil;

import java.util.List;

/**
 * Created by xushasha
 * use
 */
public class EquipmentCollectAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<DataBean> list;

    public EquipmentCollectAdapter(Context context, List<DataBean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.equipment_collect_adapter_item_layout, null);
            holder = new ViewHolder();
            holder.cb = (CheckBox) convertView.findViewById(R.id.equipment_collect_item_cb);
            holder.address = (TextView) convertView.findViewById(R.id.equipment_collect_item_address);
            holder.type = (TextView) convertView.findViewById(R.id.equipment_collect_item_type);
            holder.status = (ImageView) convertView.findViewById(R.id.equipment_collect_item_status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        是否被选中
        if(list.get(position).isSle()){
            holder.cb.setChecked(true);
        }else{
            holder.cb.setChecked(false);
        }
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setSle(isChecked);
            }
        });
        holder.address.setText(list.get(position).getCityId()+""+list.get(position).getCountyId()+""+list.get(position).getPowerSupplyId()+""+list.get(position).getDeviceName()+"");
        holder.type.setText(ChangeTypeUtil.changI2S(list.get(position).getDeviceTypeId()));
        if(list.get(position).getState()){
            holder.status.setImageResource(R.mipmap.equipment_online);
        }else{
            holder.status.setImageResource(R.mipmap.equipment_offline);
        }
        return convertView;
    }

    class ViewHolder{
        CheckBox cb;
        TextView address;
        TextView type;
        ImageView status;
    }
}
