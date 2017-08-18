package com.jdyy.cfunding.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.bean.Constant;
import com.jdyy.cfunding.bean.Item;
import com.jdyy.cfunding.recyclerView.adapter.BaseViewHolder;
import com.jdyy.cfunding.utils.DateUtils;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * 热门推荐适配器
 * Created by Administrator on 2016/11/25 0025.
 */
public class HotViewHolder extends BaseViewHolder<Item> {
    private TextView mTvHotName,mTvHotPercent,mTvHotPerson,mTvHotMoney,mTvHotTime;
    private ImageView mIvHotPicture,mIvHotFlag;

    public HotViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_hot);
        mTvHotName = $(R.id.tv_hot_name);
        mTvHotPercent= $(R.id.tv_hot_percent);
        mTvHotPerson = $(R.id.tv_hot_person);
        mTvHotMoney = $(R.id.tv_hot_money);
        mTvHotTime = $(R.id.tv_hot_time);
        mIvHotPicture = $(R.id.iv_hot_picture);
        mIvHotFlag = $(R.id.iv_hot_flag);
    }

    @Override
    public void setData(Item data) {
        if (data.getCatName().equals(Constant.WEN_CHUANG)){
            mIvHotFlag.setImageResource(R.mipmap.cp_wc);
        }else if (data.getCatName().equals(Constant.NONG_YE)){
            mIvHotFlag.setImageResource(R.mipmap.cp_ny);
        }else {
            mIvHotFlag.setImageResource(R.mipmap.cp_kj);
        }
        mTvHotName.setText(data.getName());
        mTvHotPerson.setText(data.getView());
        mTvHotMoney.setText(data.getRaising_money().split("[.]")[0]+"元");
        ImageLoader.getInstance().displayImage(Constant.Main_URL+data.getCover_img(),mIvHotPicture);
        mTvHotTime.setText(DateUtils.getIntervalDays(data.getEnd_time()));
    }
}
