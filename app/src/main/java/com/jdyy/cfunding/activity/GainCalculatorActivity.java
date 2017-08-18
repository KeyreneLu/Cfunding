package com.jdyy.cfunding.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.view.OnRulerChangeListener;
import com.jdyy.cfunding.view.RulerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class GainCalculatorActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.tv_lucre_percent)
    TextView mTvLucrePercent;
    @Bind(R.id.tv_lucre_money)
    TextView mTvLucreMoney;
    @Bind(R.id.btn_reduce)
    Button mBtnReduce;
    @Bind(R.id.et_invest_money)
    EditText mEtInvestMoney;
    @Bind(R.id.btn_add)
    Button mBtnAdd;
    @Bind(R.id.tv_invest_money)
    TextView mTvInvestMoney;
    @Bind(R.id.rulerView)
    RulerView mRulerView;
    @Bind(R.id.tv_income_explain)
    TextView mTvIncomeExplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaincalculator);
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
        mTitle.setText(R.string.syjsq);
        mIvHomeLeft.setVisibility(View.VISIBLE);

    }

    private void setlistener() {

        mIvHomeLeft.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnReduce.setOnClickListener(this);
        mRulerView.setOnRulerChangeListener(new OnRulerChangeListener() {
            @Override
            public void onChanged(int newValue) {
                mTvInvestMoney.setText(newValue+"");
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
