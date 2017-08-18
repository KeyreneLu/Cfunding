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
 * ----------Dragon be here!----------/
 *       ┏┓      ┏┓
 *     ┏┛┻━━━┛┻┓
 *     ┃              ┃
 *     ┃      ━      ┃
 *     ┃  ┳┛  ┗┳  ┃
 *     ┃              ┃
 *     ┃      ┻      ┃
 *     ┃              ┃
 *     ┗━┓      ┏━┛
 *         ┃      ┃神兽保佑
 *         ┃      ┃代码无BUG！
 *         ┃      ┗━━━┓
 *         ┃              ┣┓
 *         ┃              ┏┛
 *         ┗┓┓┏━┳┓┏┛
 *           ┃┫┫  ┃┫┫
 *           ┗┻┛  ┗┻┛
 * ━━━━━━神兽出没━━━━━━
 */
public class AccountInfoActivity extends BaseActivity {
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
    @Bind(R.id.tv_number)
    TextView mTvNumber;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_card)
    TextView mTvCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountinfo);
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
        mTitle.setText(R.string.zhxx);
        mIvHomeLeft.setVisibility(View.VISIBLE);
        mIvHomeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
