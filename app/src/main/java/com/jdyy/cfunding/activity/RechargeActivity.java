package com.jdyy.cfunding.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class RechargeActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.et_number)
    EditText mEtNumber;
    @Bind(R.id.tv_real_phone)
    TextView mTvRealPhone;
    @Bind(R.id.tv_real_name)
    TextView mTvRealName;
    @Bind(R.id.tv_bank_card)
    TextView mTvBankCard;
    @Bind(R.id.btn_recharge)
    Button mBtnRecharge;

    //dialog里面的控件
    private EditText mEtMima;
    private TextView mTvForget;
    private ImageView mIvDelete;
    private Button mBtnCancel, mBtnOk;
    View mRootView;
    private LayoutInflater mInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
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
        mTitle.setText(R.string.cz);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }


    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mBtnRecharge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.btn_recharge:
                if (TextUtils.isEmpty(mEtNumber.getText().toString())) {
                    T.showShort(RechargeActivity.this, "您还未输入充值金额，请输入");
                } else {
                    ShowDialog();
                }
                break;
        }
    }

    private void ShowDialog() {
        mInflater = LayoutInflater.from(RechargeActivity.this);
        mRootView = mInflater.inflate(R.layout.dialog_password_layout, null);
        //必须要加style，不然会有白色头部
        final Dialog mDialog = new Dialog(RechargeActivity.this, R.style.Dialog);

        mDialog.getWindow().setContentView(mRootView);
        mEtMima = (EditText) mRootView.findViewById(R.id.et_mima);
        mIvDelete = (ImageView) mRootView.findViewById(R.id.iv_delete);
        mTvForget = (TextView) mRootView.findViewById(R.id.tv_forget);
        mBtnCancel = (Button) mRootView.findViewById(R.id.btn_cancel);
        mBtnOk = (Button) mRootView.findViewById(R.id.btn_ok);

        /** 自动弹出软键盘 **/
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEtMima, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        mDialog.show();
        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtMima.setText("");
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtMima.getText().toString())) {
                    T.showShort(RechargeActivity.this, "密码为空,请输入");
                } else {
                    Intent intent = new Intent(RechargeActivity.this, RechargeDetailActivity.class);
                    intent.putExtra("sum", mEtNumber.getText().toString());
                    startActivity(intent);
                    mDialog.dismiss();
                    finish();
                }
            }
        });

        mTvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(ModifyActivity.class);
            }
        });
    }
}
