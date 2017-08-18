package com.jdyy.cfunding.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jdyy.cfunding.Gesture.GestureContentView;
import com.jdyy.cfunding.Gesture.GestureDrawline;
import com.jdyy.cfunding.R;
import com.jdyy.cfunding.utils.SPUtils;
import com.jdyy.cfunding.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class GestureActivity extends BaseActivity {
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.gesture_container)
    RelativeLayout mGestureContainer;
    @Bind(R.id.text_tip)
    TextView mTextTip;

    private GestureContentView mGestureContentView;
    private boolean mIsFirstInput = true;
    private String mFirstPassword = null;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        ButterKnife.bind(this);
        flag = getIntent().getStringExtra("flag");
        initView();
    }

    private void initView() {
        mRlTou.setBackgroundResource(R.color.bg);
        mGestureContentView = new GestureContentView(this, false, "", new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {
                if (!isInputPassValidate(inputCode)) {
                    T.showShort(GestureActivity.this, "最少链接4个点, 请重新输入");
                    mGestureContentView.clearDrawlineState(0L);
                    return;
                }
                if (mIsFirstInput) {
                    mFirstPassword = inputCode;
                    mGestureContentView.clearDrawlineState(0L);
                    T.showShort(GestureActivity.this, "请重新再绘制一次");
                } else {
                    if (inputCode.equals(mFirstPassword)) {
                        Toast.makeText(GestureActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        SPUtils.put(GestureActivity.this, "inputCode", inputCode);
                        SPUtils.put(GestureActivity.this, "isOpen", true);
                        mGestureContentView.clearDrawlineState(0L);
                        if (flag.equals("0")) {
                            goToNextActivity(MainActivity.class);
                        } else {
                            GestureActivity.this.finish();
                        }
                    } else {
                        T.showShort(GestureActivity.this, "与上一次绘制不一致，请重新绘制");
                        // 左右移动动画
//                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureActivity.this, R.anim.shake);
//                        mTextTip.startAnimation(shakeAnimation);
                        // 保持绘制的线，1.5秒后清除
                        mGestureContentView.clearDrawlineState(1300L);
                    }
                }
                mIsFirstInput = false;
            }

            @Override
            public void checkSuccess() {

            }

            @Override
            public void checkFail() {

            }
        });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }

    private boolean isInputPassValidate(String inputPassword) {
        if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
            return false;
        }
        return true;
    }
}
