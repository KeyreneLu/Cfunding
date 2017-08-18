package com.jdyy.cfunding.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class ExchangeDetailsActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.cash_money)
    TextView mCashMoney;
    @Bind(R.id.tv_cash_number)
    TextView mTvCashNumber;
    @Bind(R.id.tv_deadline)
    TextView mTvDeadline;
    @Bind(R.id.rl_cash)
    RelativeLayout mRlCash;
    @Bind(R.id.iv_add)
    ImageButton mIvAdd;
    @Bind(R.id.et_cash_number)
    EditText mEtCashNumber;
    @Bind(R.id.iv_reduce)
    ImageButton mIvReduce;
    @Bind(R.id.tv_cash_pay_number)
    TextView mTvCashPayNumber;
    @Bind(R.id.tv_cash_existing)
    TextView mTvCashExisting;
    @Bind(R.id.tv_cash_state)
    TextView mTvCashState;
    @Bind(R.id.tv_cash_way)
    TextView mTvCashWay;
    @Bind(R.id.tv_cash_earning)
    TextView mTvCashEarning;
    @Bind(R.id.iv_cash_choose)
    ImageButton mIvCashChoose;
    @Bind(R.id.tv_cash_protocol)
    TextView mTvCashProtocol;
    @Bind(R.id.ll_cash_protocol)
    LinearLayout mLlCashProtocol;
    @Bind(R.id.btn_cash_sure)
    Button mBtnCashSure;

    private int index;
    private String content;
    private int mild;
    private boolean isRead = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchangedetails);
        ButterKnife.bind(this);
        index = getIntent().getIntExtra("index", 10);
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
        mTitle.setText(R.string.dhxq);
        mIvHomeLeft.setVisibility(View.VISIBLE);

        if (index == 50) {
            mRlCash.setBackgroundResource(R.mipmap.hd_red);
            mCashMoney.setText("50元");
            mTvCashNumber.setText("500");
            mTvCashPayNumber.setText("500");
        } else if (index == 20) {
            mRlCash.setBackgroundResource(R.mipmap.hd_orange);
            mCashMoney.setText("20元");
            mTvCashNumber.setText("200");
            mTvCashPayNumber.setText("200");
        } else {
            mRlCash.setBackgroundResource(R.mipmap.hd_blue);
            mCashMoney.setText("10元");
            mTvCashNumber.setText("100");
            mTvCashPayNumber.setText("100");
        }
        mEtCashNumber.setSelection(mEtCashNumber.getText().length());
        mEtCashNumber.addTextChangedListener(new TextWatcher() {
            int sum;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = mEtCashNumber.getText().toString();
                if (text.equals("")) {
                    mEtCashNumber.setHint("");
                    mTvCashPayNumber.setText("");
                } else {
                    int page = Integer.parseInt(text);
                    if (index == 50) {
                        sum = page * 500;
                        mTvCashPayNumber.setText(sum + "");
                    } else if (index == 20) {
                        sum = page * 200;
                        mTvCashPayNumber.setText(sum + "");
                    } else {
                        sum = page * 100;
                        mTvCashPayNumber.setText(sum + "");
                    }
                }
            }
        });
    }


    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
        mIvReduce.setOnClickListener(this);
        mIvCashChoose.setOnClickListener(this);
        mTvCashProtocol.setOnClickListener(this);
        mBtnCashSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.iv_add:
                changePage(1);
                break;
            case R.id.iv_reduce:
                changePage(0);
                break;
            case R.id.iv_cash_choose:
                if (isRead) {
                    mIvCashChoose.setBackgroundResource(R.mipmap.icon_duigouh);
                    isRead = false;
                } else {
                    mIvCashChoose.setBackgroundResource(R.mipmap.icon_duigou);
                    isRead = true;
                }
                break;
            case R.id.tv_cash_protocol:

                break;
            case R.id.btn_cash_sure:
                content = mEtCashNumber.getText().toString();
                if (content.equals("")) {
                    T.showShort(ExchangeDetailsActivity.this, "请输入购买代金券张数");
                } else {
                    if (isRead) {
                        goToActivity(PaymentDetailsActivity.class);
                    } else {
                        T.showShort(ExchangeDetailsActivity.this, "协议尚未同意");
                    }
                }
                break;
        }
    }

    private void changePage(int i) {
        content = mEtCashNumber.getText().toString();
        if (content.equals("")) {
            if (i == 1) {
                mild = 1;
                mEtCashNumber.setText(mild + "");
                mEtCashNumber.setSelection(mEtCashNumber.getText().length());
            }
        } else {
            mild = Integer.parseInt(content);
            mild = (i == 1) ? mild + 1 : mild - 1;
            if (mild == 0) {
                mEtCashNumber.setText("1");
            } else {
                mEtCashNumber.setText(mild + "");
            }
            mEtCashNumber.setSelection(mEtCashNumber.getText().length());
        }
    }
}
