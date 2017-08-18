package com.jdyy.cfunding.fragment.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.adapter.DynamiscAdapter;
import com.jdyy.cfunding.bean.Information;
import com.jdyy.cfunding.fragment.BaseFragment;
import com.jdyy.cfunding.http.GlobalToken;
import com.jdyy.cfunding.http.HttpMethods;
import com.jdyy.cfunding.view.ListViewForScrollView;
import com.jdyy.cfunding.view.scrollView.CustomViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * 项目动态
 * Created by Administrator on 2016/11/30 0030.
 */

public class ProjectDynamicsFragment extends BaseFragment {


    @Bind(R.id.list)
    ListViewForScrollView mList;
    @Bind(R.id.tv_none)
    TextView mTvNone;
    private View mRootView;
    private CustomViewPager vp;
    private String id;
    private Subscriber<Information> mSubscriber;
    private DynamiscAdapter mAdapter;

    public ProjectDynamicsFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_projectdynamics;
    }


    @Override
    protected void initView(View view) {

        vp.setObjectForPosition(view, 2);
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            id = arguments.getString("id");
        }
        mSubscriber = new Subscriber<Information>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Information information) {
                Log.e("SupportList.dymic", information.getDymic().size() + "");
                if (information.getDymic().size()<=0){
                    mList.setVisibility(View.GONE);
                    mTvNone.setVisibility(View.VISIBLE);
                }else {
                    mAdapter = new DynamiscAdapter(getContext(), information.getDymic());
                    mList.setAdapter(mAdapter);
                }
            }
        };
        HttpMethods.getInstance().getPrejectInformationNoToken(mSubscriber, GlobalToken.getToken(),id);
//        HttpMethods.getInstance().getPrejectInformation(mSubscriber, Constant.APP_ID, Constant.APP_SECRET, id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
