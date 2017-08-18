package com.jdyy.cfunding.fragment.detail;

import android.view.View;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.fragment.BaseFragment;
import com.jdyy.cfunding.view.ListViewForScrollView;
import com.jdyy.cfunding.view.scrollView.CustomViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 到期收货界面
 * Created by Administrator on 2016/11/30 0030.
 */

public class DailyAskFragment extends BaseFragment {

    @Bind(R.id.list)
    ListViewForScrollView mList;
    @Bind(R.id.tv_view_comments)
    TextView mTvViewComments;
    private View mRootView;
    private CustomViewPager vp;

    public DailyAskFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dailyask;
    }

    @Override
    protected void initView(View view) {
        vp.setObjectForPosition(view, 3);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
