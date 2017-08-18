package com.jdyy.cfunding.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.adapter.BankAdapter;
import com.jdyy.cfunding.bean.Bank;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class RealAuthenticActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_sfz)
    EditText mEtSfz;
    @Bind(R.id.rl_card)
    RelativeLayout mRlCard;
    @Bind(R.id.tv_bank)
    TextView mTvBank;
    @Bind(R.id.iv_bank)
    ImageView mIvBank;
    @Bind(R.id.et_bcard)
    EditText mEtBcard;
    @Bind(R.id.btn_real_authentic)
    Button mBtnRealAuthentic;

    //得到银行信息的集合
    int[] logo = {R.mipmap.sy_nyyh, R.mipmap.sy_pfyh, R.mipmap.sy_jtyh, R.mipmap.sy_gsyh, R.mipmap.sy_ycyh, R.mipmap.sy_gfyh
            , R.mipmap.sy_msyh, R.mipmap.sy_payh, R.mipmap.sy_zsyh, R.mipmap.sy_zgyh, R.mipmap.sy_jsyh, R.mipmap.sy_gdyh, R.mipmap.sy_xyyh
            , R.mipmap.sy_zxyh, R.mipmap.sy_hxyh};
    @Bind(R.id.rl_bank)
    RelativeLayout mRlBank;
    @Bind(R.id.rl_main)
    RelativeLayout mRlMain;
    private String[] names;
    private String[] costs;
    List<Bank> Banks;
    private View mRootView;

    PopupWindow mPopupWindow;
    //    银行ListView，适配器
    ListView mLvBank;
    TextView mBtnCancel, mBtnSure;
    BankAdapter mBankAdapter;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realauthentic);
        ButterKnife.bind(this);
        initData();
        initView();
        setlistener();
    }

    private void initData() {
        Banks = new ArrayList<>();
        //        得到银行的信息
        names = getResources().getStringArray(R.array.Bank);
        costs = getResources().getStringArray(R.array.Cost);

        for (int i = 0; i < logo.length; i++) {
            Bank bank = new Bank(logo[i], names[i], costs[i]);
            Banks.add(bank);
        }
    }

    private void initView() {
        setToolbar1();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.statusColor);
        }

        mRlLeft.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.smrz);
        mIvHomeLeft.setVisibility(View.VISIBLE);
        mEtBcard.addTextChangedListener(new myWatcher());
    }

    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mBtnRealAuthentic.setOnClickListener(this);
        mRlBank.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.btn_real_authentic:

                break;
            case R.id.rl_bank:
                showBankPopuWindow();
                break;
        }
    }

    //银行选择窗口
    private void showBankPopuWindow() {
        mRootView = LayoutInflater.from(RealAuthenticActivity.this).inflate(R.layout.popu_bank, null, false);
        mPopupWindow = new PopupWindow(mRootView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mLvBank = (ListView) mRootView.findViewById(R.id.lv_bank);
        mBtnSure = (TextView) mRootView.findViewById(R.id.tv_sure);
        mBtnCancel = (TextView) mRootView.findViewById(R.id.tv_cancel);
        mBankAdapter = new BankAdapter(RealAuthenticActivity.this, Banks);
        mLvBank.setAdapter(mBankAdapter);
        mLvBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBankAdapter.changeSelected(position);
                pos = position;
            }
        });
        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvBank.setText(names[pos]);
                mPopupWindow.dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.SelectPicDialog);
        mPopupWindow.showAtLocation(mRlMain,
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }



    class myWatcher implements TextWatcher {
        int beforeTextLength = 0;
        int onTextLength = 0;
        boolean isChanged = false;

        int location = 0;// 记录光标的位置
        private char[] tempChar;
        private StringBuffer buffer = new StringBuffer();
        int konggeNumberB = 0;

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
            onTextLength = s.length();
            buffer.append(s.toString());
            if (onTextLength == beforeTextLength || onTextLength <= 3
                    || isChanged) {
                isChanged = false;
                return;
            }
            isChanged = true;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
            beforeTextLength = s.length();
            if (buffer.length() > 0) {
                buffer.delete(0, buffer.length());
            }
            konggeNumberB = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    konggeNumberB++;
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            if (isChanged) {
                location = mEtBcard.getSelectionEnd();
                int index = 0;
                while (index < buffer.length()) {
                    if (buffer.charAt(index) == ' ') {
                        buffer.deleteCharAt(index);
                    } else {
                        index++;
                    }
                }

                index = 0;
                int konggeNumberC = 0;
                while (index < buffer.length()) {
                    if ((index == 4 || index == 9 || index == 14 || index == 19)) {
                        buffer.insert(index, ' ');
                        konggeNumberC++;
                    }
                    index++;
                }

                if (konggeNumberC > konggeNumberB) {
                    location += (konggeNumberC - konggeNumberB);
                }

                tempChar = new char[buffer.length()];
                buffer.getChars(0, buffer.length(), tempChar, 0);
                String str = buffer.toString();
                if (location > str.length()) {
                    location = str.length();
                } else if (location < 0) {
                    location = 0;
                }

                mEtBcard.setText(str);
                Editable etable = mEtBcard.getText();
                Selection.setSelection(etable, location);
                isChanged = false;
            }
        }

    }
}
