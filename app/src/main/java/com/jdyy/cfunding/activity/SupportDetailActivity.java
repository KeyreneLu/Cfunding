package com.jdyy.cfunding.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.view.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

import static java.lang.Integer.parseInt;

/**
 * 商品购买界面
 * Created by Administrator on 2016/12/6 0006.
 */

public class SupportDetailActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.iv_product_pic)
    RoundImageView mIvProductPic;
    @Bind(R.id.tv_product_name)
    TextView mTvProductName;
    @Bind(R.id.tv_product_price)
    TextView mTvProductPrice;
    @Bind(R.id.tv_product_content)
    TextView mTvProductContent;
    @Bind(R.id.iv_add)
    ImageButton mIvAdd;
    @Bind(R.id.et_product_number)
    EditText mEtProductNumber;
    @Bind(R.id.iv_reduce)
    ImageButton mIvReduce;
    @Bind(R.id.seek_bar)
    SeekBar mSeekBar;
    @Bind(R.id.tv_pick_number)
    TextView mTvPickNumber;
    @Bind(R.id.tv_sell_number)
    TextView mTvSellNumber;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_user_phone)
    TextView mTvUserPhone;
    @Bind(R.id.tv_user_address)
    TextView mTvUserAddress;
    @Bind(R.id.tv_mail_way)
    TextView mTvMailWay;
    @Bind(R.id.tv_user_money)
    TextView mTvUserMoney;
    @Bind(R.id.tv_user_number)
    TextView mTvUserNumber;
    @Bind(R.id.rl_pick_detail)
    RelativeLayout mRlPickDetail;
    @Bind(R.id.tv_sell_time)
    TextView mTvSellTime;
    @Bind(R.id.tv_yield_rate)
    TextView mTvYieldRate;
    @Bind(R.id.tv_yield_money)
    TextView mTvYieldMoney;
    @Bind(R.id.tv_user_yield_money)
    TextView mTvUserYieldMoney;
    @Bind(R.id.tv_sell_yield_number)
    TextView mTvSellYieldNumber;
    @Bind(R.id.rl_sell_detail)
    RelativeLayout mRlSellDetail;
    @Bind(R.id.iv_voucher_left)
    ImageView mIvVoucherLeft;
    @Bind(R.id.tv_voucher_money)
    TextView mTvVoucherMoney;
    @Bind(R.id.et_remark)
    EditText mEtRemark;
    @Bind(R.id.tv_pay_money_sum)
    TextView mTvPayMoneySum;
    @Bind(R.id.tv_pay_number)
    TextView mTvPayNumber;
    @Bind(R.id.sc_main_content)
    ScrollView mScMainContent;
    @Bind(R.id.tv_pay_sum)
    TextView mTvPaySum;
    @Bind(R.id.tv_place_order)
    TextView mTvPlaceOrder;
    @Bind(R.id.iv_address_left)
    ImageView mIvAddressLeft;
    @Bind(R.id.iv_date_left)
    ImageView mIvDateLeft;

    private String content;//用户输入的数字字符串
    private int mild;//用户输入的件数
    private ScaleAnimation mSAnimOut;//下单部分模块消失动画
    private ScaleAnimation mSAnimIn;//下单部分模块显示动画
    private boolean isChange = false;//seekbar的最大值是否改变

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supportdetail);
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
        mIvHomeLeft.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.qrdd);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int max = seekBar.getMax();
                if (progress == 0) {
                    mTvPickNumber.setText("0");
                    mTvSellNumber.setText(max + "");
                    hiddenView(mRlPickDetail);
                    if (max == 1) {
                        showView(mRlSellDetail);
                    }
                } else if (progress == max) {
                    mTvPickNumber.setText(max + "");
                    mTvSellNumber.setText("0");
                    hiddenView(mRlSellDetail);
                    if (max == 1) {
                        showView(mRlPickDetail);
                    }
                } else {
                    mTvPickNumber.setText(progress + "");
                    mTvSellNumber.setText((max - progress) + "");
                    if (!isChange) {
                        showView(mRlSellDetail);
                        showView(mRlPickDetail);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChange = false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mEtProductNumber.setSelection(mEtProductNumber.getText().length());
        mEtProductNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = mEtProductNumber.getText().toString();
                if (text.equals("")) {
                    mEtProductNumber.setHint("");
                    changeUI(1);
                } else {
                    isChange = true;
                    int page = parseInt(text);
                    changeUI(page);
                }
            }
        });

        mSAnimOut = new ScaleAnimation(1.0f, 0, 1.0f, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
        mSAnimOut.setDuration(500);
        mSAnimOut.setFillAfter(true);

        mSAnimIn = new ScaleAnimation(0, 1.0f, 0, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
        mSAnimIn.setDuration(500);
        mSAnimIn.setFillAfter(true);
    }


    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
        mIvReduce.setOnClickListener(this);
        mIvAddressLeft.setOnClickListener(this);
        mIvDateLeft.setOnClickListener(this);
        mIvVoucherLeft.setOnClickListener(this);
        mTvPlaceOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.iv_add:
                isChange = true;
                changePage(1);
                break;
            case R.id.iv_reduce:
                changePage(0);
                break;
            case R.id.iv_address_left:

                break;
            case R.id.iv_date_left:

                break;
            case R.id.iv_voucher_left:

                break;
            case R.id.tv_place_order:

                break;
        }
    }

    private void changePage(int i) {
        content = mEtProductNumber.getText().toString();
        if (content.equals("")) {
            mild = 1;
            mEtProductNumber.setText(mild + "");
            changeUI(mild);
            mEtProductNumber.setSelection(mEtProductNumber.getText().length());
        } else {
            mild = parseInt(content);
            mild = (i == 1) ? mild + 1 : mild - 1;
            if (mild == 0 || mild == 1) {
                mild = 1;
                mEtProductNumber.setText(mild + "");
                changeUI(mild);
                mEtProductNumber.setSelection(mEtProductNumber.getText().length());
            } else {
                mEtProductNumber.setText(mild + "");
                changeUI(mild);
                mEtProductNumber.setSelection(mEtProductNumber.getText().length());
            }
        }
    }

    private void changeUI(int index) {
        mSeekBar.setMax(index);
        mSeekBar.setProgress(index);
        mTvPickNumber.setText(index + "");
        mTvSellNumber.setText("0");
    }


    private void hiddenView(final RelativeLayout rl) {
        if (rl.getVisibility() == View.VISIBLE) {
            rl.startAnimation(mSAnimOut);
            mSAnimOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    rl.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    private void showView(RelativeLayout rl) {
        if (rl.getVisibility() == View.GONE) {
            rl.setVisibility(View.VISIBLE);
            rl.startAnimation(mSAnimIn);
        }
    }
}
