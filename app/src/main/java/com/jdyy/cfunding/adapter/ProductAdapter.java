package com.jdyy.cfunding.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jdyy.cfunding.adapter.viewholder.ProductViewHolder;
import com.jdyy.cfunding.bean.Product;
import com.jdyy.cfunding.recyclerView.adapter.BaseViewHolder;
import com.jdyy.cfunding.recyclerView.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class ProductAdapter extends RecyclerArrayAdapter<Product> {

    public ProductAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductViewHolder(parent);
    }
}
