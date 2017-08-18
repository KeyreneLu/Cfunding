package com.jdyy.cfunding.fragment;

import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.activity.CashActivity;
import com.jdyy.cfunding.activity.IntegralDetailActivity;
import com.jdyy.cfunding.activity.MyFriendActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class FindFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.rl_accumulate_points)
    RelativeLayout mRlAccumulatePoints;
    @Bind(R.id.rl_integral_mall)
    RelativeLayout mRlIntegralMall;
    @Bind(R.id.rl_network_reward)
    RelativeLayout mRlNetworkReward;
    @Bind(R.id.ll_lucky_turntable)
    LinearLayout mLlLuckyTurntable;
    @Bind(R.id.ll_gold_coin)
    LinearLayout mLlGoldCoin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }


    @Override
    protected void initView(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.statusColor);
        }
        mTitle.setText(R.string.find);
        setlistener();
    }

    @Override
    protected void loadData() {


    }

    private void setlistener() {
        mRlNetworkReward.setOnClickListener(this);
        mRlAccumulatePoints.setOnClickListener(this);
        mRlIntegralMall.setOnClickListener(this);
        mLlGoldCoin.setOnClickListener(this);
        mLlLuckyTurntable.setOnClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_accumulate_points:
                goToActivity(IntegralDetailActivity.class);
                break;
            case R.id.rl_integral_mall:
                goToActivity(CashActivity.class);
                break;
            case R.id.rl_network_reward:
                goToActivity(MyFriendActivity.class);
                break;
            case R.id.ll_gold_coin:
                break;
            case R.id.ll_lucky_turntable:
                break;
        }
    }
}
