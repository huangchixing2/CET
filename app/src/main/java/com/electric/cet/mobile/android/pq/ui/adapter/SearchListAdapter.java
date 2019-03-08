package com.electric.cet.mobile.android.pq.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.electric.cet.mobile.android.pq.R;

import java.util.List;

public class SearchListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<String> list;

    public SearchListAdapter(Context context,List<String> list){
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
            convertView = inflater.inflate(R.layout.activity_search_item, null);
            holder = new ViewHolder();
            holder.search_tv = (TextView) convertView.findViewById(R.id.search_item_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.search_tv.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView search_tv;
    }
}
