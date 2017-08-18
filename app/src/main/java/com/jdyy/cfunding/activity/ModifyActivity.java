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
 * Created by Administrator on 2016/12/12 0012.
 */

public class ModifyActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
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
    @Bind(R.id.et_modify_account)
    EditText mEtModifyAccount;
    @Bind(R.id.et_Invitation_code)
    EditText mEtInvitationCode;
    @Bind(R.id.btn_invitation_code)
    Button mBtnInvitationCode;
    @Bind(R.id.et_modify_password)
    EditText mEtModifyPassword;
    @Bind(R.id.iv_clear)
    ImageView mIvClear;
    @Bind(R.id.iv_saw)
    ImageView mIvSaw;
    @Bind(R.id.iv_choose)
    ImageButton mIvChoose;
    @Bind(R.id.tv_xieyi)
    TextView mTvXieyi;
    @Bind(R.id.btn_change_password)
    Button mBtnChangePassword;
    //    密码是否可见
    private boolean IsShow = false;
    //    协议是否选中
    private boolean IsRead = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
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
        mTitle.setText(R.string.xgmm);
        mIvHomeLeft.setVisibility(View.VISIBLE);
        mBtnChangePassword.setEnabled(false);
    }
    private void setlistener() {
        mIvChoose.setOnClickListener(this);
        mBtnInvitationCode.setOnClickListener(this);
        mIvClear.setOnClickListener(this);
        mIvSaw.setOnClickListener(this);
        mBtnChangePassword.setOnClickListener(this);
        mTvXieyi.setOnClickListener(this);
        mIvHomeLeft.setOnClickListener(this);

        mEtModifyAccount.addTextChangedListener(this);
        mEtInvitationCode.addTextChangedListener(this);
        mEtModifyPassword.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.btn_invitation_code:
                //然后在需要用这个的方法里new一个对象，然后调用start();方法就可以啦
                TimeCountUtils timeCountUtils = new TimeCountUtils(ModifyActivity.this, 60000, 1000, mBtnInvitationCode);
                timeCountUtils.start();
                break;
            case R.id.iv_clear:
                mEtModifyPassword.setText("");
                break;
            case R.id.iv_saw:
                if (IsShow) {
                    mEtModifyPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    mIvSaw.setImageResource(R.mipmap.dl_eyeblue);
                    IsShow = false;
                } else {
                    mEtModifyPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvSaw.setImageResource(R.mipmap.dl_eyegray);
                    IsShow = true;
                }
                break;
            case R.id.btn_change_password:
                String userPassword = mEtModifyPassword.getText().toString();
                if (userPassword.length()<6){
                    T.showShort(ModifyActivity.this,"密码长度不够");
                }else if (!com.jdyy.cfunding.utils.TextUtils.isLetterDigit(userPassword)){
                    T.showShort(ModifyActivity.this,"密码必须包含数字、字母符号");
                }else if (!IsRead){
                    T.showShort(ModifyActivity.this,"协议尚未同意");
                } else {
                    goToNextActivity(MainActivity.class);
                }
                break;
            case R.id.tv_xieyi:
                break;
            case R.id.iv_choose:
                if (IsRead) {
                    mIvChoose.setBackgroundResource(R.mipmap.icon_duigouh);
                    IsRead = false;
                } else {
                    mIvChoose.setBackgroundResource(R.mipmap.icon_duigou);
                    IsRead = true;
                }
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
        if (!TextUtils.isEmpty(mEtModifyAccount.getText().toString())
                && !TextUtils.isEmpty(mEtModifyPassword.getText().toString())
                && !TextUtils.isEmpty(mEtInvitationCode.getText().toString())) {
            mBtnChangePassword.setTextColor(getResources().getColor(R.color.white));
            mBtnChangePassword.setBackgroundResource(R.drawable.rect_blue_normal);
            mBtnChangePassword.setEnabled(true);
        } else {
            mBtnChangePassword.setTextColor(getResources().getColor(R.color.two_eight_color));
            mBtnChangePassword.setBackgroundResource(R.drawable.rect_blue_press);
            mBtnChangePassword.setEnabled(false);
        }
    }
}
