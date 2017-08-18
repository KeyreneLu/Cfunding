package com.jdyy.cfunding.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class CashActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.tv_fifty_cash)
    TextView mTvFiftyCash;
    @Bind(R.id.tv_fifty_number)
    TextView mTvFiftyNumber;
    @Bind(R.id.iv_fifty_choose)
    ImageView mIvFiftyChoose;
    @Bind(R.id.tv_fifty_deadline)
    TextView mTvFiftyDeadline;
    @Bind(R.id.Rl_fifty_cash)
    RelativeLayout mRlFiftyCash;
    @Bind(R.id.tv_twenty_cash)
    TextView mTvTwentyCash;
    @Bind(R.id.tv_twenty_number)
    TextView mTvTwentyNumber;
    @Bind(R.id.iv_twenty_choose)
    ImageView mIvTwentyChoose;
    @Bind(R.id.tv_twenty_deadline)
    TextView mTvTwentyDeadline;
    @Bind(R.id.Rl_twenty_cash)
    RelativeLayout mRlTwentyCash;
    @Bind(R.id.tv_ten_cash)
    TextView mTvTenCash;
    @Bind(R.id.tv_ten_number)
    TextView mTvTenNumber;
    @Bind(R.id.iv_ten_choose)
    ImageView mIvTenChoose;
    @Bind(R.id.tv_ten_deadline)
    TextView mTvTenDeadline;
    @Bind(R.id.Rl_ten_cash)
    RelativeLayout mRlTenCash;
    @Bind(R.id.tv_right1)
    TextView mTvRight1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        ButterKnife.bind(this);
        initView();
        setlistener();
    }

    private void initView() {
        setToolbar1();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.statusColor);
        }
        mRlLeft.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.jfsc);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }

    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mTvRight1.setOnClickListener(this);
        mRlFiftyCash.setOnClickListener(this);
        mRlTwentyCash.setOnClickListener(this);
        mRlTenCash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.tv_right1:
                goToActivity(CashDeclareActivity.class);
                break;
            case R.id.Rl_fifty_cash:
                goNextActivity(50);
                break;
            case R.id.Rl_twenty_cash:
                goNextActivity(20);
                break;
            case R.id.Rl_ten_cash:
                goNextActivity(10);
                break;
        }
    }

    private void goNextActivity(int index) {
        Intent intent = new Intent(CashActivity.this,ExchangeDetailsActivity.class);
        intent.putExtra("index",index);
        startActivity(intent);
    }
}
