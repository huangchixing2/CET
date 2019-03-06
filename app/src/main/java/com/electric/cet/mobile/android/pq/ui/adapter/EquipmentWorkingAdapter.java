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
//    private List<DataBean> list = new ArrayList<>();
    private List<EquipmentWorkingModel> list;
    public EquipmentWorkingAdapter(Context context, List<EquipmentWorkingModel> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    public EquipmentWorkingAdapter(EquipmentFragment equipmentFragment, List<DataBean> workingData) {

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
            holder.cb = (CheckBox) convertView.findViewById(R.id.equipment_working_item_cb);
            holder.address = (TextView) convertView.findViewById(R.id.equipment_working_item_address);
            holder.type = (TextView) convertView.findViewById(R.id.equipment_working_item_type);
            holder.statu = (ImageView) convertView.findViewById(R.id.equipment_working_item_statu);
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
        holder.address.setText(list.get(position).getAddress());
        holder.type.setText(list.get(position).getType());  //此字段服务器未提供
        if(list.get(position).isStatu()){
            holder.statu.setImageResource(R.mipmap.equipment_online);
        }else{
            holder.statu.setImageResource(R.mipmap.equipment_offline);
        }
        return convertView;
    }
    class ViewHolder{
        CheckBox cb;
        TextView address;
        TextView type;
        ImageView statu;
    }
}
