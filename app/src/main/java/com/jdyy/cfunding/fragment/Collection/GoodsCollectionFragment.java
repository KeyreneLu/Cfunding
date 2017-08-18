package com.jdyy.cfunding.fragment.Collection;

import android.view.View;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.fragment.BaseFragment;
import com.jdyy.cfunding.recyclerView.EasyRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public class GoodsCollectionFragment extends BaseFragment {

    @Bind(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common_layout;
    }

    @Override
    protected void initView(View view) {

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
