package com.jdyy.cfunding.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.adapter.ExpandAdapter;
import com.jdyy.cfunding.bean.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

public class ReckoningActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.expand_list)
    ExpandableListView mExpandList;
    @Bind(R.id.rl_progress)
    RelativeLayout mRlProgress;
    ExpandAdapter mAdapter;
    List<Person> list;
    @Bind(R.id.iv_right)
    ImageView mIvRight;
    @Bind(R.id.main_content)
    RelativeLayout mMainContent;

    private PopupWindow mPopupWindow;
    private View view;
    private ImageView mIvPopuExit;
    private static int index = 0;
    private TextView mTvReckoningAll, mTvReckoningDeposit, mTvReckoningReturn, mTvReckoningReward, mTvReckoningInvest, mTvReckoningRecharge, mTvReckoningOther;
    List<TextView> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reckoning);
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
        mIvHomeLeft.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.zd);
        mIvRight.setImageResource(R.mipmap.cp_sx);

        list = new ArrayList<>();
        mAdapter = new ExpandAdapter(ReckoningActivity.this, list);
        mExpandList.setAdapter(mAdapter);
        for (int i = 0; i < list.size(); i++) {
            mExpandList.expandGroup(i);
        }
        mExpandList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return true;
            }
        });


    }


    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mIvRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.iv_right:
                showPoPuWindow();
                break;
        }
    }

    private void showPoPuWindow() {
        view = LayoutInflater.from(ReckoningActivity.this).inflate(R.layout.popu_reckoning_layout, null, false);
        mList.clear();
        mIvPopuExit = (ImageView) view.findViewById(R.id.iv_popu_exit);
        mTvReckoningAll = (TextView) view.findViewById(R.id.tv_reckoning_all);
        mTvReckoningDeposit = (TextView) view.findViewById(R.id.tv_reckoning_deposit);
        mTvReckoningReturn = (TextView) view.findViewById(R.id.tv_reckoning_return);
        mTvReckoningReward = (TextView) view.findViewById(R.id.tv_reckoning_reward);
        mTvReckoningInvest = (TextView) view.findViewById(R.id.tv_reckoning_invest);
        mTvReckoningRecharge = (TextView) view.findViewById(R.id.tv_reckoning_recharge);
        mTvReckoningOther = (TextView) view.findViewById(R.id.tv_reckoning_other);
        mList.add(mTvReckoningAll);
        mList.add(mTvReckoningDeposit);
        mList.add(mTvReckoningReturn);
        mList.add(mTvReckoningReward);
        mList.add(mTvReckoningInvest);
        mList.add(mTvReckoningRecharge);
        mList.add(mTvReckoningOther);

        mPopupWindow = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        for (int i = 0; i < mList.size(); i++) {
            if (index == i) {
                mList.get(i).setTextColor(getResources().getColor(R.color.blue));
                mList.get(i).setBackgroundResource(R.drawable.rect_blue2);
            } else {
                mList.get(i).setTextColor(getResources().getColor(R.color.text_hot_color));
                mList.get(i).setBackgroundResource(R.drawable.rect_gray8);
            }
        }
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.setAnimationStyle(R.style.AnimationRight);
        mIvPopuExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        for (int i = 0; i < mList.size(); i++) {
            final int finalI = i;
            mList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = finalI;
                    mPopupWindow.dismiss();
                    httpData();
                }
            });
        }
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindow.showAtLocation(mMainContent, Gravity.RIGHT, 0, 0);
    }

    private void httpData() {

    }
}
