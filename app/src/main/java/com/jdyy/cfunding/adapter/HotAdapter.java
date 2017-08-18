package com.jdyy.cfunding.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jdyy.cfunding.bean.Item;
import com.jdyy.cfunding.recyclerView.adapter.BaseViewHolder;
import com.jdyy.cfunding.recyclerView.adapter.RecyclerArrayAdapter;
import com.jdyy.cfunding.adapter.viewholder.HotViewHolder;


/**
 * Created by Mr.Jude on 2015/7/18.
 */
public class HotAdapter extends RecyclerArrayAdapter<Item> {

    public HotAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotViewHolder(parent);
    }
}
