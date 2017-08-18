package com.jdyy.cfunding.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.recyclerView.EasyRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class MyFriendActivity extends BaseActivity {
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
    @Bind(R.id.tv_once_rebate)
    TextView mTvOnceRebate;
    @Bind(R.id.tv_register_number)
    TextView mTvRegisterNumber;
    @Bind(R.id.tv_real_number)
    TextView mTvRealNumber;
    @Bind(R.id.tv_invested_number)
    TextView mTvInvestedNumber;
    @Bind(R.id.tv_secong_rebate)
    TextView mTvSecongRebate;
    @Bind(R.id.tv_second_number)
    TextView mTvSecondNumber;
    @Bind(R.id.tv_second_real)
    TextView mTvSecondReal;
    @Bind(R.id.tv_second_invested)
    TextView mTvSecondInvested;
    @Bind(R.id.tv_reward_descript)
    TextView mTvRewardDescript;
    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfriend);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setToolbar1();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.statusColor);
        }
        mRlLeft.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.axzd);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }
}
