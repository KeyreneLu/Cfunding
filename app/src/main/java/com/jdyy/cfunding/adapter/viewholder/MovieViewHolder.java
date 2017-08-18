package com.jdyy.cfunding.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.bean.Person;
import com.jdyy.cfunding.recyclerView.adapter.BaseViewHolder;

/**
 * 影视众筹项目适配器
 * Created by Administrator on 2016/11/25 0025.
 */

public class MovieViewHolder extends BaseViewHolder<Person> {
    private TextView mTv_name;
    private TextView mTv_sign;

    public MovieViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_movie);
        mTv_name = $(R.id.tv_movie_name);
        mTv_sign = $(R.id.tv_movie_person);
    }

    @Override
    public void setData(Person data) {
        mTv_name.setText(data.getName());
        mTv_sign.setText(data.getSign());
    }
}
