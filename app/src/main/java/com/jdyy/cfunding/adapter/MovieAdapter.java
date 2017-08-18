package com.jdyy.cfunding.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jdyy.cfunding.bean.Person;
import com.jdyy.cfunding.recyclerView.adapter.BaseViewHolder;
import com.jdyy.cfunding.recyclerView.adapter.RecyclerArrayAdapter;
import com.jdyy.cfunding.adapter.viewholder.MovieViewHolder;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class MovieAdapter extends RecyclerArrayAdapter<Person>{

    public MovieAdapter(Context context) {
        super(context);
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(parent);
    }
}
