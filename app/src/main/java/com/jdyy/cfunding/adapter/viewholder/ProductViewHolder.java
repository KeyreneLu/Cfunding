package com.jdyy.cfunding.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.bean.Constant;
import com.jdyy.cfunding.bean.Product;
import com.jdyy.cfunding.recyclerView.adapter.BaseViewHolder;
import com.jdyy.cfunding.utils.DateUtils;
import com.jdyy.cfunding.view.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 众筹项目适配器
 * Created by Administrator on 2016/11/24 0024.
 */

public class ProductViewHolder extends BaseViewHolder<Product> {
    private TextView mTvProductTitle,mTvProductName,mTvProductPercent,mTvProductProgress
            ,mTvProductPerson,mTvProductMoney,mTvProductTime;
    private RoundImageView mIvProductPicture;
    private ImageView mIvProductFlag;

    public ProductViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_product);
        mTvProductTitle = $(R.id.tv_product_title);
        mTvProductName = $(R.id.tv_product_name);
        mTvProductPercent = $(R.id.tv_product_percent);
        mTvProductProgress = $(R.id.tv_product_progress_percent);
        mTvProductPerson = $(R.id.tv_product_person);
        mTvProductMoney = $(R.id.tv_product_money);
        mTvProductTime = $(R.id.tv_product_time);
        mIvProductFlag = $(R.id.iv_product_flag);
        mIvProductPicture = $(R.id.iv_product_picture);
//        mTv_name = (TextView) parent.findViewById(R.id.person_name);
    }

    @Override
    public void setData(Product data) {
        if (data.getCatName().equals(Constant.WEN_CHUANG)){
            mIvProductFlag.setImageResource(R.mipmap.cp_wc);
        }else if (data.getCatName().equals(Constant.NONG_YE)){
            mIvProductFlag.setImageResource(R.mipmap.cp_ny);
        }else {
            mIvProductFlag.setImageResource(R.mipmap.cp_kj);
        }
        mTvProductName.setText(data.getDesc());
        mTvProductTitle.setText(data.getName());
        mTvProductPerson.setText(data.getView());
        mTvProductMoney.setText(data.getRaising_money().split("[.]")[0]+"元");
        ImageLoader.getInstance().displayImage(Constant.Main_URL+data.getCover_img(),mIvProductPicture);
        mTvProductTime.setText(DateUtils.getIntervalDays(data.getEnd_time()));
    }
}
