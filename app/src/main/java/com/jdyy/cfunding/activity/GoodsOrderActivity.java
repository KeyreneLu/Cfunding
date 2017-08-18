package com.jdyy.cfunding.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.view.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class GoodsOrderActivity extends BaseActivity {
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
    @Bind(R.id.tv_goods_pay_state)
    TextView mTvGoodsPayState;
    @Bind(R.id.iv_goods_pay_picture)
    ImageView mIvGoodsPayPicture;
    @Bind(R.id.iv_goods_picture)
    RoundImageView mIvGoodsPicture;
    @Bind(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @Bind(R.id.tv_goods_price)
    TextView mTvGoodsPrice;
    @Bind(R.id.tv_goods_spec)
    TextView mTvGoodsSpec;
    @Bind(R.id.tv_goods_pay_number)
    TextView mTvGoodsPayNumber;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_user_phone)
    TextView mTvUserPhone;
    @Bind(R.id.iv_location)
    ImageView mIvLocation;
    @Bind(R.id.tv_user_address)
    TextView mTvUserAddress;
    @Bind(R.id.tv_goods_pay_name)
    TextView mTvGoodsPayName;
    @Bind(R.id.tv_goods_pay_price)
    TextView mTvGoodsPayPrice;
    @Bind(R.id.tv_goods_pay_spec)
    TextView mTvGoodsPaySpec;
    @Bind(R.id.rl_take_delivery)
    RelativeLayout mRlTakeDelivery;
    @Bind(R.id.tv_goods_lucre_number)
    TextView mTvGoodsLucreNumber;
    @Bind(R.id.tv_goods_lucre_time)
    TextView mTvGoodsLucreTime;
    @Bind(R.id.tv_goods_lucre_percent)
    TextView mTvGoodsLucrePercent;
    @Bind(R.id.tv_goods_lucre_money)
    TextView mTvGoodsLucreMoney;
    @Bind(R.id.tv_goods_pay_sum)
    TextView mTvGoodsPaySum;
    @Bind(R.id.tv_goods_pay_cash)
    TextView mTvGoodsPayCash;
    @Bind(R.id.rl_lucre)
    RelativeLayout mRlLucre;
    @Bind(R.id.tv_all_pay_sum2)
    TextView mTvAllPaySum2;
    @Bind(R.id.rl_second_none)
    RelativeLayout mRlSecondNone;
    @Bind(R.id.tv_all_pay_sum)
    TextView mTvAllPaySum;
    @Bind(R.id.rl_first_none)
    RelativeLayout mRlFirstNone;
    @Bind(R.id.tv_pay_serial_number)
    TextView mTvPaySerialNumber;
    @Bind(R.id.tv_goods_pay_way)
    TextView mTvGoodsPayWay;
    @Bind(R.id.tv_goods_pay_date)
    TextView mTvGoodsPayDate;
    @Bind(R.id.rl_content_none)
    RelativeLayout mRlContentNone;
    @Bind(R.id.sc_main_content)
    ScrollView mScMainContent;
    @Bind(R.id.tv_order_cancel)
    TextView mTvOrderCancel;
    @Bind(R.id.tv_order_pay)
    TextView mTvOrderPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsorder);
        ButterKnife.bind(this);
    }
}
