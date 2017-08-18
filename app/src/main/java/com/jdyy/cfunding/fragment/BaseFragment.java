package com.jdyy.cfunding.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * Fragment的基类
 * Created by Administrator on 2016/11/23 0023.
 */
public abstract class BaseFragment extends Fragment {
    private View view;

    protected Activity mActivity;

    //获取fragment布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected Activity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, view);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
            initView(view);
        }
        return view;
    }

    /**
     * 跳转另一个Activity
     *
     * @param cls 目标Activity
     */
    public void goToActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        getHoldingActivity().startActivity(intent);
    }

    /**
     * 跳转另一个Activity
     *
     * @param cls 目标Activity
     */
    public void goToNextActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        getHoldingActivity().startActivity(intent);
        getHoldingActivity().finish();
    }

    //是否可见
    protected boolean isVisble;

    /**
     * 实现Fragment数据的缓加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisble = true;
            onVisible();
        } else {
            isVisble = false;
            onInVisible();
        }
    }

    protected void onInVisible() {
    }

    protected void onVisible() {
        //加载数据
        loadData();
    }

    protected abstract void initView(View view);


    protected abstract void loadData();
}
