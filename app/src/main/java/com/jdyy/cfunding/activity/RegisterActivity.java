package com.jdyy.cfunding.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.utils.T;
import com.jdyy.cfunding.utils.TimeCountUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注册界面
 * Created by Administrator on 2016/12/12 0012.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
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
    @Bind(R.id.et_register_account)
    EditText mEtRegisterAccount;
    @Bind(R.id.et_identifying_code)
    EditText mEtIdentifyingCode;
    @Bind(R.id.btn_identifying_code)
    Button mBtnIdentifyingCode;
    @Bind(R.id.et_user_password)
    EditText mEtUserPassword;
    @Bind(R.id.iv_clear)
    ImageView mIvClear;
    @Bind(R.id.saw)
    ImageView mSaw;
    @Bind(R.id.et_invitation_code)
    EditText mEtInvitationCode;
    @Bind(R.id.zc_choose)
    ImageButton mZcChoose;
    @Bind(R.id.zc_xieyi)
    TextView mZcXieyi;
    @Bind(R.id.btn_register)
    Button mBtnRegister;

    private boolean IsShow = false;
    private boolean IsRead = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        mTitle.setText(R.string.zc);
        mIvHomeLeft.setVisibility(View.VISIBLE);
        mBtnRegister.setEnabled(false);
    }

    private void setlistener() {
        mBtnIdentifyingCode.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        mIvClear.setOnClickListener(this);
        mSaw.setOnClickListener(this);
        mZcChoose.setOnClickListener(this);
        mZcXieyi.setOnClickListener(this);
        mIvHomeLeft.setOnClickListener(this);

        mEtRegisterAccount.addTextChangedListener(this);
        mEtUserPassword.addTextChangedListener(this);
        mEtIdentifyingCode.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.btn_identifying_code:
                //然后在需要用这个的方法里new一个对象，然后调用start();方法就可以啦
                TimeCountUtils timeCountUtils = new TimeCountUtils(RegisterActivity.this, 60000, 1000, mBtnIdentifyingCode);
                timeCountUtils.start();
                break;
            case R.id.btn_register:
                String userPassword = mEtUserPassword.getText().toString();
                if (userPassword.length() < 6) {
                    T.showShort(RegisterActivity.this, "密码长度不够");
                } else if (!com.jdyy.cfunding.utils.TextUtils.isLetterDigit(userPassword)) {
                    T.showShort(RegisterActivity.this, "密码必须包含数字、字母符号");
                } else if (!IsRead) {
                    T.showShort(RegisterActivity.this, "协议尚未同意");
                } else {
                    goToNextActivity(MainActivity.class);
                }
                break;
            case R.id.iv_clear:
                mEtUserPassword.setText("");
                break;
            case R.id.saw:
                if (IsShow) {
                    mEtUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    IsShow = false;
                    mSaw.setImageResource(R.mipmap.dl_eyeblue);
                } else {
                    mEtUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    IsShow = true;
                    mSaw.setImageResource(R.mipmap.dl_eyegray);
                }
                break;
            case R.id.zc_choose:
                if (IsRead) {
                    mZcChoose.setBackgroundResource(R.mipmap.icon_duigouh);
                    IsRead = false;
                } else {
                    mZcChoose.setBackgroundResource(R.mipmap.icon_duigou);
                    IsRead = true;
                }
                break;
            case R.id.zc_xieyi:

                break;

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(mEtRegisterAccount.getText().toString())
                && !TextUtils.isEmpty(mEtUserPassword.getText().toString())
                && !TextUtils.isEmpty(mEtIdentifyingCode.getText().toString())) {
            mBtnRegister.setTextColor(getResources().getColor(R.color.white));
            mBtnRegister.setBackgroundResource(R.drawable.rect_blue_normal);
            mBtnRegister.setEnabled(true);
        } else {
            mBtnRegister.setTextColor(getResources().getColor(R.color.two_eight_color));
            mBtnRegister.setBackgroundResource(R.drawable.rect_blue_press);
            mBtnRegister.setEnabled(false);
        }
    }
}
