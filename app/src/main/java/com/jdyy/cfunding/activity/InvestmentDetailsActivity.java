package com.jdyy.cfunding.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class InvestmentDetailsActivity extends BaseActivity {
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
    @Bind(R.id.iv_invest_picture)
    ImageView mIvInvestPicture;
    @Bind(R.id.tv_invest_name)
    TextView mTvInvestName;
    @Bind(R.id.btn_reduce)
    Button mBtnReduce;
    @Bind(R.id.rl_reduce)
    RelativeLayout mRlReduce;
    @Bind(R.id.et_invest_money)
    EditText mEtInvestMoney;
    @Bind(R.id.btn_add)
    Button mBtnAdd;
    @Bind(R.id.rl_add)
    RelativeLayout mRlAdd;
    @Bind(R.id.tv_invest_name2)
    TextView mTvInvestName2;
    @Bind(R.id.tv_invest_date)
    TextView mTvInvestDate;
    @Bind(R.id.tv_invest_percent)
    TextView mTvInvestPercent;
    @Bind(R.id.tv_invest_cash)
    TextView mTvInvestCash;
    @Bind(R.id.iv_cash)
    ImageView mIvCash;
    @Bind(R.id.tv_user_money)
    TextView mTvUserMoney;
    @Bind(R.id.tv_invest_number)
    TextView mTvInvestNumber;
    @Bind(R.id.iv_xieyi)
    ImageView mIvXieyi;
    @Bind(R.id.tv_xieyi)
    TextView mTvXieyi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investmentdetails);
        ButterKnife.bind(this);
    }
}
