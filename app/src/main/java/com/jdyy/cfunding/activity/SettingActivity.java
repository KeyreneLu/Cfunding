package com.jdyy.cfunding.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.jdyy.cfunding.app.getContext;

/**
 * 个人设置界面
 * Created by Administrator on 2016/12/9 0009.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.tv_setting_datum)
    TextView mTvSettingDatum;
    @Bind(R.id.tv_renzhen)
    TextView mTvRenzhen;
    @Bind(R.id.ll_renzhen)
    RelativeLayout mLlRenzhen;
    @Bind(R.id.ll_account)
    RelativeLayout mLlAccount;
    @Bind(R.id.ll_address)
    RelativeLayout mLlAddress;
    @Bind(R.id.ll_xiugai)
    RelativeLayout mLlXiugai;
    @Bind(R.id.iv_shoushi)
    ImageView mIvShoushi;
    @Bind(R.id.ll_shoushi)
    RelativeLayout mLlShoushi;
    @Bind(R.id.tv_about)
    TextView mTvAbout;
    @Bind(R.id.ll_about)
    RelativeLayout mLlAbout;
    @Bind(R.id.ll_problem)
    RelativeLayout mLlProblem;
    @Bind(R.id.tv_phone)
    TextView mTvPhone;
    @Bind(R.id.ll_phone)
    RelativeLayout mLlPhone;
    @Bind(R.id.ll_feedback)
    RelativeLayout mLlFeedback;
    @Bind(R.id.btn_exit_app)
    TextView mBtnExitApp;
    @Bind(R.id.ll_account_info)
    RelativeLayout mLlAccountInfo;

    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        isOpen = (boolean) SPUtils.get(getContext(), "isOpen", false);
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
        mTitle.setText(R.string.sz);
        mIvHomeLeft.setVisibility(View.VISIBLE);
        if (isOpen) {
            mIvShoushi.setImageResource(R.mipmap.wd_on);
        } else {
            mIvShoushi.setImageResource(R.mipmap.wd_off);
        }
    }

    private void setlistener() {
        mLlAccountInfo.setOnClickListener(this);
        mLlRenzhen.setOnClickListener(this);
        mLlAccount.setOnClickListener(this);
        mLlAddress.setOnClickListener(this);
        mLlXiugai.setOnClickListener(this);
        mIvShoushi.setOnClickListener(this);
        mLlPhone.setOnClickListener(this);
        mLlFeedback.setOnClickListener(this);
        mBtnExitApp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_account_info:
                goToActivity(PersonalDataActivity.class);
                break;
            case R.id.ll_renzhen:
                goToActivity(RealAuthenticActivity.class);
                break;
            case R.id.ll_account:
                goToActivity(AccountInfoActivity.class);
                break;
            case R.id.ll_address:
                goToActivity(ReceiptAddressActivity.class);
                break;
            case R.id.ll_xiugai:
                goToActivity(ModifyActivity.class);
                break;
            case R.id.iv_shoushi:
                if (isOpen) {
                    mIvShoushi.setImageResource(R.mipmap.wd_off);
                    SPUtils.put(getContext(), "inputCode", "");
                    SPUtils.put(getContext(), "isOpen", false);
                    isOpen = false;
                } else {
                    mIvShoushi.setImageResource(R.mipmap.wd_on);
                    Intent intent1 = new Intent(SettingActivity.this, GestureActivity.class);
                    intent1.putExtra("flag", "1");
                    startActivity(intent1);
                    isOpen = true;
                }
                break;
            case R.id.ll_phone:

                break;
            case R.id.ll_feedback:
                goToActivity(FeedbackActivity.class);
                break;
            case R.id.btn_exit_app:
                final Dialog alertDialog = new AlertDialog.Builder(SettingActivity.this)
                        .setTitle(R.string.ts)
                        .setMessage(R.string.qrtcdqzh).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finishAll();
                            }
                        }).create();
                alertDialog.show();
                break;
        }
    }
}
