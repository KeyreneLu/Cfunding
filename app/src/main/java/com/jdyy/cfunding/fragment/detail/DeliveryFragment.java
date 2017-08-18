package com.jdyy.cfunding.fragment.detail;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.fragment.BaseFragment;
import com.jdyy.cfunding.view.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 日常问答界面
 * Created by Administrator on 2016/11/30 0030.
 */

public class DeliveryFragment extends BaseFragment {
    @Bind(R.id.iv_delivery_picture)
    RoundImageView mIvDeliveryPicture;
    @Bind(R.id.tv_delivery_name)
    TextView mTvDeliveryName;
    @Bind(R.id.tv_delivery_price)
    TextView mTvDeliveryPrice;
    @Bind(R.id.tv_delivery_spec)
    TextView mTvDeliverySpec;
    @Bind(R.id.tv_delivery_content)
    TextView mTvDeliveryContent;
    @Bind(R.id.tv_delicery_time)
    TextView mTvDeliceryTime;
    @Bind(R.id.tv_delivery_cost)
    TextView mTvDeliveryCost;
    @Bind(R.id.rl_main_content)
    RelativeLayout mRlMainContent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_delivery;
    }


    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
