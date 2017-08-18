package com.jdyy.cfunding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.bean.SupportBean;
import com.jdyy.cfunding.utils.TextUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class ProductSupportAdapter extends BaseAdapter{

    private Context mContext;
    List<SupportBean> data;
    public ProductSupportAdapter(Context context, List<SupportBean> data) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_product_support,null,false);
            holder.mTvProductName = (TextView) convertView.findViewById(R.id.tv_support_name);
            holder.mTvProductSum = (TextView) convertView.findViewById(R.id.tv_support_sum);
            holder.mTvProductDate = (TextView) convertView.findViewById(R.id.tv_support_date);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        SupportBean s = data.get(position);
        holder.mTvProductName.setText(TextUtils.PhoneDeal(3,7,s.getMobile()));
        holder.mTvProductSum.setText(s.getOrder_amount());
        holder.mTvProductDate.setText(TextUtils.timesOne(s.getOrder_create_time()));
        return convertView;
    }

    class ViewHolder{
        TextView mTvProductName,mTvProductSum,mTvProductDate;
    }
}
