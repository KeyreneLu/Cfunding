package com.jdyy.cfunding.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.recyclerView.EasyRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ZAMA积分界面
 * Created by Administrator on 2016/12/13 0013.
 */

public class IntegralDetailActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.tv_integral_sum)
    TextView mTvIntegralSum;
    @Bind(R.id.btn_now_buy)
    Button mBtnNowBuy;
    @Bind(R.id.easyRecyclerView)
    EasyRecyclerView mEasyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integraldetail);
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
        mTitle.setText(R.string.wdjf);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }

    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mBtnNowBuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.btn_now_buy:
                goToActivity(IntegralActivity.class);
                break;
        }
    }
}
