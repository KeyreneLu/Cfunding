package com.jdyy.cfunding.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class DeblockActivity extends BaseActivity {
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.text_tip)
    TextView mTextTip;
    @Bind(R.id.gesture_container)
    RelativeLayout mGestureContainer;
    @Bind(R.id.tv_forget_password)
    TextView mTvForgetPassword;

    private String inputCode;//手势密码
    private GestureContentView mGestureContentView;//手势密码盘控件
    private int time = 4;//手势密码错误次数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deblock);
        ButterKnife.bind(this);
        inputCode = (String) SPUtils.get(this, "inputCode", "");
        initView();
    }

    private void initView() {
        setToolbar1();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setBackgroundResource(R.color.white);
        }
        //手势密码，验证正确性
        mGestureContentView = new GestureContentView(this, true, inputCode, new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {

            }

            @Override
            public void checkSuccess() {
                mGestureContentView.clearDrawlineState(0L);
                T.showShort(DeblockActivity.this, "密码正确");
                goToNextActivity(MainActivity.class);
            }

            @Override
            public void checkFail() {
                mGestureContentView.clearDrawlineState(0L);
                if (time == 0){
                    SPUtils.put(DeblockActivity.this,"inputCode","");
                    SPUtils.put(DeblockActivity.this,"isOpen",false);
                    goToNextActivity(LoginActivity.class);
                    finish();
                }else {
                    mTextTip.setText("密码错误，还可以试"+time+"次");
                    TranslateAnimation animation = new TranslateAnimation(8, -8, 0, 0);
                    animation.setInterpolator(new OvershootInterpolator());
                    animation.setDuration(300);
                    animation.setRepeatCount(2);
                    animation.setRepeatMode(Animation.REVERSE);
                    mTextTip.startAnimation(animation);
                    time--;
                }
            }
        });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);

        mTvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put(DeblockActivity.this,"inputCode","");
                SPUtils.put(DeblockActivity.this,"isOpen",false);
                goToNextActivity(LoginActivity.class);
                finish();
            }
        });
    }
}
