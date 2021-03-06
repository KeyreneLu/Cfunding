package com.jdyy.cfunding.activity;

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
 * Created by Administrator on 2016/12/9 0009.
 */

public class RechargeDetailActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.iv_right)
    ImageView mIvRight;
    @Bind(R.id.tv_right1)
    TextView mTvRight1;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.tv_balance)
    TextView mTvBalance;
    @Bind(R.id.progress1)
    View mProgress1;
    @Bind(R.id.iv_time2)
    ImageView mIvTime2;
    @Bind(R.id.progress2)
    View mProgress2;
    @Bind(R.id.tv_time2)
    TextView mTvTime2;
    @Bind(R.id.progress3)
    View mProgress3;
    @Bind(R.id.iv_time3)
    ImageView mIvTime3;
    @Bind(R.id.tv_time3)
    TextView mTvTime3;
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechargedetail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setToolbar1();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.statusColor);
        }
        mTitle.setText(R.string.jgxq);
        mTvRight1.setText(R.string.wc2);
    }
}
