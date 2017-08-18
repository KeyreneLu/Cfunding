package com.jdyy.cfunding.fragment.Record;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.fragment.BaseFragment;
import com.jdyy.cfunding.recyclerView.EasyRecyclerView;
import com.jdyy.cfunding.recyclerView.adapter.RecyclerArrayAdapter;
import com.jdyy.cfunding.recyclerView.decoration.SpaceDecoration;
import com.jdyy.cfunding.utils.Util;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/9 0009.
 */

public class GoodsRecordFragment extends BaseFragment implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.easyRecyclerView)
    EasyRecyclerView recyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goodsrecord;
    }

    @Override
    protected void loadData() {
        onRefresh();
    }


    @Override
    protected void initView(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SpaceDecoration itemDecoration = new SpaceDecoration((int) Util.dip2px(getContext(), 6));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
//        recyclerView.setAdapterWithProgress(adapter = new ProductAdapter(getContext()) {
//            @Override
//            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
//                return new ProductViewHolder(parent);
//            }
//        });
//        //加载数据
//        adapter.setMore(R.layout.view_more, this);
//
//        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
//            @Override
//            public void onNoMoreShow() {
//                adapter.resumeMore();
//            }
//
//            @Override
//            public void onNoMoreClick() {
//                adapter.resumeMore();
//            }
//        });
//        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
//            @Override
//            public void onErrorShow() {
//                adapter.resumeMore();
//            }
//
//            @Override
//            public void onErrorClick() {
//                adapter.resumeMore();
//            }
//        });
        recyclerView.setRefreshListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

}
