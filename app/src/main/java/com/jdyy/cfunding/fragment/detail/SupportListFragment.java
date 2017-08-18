package com.jdyy.cfunding.fragment.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.adapter.ProductSupportAdapter;
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
 * 支持列表界面
 * Created by Administrator on 2016/11/30 0030.
 */

public class SupportListFragment extends BaseFragment {


    @Bind(R.id.list)
    ListViewForScrollView mList;

    CustomViewPager vp;
    private View mRootView;
    ProductSupportAdapter mAdapter;
    private Subscriber<Information> mSubscriber;
    private String id;
    public SupportListFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_supportlist;
    }

    @Override
    protected void initView(View view) {

        vp.setObjectForPosition(view, 1);
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        if(arguments!=null)
        {
            id=arguments.getString("id");
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
                Log.e("SupportListFragment",information.getSupport().size()+"");
                mAdapter = new ProductSupportAdapter(getContext(),information.getSupport());
                mList.setAdapter(mAdapter);
            }
        };
        HttpMethods.getInstance().getPrejectInformationNoToken(mSubscriber, GlobalToken.getToken(),id);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
