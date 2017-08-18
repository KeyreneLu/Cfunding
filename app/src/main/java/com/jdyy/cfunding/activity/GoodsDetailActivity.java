package com.jdyy.cfunding.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.view.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class GoodsDetailActivity extends BaseActivity {
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.iv_home_left)
    ImageView mIvHomeLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.iv_goods_picture)
    RoundImageView mIvGoodsPicture;
    @Bind(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @Bind(R.id.tv_goods_price)
    TextView mTvGoodsPrice;
    @Bind(R.id.tv_goods_spec)
    TextView mTvGoodsSpec;
    @Bind(R.id.tv_goods_describe)
    TextView mTvGoodsDescribe;
    @Bind(R.id.tv_goods_fare)
    TextView mTvGoodsFare;
    @Bind(R.id.tv_goods_time)
    TextView mTvGoodsTime;
    @Bind(R.id.tv_invite_investment)
    TextView mTvInviteInvestment;
    @Bind(R.id.tv_investment_together)
    TextView mTvInvestmentTogether;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetail);
        ButterKnife.bind(this);
    }
}
