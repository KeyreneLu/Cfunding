package com.jdyy.cfunding.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class PersonalDataActivity extends BaseActivity {
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.iv_right)
    ImageView mIvRight;
    @Bind(R.id.tv_right1)
    TextView mTvRight1;
    @Bind(R.id.iv_home_left)
    ImageView mIvHomeLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.et_person_account)
    EditText mEtPersonAccount;
    @Bind(R.id.et_person_age)
    EditText mEtPersonAge;
    @Bind(R.id.et_personal_sex)
    EditText mEtPersonalSex;
    @Bind(R.id.btn_person_sure)
    Button mBtnPersonSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaldata);
        ButterKnife.bind(this);
    }
}
