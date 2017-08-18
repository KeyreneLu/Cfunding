package com.jdyy.cfunding.fragment.detail;

import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.activity.GainCalculatorActivity;
import com.jdyy.cfunding.adapter.SalesAdapter;
import com.jdyy.cfunding.bean.Invest;
import com.jdyy.cfunding.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 委托销售界面
 * Created by Administrator on 2016/11/30 0030.
 */

public class SalesFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.lv_money_data)
    ListView mLvMoneyData;
    @Bind(R.id.rl_historical_rate)
    RelativeLayout mRlHistoricalRate;
    @Bind(R.id.rl_gain_calculator)
    RelativeLayout mRlGainCalculator;

    private SalesAdapter mAdapter;
    List<Invest> data;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sales;
    }


    @Override
    protected void initView(View view) {
        data = new ArrayList<>();
        for (int i=1;i<=5;i++){
            Invest in = new Invest(i+"00","20%");
            data.add(in);
        }

        mAdapter = new SalesAdapter(getContext(),data);
        mLvMoneyData.setAdapter(mAdapter);
        setlistener();
    }

    private void setlistener() {
        mRlGainCalculator.setOnClickListener(this);
        mRlHistoricalRate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_gain_calculator:
                goToActivity(GainCalculatorActivity.class);
                break;
            case R.id.rl_historical_rate:
                break;
        }
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
