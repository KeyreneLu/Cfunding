package com.jdyy.cfunding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.bean.Invest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class SalesAdapter extends BaseAdapter {
    private Context mContext;
    List<Invest> data = new ArrayList<>();
    public SalesAdapter(Context context, List<Invest> data) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sales,null,false);
            holder.mTvSalePercent = (TextView) convertView.findViewById(R.id.tv_sale_percent);
            holder.mTvSaleMoney = (TextView) convertView.findViewById(R.id.tv_sale_money);
            holder.mIvSaleState = (ImageView) convertView.findViewById(R.id.iv_sale_state);
            if (position == data.size()-1) {
                holder.mIvSaleState.setVisibility(View.VISIBLE);
                holder.mTvSalePercent.setTextColor(mContext.getResources().getColor(R.color.text_hot_color));
            } else {
                holder.mIvSaleState.setVisibility(View.INVISIBLE);
                holder.mTvSalePercent.setTextColor(mContext.getResources().getColor(R.color.hot_red));
            }
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
            if (position == data.size()-1) {
                holder.mIvSaleState.setVisibility(View.VISIBLE);
                holder.mTvSalePercent.setTextColor(mContext.getResources().getColor(R.color.text_hot_color));
            } else {
                holder.mIvSaleState.setVisibility(View.INVISIBLE);
                holder.mTvSalePercent.setTextColor(mContext.getResources().getColor(R.color.hot_red));
            }
        }
        Invest i = data.get(position);
        holder.mTvSaleMoney.setText(i.getMoney()+"以上（含"+i.getMoney()+")");
        if (position == data.size()-1){
            holder.mTvSalePercent.setText("保本");
        }else {
            holder.mTvSalePercent.setText(i.getPercent());
        }
        return convertView;
    }

    class ViewHolder{
        TextView mTvSaleMoney,mTvSalePercent;
        ImageView mIvSaleState;
    }
}
