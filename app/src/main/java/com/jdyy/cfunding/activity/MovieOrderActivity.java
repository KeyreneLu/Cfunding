package com.jdyy.cfunding.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class MovieOrderActivity extends BaseActivity {
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
    @Bind(R.id.tv_movie_pay_state)
    TextView mTvMoviePayState;
    @Bind(R.id.iv_movie_pay_picture)
    ImageView mIvMoviePayPicture;
    @Bind(R.id.tv_movie_lucre_number)
    TextView mTvMovieLucreNumber;
    @Bind(R.id.tv_movie_lucre_time)
    TextView mTvMovieLucreTime;
    @Bind(R.id.tv_movie_lucre_percent)
    TextView mTvMovieLucrePercent;
    @Bind(R.id.tv_movie_lucre_money)
    TextView mTvMovieLucreMoney;
    @Bind(R.id.tv_movie_pay_sum)
    TextView mTvMoviePaySum;
    @Bind(R.id.tv_movie_pay_cash)
    TextView mTvMoviePayCash;
    @Bind(R.id.rl_lucre)
    RelativeLayout mRlLucre;
    @Bind(R.id.tv_all_pay_sum2)
    TextView mTvAllPaySum2;
    @Bind(R.id.rl_second_none)
    RelativeLayout mRlSecondNone;
    @Bind(R.id.tv_all_pay_sum)
    TextView mTvAllPaySum;
    @Bind(R.id.rl_first_none)
    RelativeLayout mRlFirstNone;
    @Bind(R.id.tv_pay_serial_number)
    TextView mTvPaySerialNumber;
    @Bind(R.id.tv_movie_pay_way)
    TextView mTvMoviePayWay;
    @Bind(R.id.tv_movie_pay_date)
    TextView mTvMoviePayDate;
    @Bind(R.id.rl_content_none)
    RelativeLayout mRlContentNone;
    @Bind(R.id.tv_order_cancel)
    TextView mTvOrderCancel;
    @Bind(R.id.tv_order_pay)
    TextView mTvOrderPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieorder);
        ButterKnife.bind(this);
    }
}
