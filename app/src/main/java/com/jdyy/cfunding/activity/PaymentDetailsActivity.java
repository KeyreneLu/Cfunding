package com.jdyy.cfunding.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class PaymentDetailsActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.tv_ten_minute)
    TextView mTvTenMinute;
    @Bind(R.id.tv_ge_minute)
    TextView mTvGeMinute;
    @Bind(R.id.tv_ten_second)
    TextView mTvTenSecond;
    @Bind(R.id.tv_ge_second)
    TextView mTvGeSecond;
    @Bind(R.id.tv_pay_sum)
    TextView mTvPaySum;
    @Bind(R.id.tv_wallet_sum)
    TextView mTvWalletSum;
    @Bind(R.id.iv_wallet_pay)
    ImageView mIvWalletPay;
    @Bind(R.id.rl_wallet_pay)
    RelativeLayout mRlWalletPay;
    @Bind(R.id.iv_card_pay)
    ImageView mIvCardPay;
    @Bind(R.id.rl_card_pay)
    RelativeLayout mRlCardPay;
    @Bind(R.id.iv_coin_pay)
    ImageView mIvCoinPay;
    @Bind(R.id.rl_coin_pay)
    RelativeLayout mRlCoinPay;
    @Bind(R.id.iv_weixin_pay)
    ImageView mIvWeixinPay;
    @Bind(R.id.rl_weixin_pay)
    RelativeLayout mRlWeixinPay;
    @Bind(R.id.btn_sure_pay)
    Button mBtnSurePay;

    private int index = 0;
    private CountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentdetails);
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
        mTitle.setText(R.string.zfxq);
        mIvHomeLeft.setVisibility(View.VISIBLE);

        mTimer = new CountDownTimer(900000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvTenMinute.setText((millisUntilFinished / 1000 / 60 / 10) + "");
                mTvGeMinute.setText((millisUntilFinished / 1000 / 60 % 10) + "");
                mTvTenSecond.setText((millisUntilFinished / 1000 % 60 / 10) + "");
                mTvGeSecond.setText((millisUntilFinished / 1000 % 60 % 10) + "");
            }
            @Override
            public void onFinish() {
                T.showShort(PaymentDetailsActivity.this, "支付失败，请重新下单!");
                PaymentDetailsActivity.this.finish();
            }
        }.start();
    }

    private void setlistener() {
        mRlWeixinPay.setOnClickListener(this);
        mIvWeixinPay.setOnClickListener(this);
        mRlWalletPay.setOnClickListener(this);
        mIvWalletPay.setOnClickListener(this);
        mRlCoinPay.setOnClickListener(this);
        mIvCoinPay.setOnClickListener(this);
        mRlCardPay.setOnClickListener(this);
        mIvCardPay.setOnClickListener(this);
        mBtnSurePay.setOnClickListener(this);
        mIvHomeLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_wallet_pay:
            case R.id.rl_wallet_pay:
                index = 1;
                changeIcon(index);
                break;
            case R.id.iv_card_pay:
            case R.id.rl_card_pay:
                index = 2;
                changeIcon(index);
                break;
            case R.id.iv_coin_pay:
            case R.id.rl_coin_pay:
                index = 3;
                changeIcon(index);
                break;
            case R.id.iv_weixin_pay:
            case R.id.rl_weixin_pay:
                index = 4;
                changeIcon(index);
                break;
            case R.id.iv_home_left:
                ShowDialog();
                break;
            case R.id.btn_sure_pay:
                if (index == 0) {
                    T.showShort(PaymentDetailsActivity.this, "请选择支付方式");
                } else if (index == 1) {

                } else if (index == 2) {

                } else if (index == 3) {

                } else if (index == 4) {

                }
                break;
        }
    }

    private void ShowDialog() {
        final Dialog alertDialog = new AlertDialog.Builder(PaymentDetailsActivity.this)
                .setTitle(R.string.ts)
                .setMessage(R.string.czswcg).setNegativeButton("继续支付", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确认放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PaymentDetailsActivity.this.finish();
                    }
                }).create();
        alertDialog.show();
    }

    private void changeIcon(int i) {
        if (i == 1) {
            mIvWalletPay.setImageResource(R.mipmap.icon_duigou);
            mIvCardPay.setImageResource(R.mipmap.icon_duigouh);
            mIvCoinPay.setImageResource(R.mipmap.icon_duigouh);
            mIvWeixinPay.setImageResource(R.mipmap.icon_duigouh);
        } else if (i == 2) {
            mIvWalletPay.setImageResource(R.mipmap.icon_duigouh);
            mIvCardPay.setImageResource(R.mipmap.icon_duigou);
            mIvCoinPay.setImageResource(R.mipmap.icon_duigouh);
            mIvWeixinPay.setImageResource(R.mipmap.icon_duigouh);
        } else if (i == 3) {
            mIvWalletPay.setImageResource(R.mipmap.icon_duigouh);
            mIvCardPay.setImageResource(R.mipmap.icon_duigouh);
            mIvCoinPay.setImageResource(R.mipmap.icon_duigou);
            mIvWeixinPay.setImageResource(R.mipmap.icon_duigouh);
        } else if (i == 4) {
            mIvWalletPay.setImageResource(R.mipmap.icon_duigouh);
            mIvCardPay.setImageResource(R.mipmap.icon_duigouh);
            mIvCoinPay.setImageResource(R.mipmap.icon_duigouh);
            mIvWeixinPay.setImageResource(R.mipmap.icon_duigou);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            ShowDialog();
        }
        return super.onKeyDown(keyCode, event);
    }
}
