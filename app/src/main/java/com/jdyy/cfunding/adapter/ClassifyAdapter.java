package com.jdyy.cfunding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.bean.Classify;

import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class ClassifyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Classify> data;

    public ClassifyAdapter(Context context, List<Classify> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder = null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pupowindow,null,false);
            holder.mTvClassify = (TextView) convertView.findViewById(R.id.tv_item_popupwindow);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTvClassify.setText(data.get(position).getName());
        return convertView;
    }

    class ViewHolder{
        TextView mTvClassify;
    }
}
