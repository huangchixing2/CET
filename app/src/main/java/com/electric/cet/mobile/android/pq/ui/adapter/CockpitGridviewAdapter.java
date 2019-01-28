package com.electric.cet.mobile.android.pq.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.model.CockpitGridViewItem;

import java.util.List;

/**
 * Created by xushasha
 * use
 */
public class CockpitGridviewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<CockpitGridViewItem> list;

    public CockpitGridviewAdapter(Context context,List<CockpitGridViewItem> list){
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
        if(convertView == null){
            convertView = inflater.inflate(R.layout.cockpit_gridview_item, null);
            holder = new ViewHolder();
            holder.textView1 = (TextView) convertView.findViewById(R.id.gridview_text_item1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.gridview_text_item2);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(list.get(position).getNum());
        holder.textView2.setText(list.get(position).getTitle());
        return convertView;
    }

    class ViewHolder {
        TextView textView1;
        TextView textView2;
    }
}
