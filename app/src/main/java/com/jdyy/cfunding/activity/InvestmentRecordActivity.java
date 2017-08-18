package com.jdyy.cfunding.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.view.NoScrollViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/9 0009.
 */

public class InvestmentRecordActivity extends BaseActivity {


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
    @Bind(R.id.rb_product)
    RadioButton mRbProduct;
    @Bind(R.id.iv_goods_state)
    ImageView mIvGoodsState;
    @Bind(R.id.rl_product)
    RelativeLayout mRlProduct;
    @Bind(R.id.rb_movie)
    RadioButton mRbMovie;
    @Bind(R.id.iv_movie_state)
    ImageView mIvMovieState;
    @Bind(R.id.rl_movie)
    RelativeLayout mRlMovie;
    @Bind(R.id.rg_menu)
    RadioGroup mRgMenu;
    @Bind(R.id.id_tab_line)
    ImageView mIdTabLine;
    @Bind(R.id.noScrollView)
    NoScrollViewPager mNoScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investmentrecord);
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
        mTitle.setText(R.string.wdtz);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }
}
