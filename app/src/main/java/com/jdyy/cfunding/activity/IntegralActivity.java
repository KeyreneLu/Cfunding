package com.jdyy.cfunding.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 积分充值界面
 * Created by Administrator on 2016/12/13 0013.
 */

public class IntegralActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.tv_surplus)
    TextView mTvSurplus;
    @Bind(R.id.tv_hundred_points)
    TextView mTvHundredPoints;
    @Bind(R.id.rl_hundred_points)
    RelativeLayout mRlHundredPoints;
    @Bind(R.id.tv_kilo_points)
    TextView mTvKiloPoints;
    @Bind(R.id.rl_kilo_points)
    RelativeLayout mRlKiloPoints;
    @Bind(R.id.tv_myriad)
    TextView mTvMyriad;
    @Bind(R.id.rl_myriad_points)
    RelativeLayout mRlMyriadPoints;
    @Bind(R.id.et_other_points)
    EditText mEtOtherPoints;
    @Bind(R.id.rl_other_points)
    RelativeLayout mRlOtherPoints;
    @Bind(R.id.tv_pay_money)
    TextView mTvPayMoney;
    @Bind(R.id.silver_choose)
    ImageButton mSilverChoose;
    @Bind(R.id.silver_protocol)
    TextView mSilverProtocol;
    @Bind(R.id.btn_sure_bug)
    Button mBtnSureBug;

    private int index = 1;
    private boolean IsChosen = true;
    private boolean isRead = false;
    //dialog里面的控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
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
        mTitle.setText(R.string.ybcz);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }

    private void setlistener() {
        mRlHundredPoints.setOnClickListener(this);
        mRlKiloPoints.setOnClickListener(this);
        mRlMyriadPoints.setOnClickListener(this);
        mRlOtherPoints.setOnClickListener(this);
        mIvHomeLeft.setOnClickListener(this);
        mSilverChoose.setOnClickListener(this);
        mBtnSureBug.setOnClickListener(this);
        mEtOtherPoints.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_hundred_points:
                IsChosen = true;
                mEtOtherPoints.setCursorVisible(false);
                index = 1;
                mEtOtherPoints.setText("");
                mTvPayMoney.setText("10元");
                hideSoftInputView();
                ChangeColor(index);
                break;
            case R.id.rl_kilo_points:
                IsChosen = true;
                mEtOtherPoints.setCursorVisible(false);
                index = 2;
                mEtOtherPoints.setText("");
                mTvPayMoney.setText("100元");
                hideSoftInputView();
                ChangeColor(index);
                break;
            case R.id.rl_myriad_points:
                IsChosen = true;
                mEtOtherPoints.setCursorVisible(false);
                index = 3;
                mEtOtherPoints.setText("");
                mTvPayMoney.setText("1000元");
                hideSoftInputView();
                ChangeColor(index);
                break;
            case R.id.et_other_points:
            case R.id.rl_other_points:
                if (IsChosen){
                    mTvPayMoney.setText("0"+"元");
                    IsChosen = false;
                }
                mEtOtherPoints.setCursorVisible(true);
                index = 4;
                ChangeColor(index);
                mEtOtherPoints.setFocusable(true);
                mEtOtherPoints.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!TextUtils.isEmpty(mEtOtherPoints.getText().toString())){
                            mTvPayMoney.setText(Float.valueOf(mEtOtherPoints.getText().toString())/10+"元");
                            IsChosen = true;
                        }else {
                            mTvPayMoney.setText("0"+"元");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.silver_choose:
                if (isRead){
                    mSilverChoose.setBackgroundResource(R.mipmap.icon_duigouh);
                    isRead =false;
                }else {
                    mSilverChoose.setBackgroundResource(R.mipmap.icon_duigou);
                    isRead = true;
                }
                break;
            case R.id.btn_sure_bug:
                if (IsChosen){
                    if (isRead){
                        goToActivity(PaymentDetailsActivity.class);
                    }else {
                        T.showShort(IntegralActivity.this,"协议尚未同意");
                    }
                }else {
                    T.showShort(IntegralActivity.this,"尚未填写充值金额");
                }
                break;
        }
    }

    private void ChangeColor(int index) {
        if (index == 1){
            mRlHundredPoints.setBackgroundResource(R.drawable.rect_blue2);
            mTvHundredPoints.setTextColor(getResources().getColor(R.color.blue));
            mRlKiloPoints.setBackgroundResource(R.drawable.rect_gray8);
            mTvKiloPoints.setTextColor(getResources().getColor(R.color.text_hot_color));
            mRlMyriadPoints.setBackgroundResource(R.drawable.rect_gray8);
            mTvMyriad.setTextColor(getResources().getColor(R.color.text_hot_color));
            mRlOtherPoints.setBackgroundResource(R.drawable.rect_gray8);
            mEtOtherPoints.setTextColor(getResources().getColor(R.color.text_hot_color));
        }else if (index == 2){
            mRlHundredPoints.setBackgroundResource(R.drawable.rect_gray8);
            mTvHundredPoints.setTextColor(getResources().getColor(R.color.text_hot_color));
            mRlKiloPoints.setBackgroundResource(R.drawable.rect_blue2);
            mTvKiloPoints.setTextColor(getResources().getColor(R.color.blue));
            mRlMyriadPoints.setBackgroundResource(R.drawable.rect_gray8);
            mTvMyriad.setTextColor(getResources().getColor(R.color.text_hot_color));
            mRlOtherPoints.setBackgroundResource(R.drawable.rect_gray8);
            mEtOtherPoints.setTextColor(getResources().getColor(R.color.text_hot_color));
        }else if (index == 3){
            mRlHundredPoints.setBackgroundResource(R.drawable.rect_gray8);
            mTvHundredPoints.setTextColor(getResources().getColor(R.color.text_hot_color));
            mRlKiloPoints.setBackgroundResource(R.drawable.rect_gray8);
            mTvKiloPoints.setTextColor(getResources().getColor(R.color.text_hot_color));
            mRlMyriadPoints.setBackgroundResource(R.drawable.rect_blue2);
            mTvMyriad.setTextColor(getResources().getColor(R.color.blue));
            mRlOtherPoints.setBackgroundResource(R.drawable.rect_gray8);
            mEtOtherPoints.setTextColor(getResources().getColor(R.color.text_hot_color));
        }else if (index == 4){
            mRlHundredPoints.setBackgroundResource(R.drawable.rect_gray8);
            mTvHundredPoints.setTextColor(getResources().getColor(R.color.text_hot_color));
            mRlKiloPoints.setBackgroundResource(R.drawable.rect_gray8);
            mTvKiloPoints.setTextColor(getResources().getColor(R.color.text_hot_color));
            mRlMyriadPoints.setBackgroundResource(R.drawable.rect_gray8);
            mTvMyriad.setTextColor(getResources().getColor(R.color.text_hot_color));
            mRlOtherPoints.setBackgroundResource(R.drawable.rect_blue2);
            mEtOtherPoints.setTextColor(getResources().getColor(R.color.blue));
        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
