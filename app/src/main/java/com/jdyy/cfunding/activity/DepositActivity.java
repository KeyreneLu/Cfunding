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

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现界面
 * Created by Administrator on 2016/12/9 0009.
 */

public class DepositActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.tv_deposit_sum)
    TextView mTvDepositSum;
    @Bind(R.id.btn_deposit_sure)
    Button mBtnDepositSure;
    @Bind(R.id.tv_deposit_phone)
    TextView mTvDepositPhone;
    @Bind(R.id.tv_deposit_name)
    TextView mTvDepositName;
    @Bind(R.id.tv_deposit_card)
    TextView mTvDepositCard;
    @Bind(R.id.tv_deposit_date)
    TextView mTvDepositDate;
    @Bind(R.id.tv_deposit_time)
    TextView mTvDepositTime;
    @Bind(R.id.et_deposit_number)
    EditText mEtDepositNumber;
    @Bind(R.id.tv_deposit_charge)
    TextView mTvDepositCharge;
    private float mMax;
    private float mBalance;
    private LayoutInflater mInflater;
    private View mRootView;
    //dialog里面的控件
    private EditText mEtMima;
    private TextView mTvForget;
    private ImageView mIvDelete;
    private Button mBtnCancel,mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
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
        mTitle.setText(R.string.tx);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }


    private void setlistener() {
//        mIvHomeLeft.setOnClickListener(this);
        mBtnDepositSure.setOnClickListener(this);
    }

    @OnClick(R.id.iv_home_left)
    void ActivityFinish(){
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.iv_home_left:
//                finish();
//                break;
            case R.id.btn_deposit_sure:
                mMax = Float.parseFloat(mTvDepositSum.getText().toString());
                if (TextUtils.isEmpty(mEtDepositNumber.getText().toString())){
                    T.showShort(DepositActivity.this,"请输入提现金额");
                }else if (Float.parseFloat(mEtDepositNumber.getText().toString()) == 0){
                    T.showShort(DepositActivity.this,"提现金额不能为0");
                }else if (Float.parseFloat(mEtDepositNumber.getText().toString()) > mMax){
                    T.showShort(DepositActivity.this,"提现金额过大，请重新确认");
                }else {
                    mBalance = Float.parseFloat(mEtDepositNumber.getText().toString());
                    ShowDialog();
                }
                break;
        }
    }

    private void ShowDialog() {
        mInflater = LayoutInflater.from(DepositActivity.this);
        mRootView = mInflater.inflate(R.layout.dialog_password_layout,null);
        //必须要加style，不然会有白色头部
        final Dialog mDialog= new Dialog(DepositActivity.this,R.style.Dialog);
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
                imm.showSoftInput( mEtMima, InputMethodManager.SHOW_IMPLICIT);
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
                if (TextUtils.isEmpty(mEtMima.getText().toString())){
                    T.showShort(DepositActivity.this,"密码为空,请输入");
                }else {
                    Intent intent = new Intent(DepositActivity.this,DepositDetailActivity.class);
                    intent.putExtra("date",getDate());
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

    public String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd   HH:mm");
        java.sql.Date curDate = new java.sql.Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
}
