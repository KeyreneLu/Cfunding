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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.bean.Constant;
import com.jdyy.cfunding.http.HttpMethods;
import com.jdyy.cfunding.utils.SPUtils;
import com.jdyy.cfunding.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
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
    @Bind(R.id.et_account)
    EditText mEtAccount;
    @Bind(R.id.et_user_password)
    EditText mEtUserPassword;
    @Bind(R.id.iv_clear)
    ImageView mIvClear;
    @Bind(R.id.iv_saw)
    ImageView mIvSaw;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.tv_forget_password)
    TextView mTvForgetPassword;
    @Bind(R.id.tv_register)
    TextView mTvRegister;

    private boolean isShow =false;
    Subscriber<String> mSubscriber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        mTitle.setText(R.string.dl);
        mIvHomeLeft.setVisibility(View.VISIBLE);
        mBtnLogin.setEnabled(false);
    }


    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mIvClear.setOnClickListener(this);
        mIvSaw.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mTvForgetPassword.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mEtAccount.addTextChangedListener(this);
        mEtUserPassword.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.iv_clear:
                mEtUserPassword.setText("");
                break;

            case R.id.iv_saw:
                if (isShow) {
//                    密码不可见
                    mEtUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    mIvSaw.setImageResource(R.mipmap.dl_eyeblue);
                    isShow = false;
                } else {
//                    密码可见
                    mEtUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvSaw.setImageResource(R.mipmap.dl_eyegray);
                    isShow = true;
                }
                break;

            case R.id.btn_login:
                mSubscriber = new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        T.showShort(LoginActivity.this,e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        SPUtils.put(LoginActivity.this,"session",s);
                    }
                };
                HttpMethods.getInstance().login(mSubscriber, Constant.APP_ID,Constant.APP_SECRET,mEtAccount.getText().toString(),mEtUserPassword.getText().toString());
                break;
            case R.id.tv_forget_password:
                goToActivity(ModifyActivity.class);
                break;
            case R.id.tv_register:
                goToActivity(RegisterActivity.class);
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
        if (!TextUtils.isEmpty(mEtAccount.getText().toString())
                && !TextUtils.isEmpty(mEtUserPassword.getText().toString())) {
            mBtnLogin.setTextColor(getResources().getColor(R.color.white));
            mBtnLogin.setBackgroundResource(R.drawable.rect_blue_normal);
            mBtnLogin.setEnabled(true);
        } else {
            mBtnLogin.setTextColor(getResources().getColor(R.color.two_eight_color));
            mBtnLogin.setBackgroundResource(R.drawable.rect_blue_press);
            mBtnLogin.setEnabled(false);
        }
    }
}
