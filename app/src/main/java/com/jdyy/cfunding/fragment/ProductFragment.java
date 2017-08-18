package com.jdyy.cfunding.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.activity.ProductDetailActivity;
import com.jdyy.cfunding.adapter.ClassifyAdapter;
import com.jdyy.cfunding.adapter.ProductAdapter;
import com.jdyy.cfunding.adapter.viewholder.ProductViewHolder;
import com.jdyy.cfunding.bean.Classify;
import com.jdyy.cfunding.bean.Constant;
import com.jdyy.cfunding.bean.Product;
import com.jdyy.cfunding.http.HttpMethods;
import com.jdyy.cfunding.recyclerView.EasyRecyclerView;
import com.jdyy.cfunding.recyclerView.adapter.BaseViewHolder;
import com.jdyy.cfunding.recyclerView.adapter.RecyclerArrayAdapter;
import com.jdyy.cfunding.recyclerView.decoration.SpaceDecoration;
import com.jdyy.cfunding.utils.ScreenUtils;
import com.jdyy.cfunding.utils.T;
import com.jdyy.cfunding.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class ProductFragment extends BaseFragment implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;

    @Bind(R.id.rl_preparation_goods)
    RelativeLayout mRlPreparationGoods;
    @Bind(R.id.tv_product_all)
    TextView mTvProductAll;
    @Bind(R.id.tv_product_march)
    TextView mTvProductMarch;
    @Bind(R.id.tv_product_finish)
    TextView mTvProductFinish;
    @Bind(R.id.rg_product_menu)
    LinearLayout mRgProductMenu;
    @Bind(R.id.main_content)
    LinearLayout mMainContent;

    private ProductAdapter adapter;
    private static int page;
    private Subscriber<List<Product>> mSubscriber;
    List<Product> mProductList = new ArrayList<>();
    private PopupWindow mPopupWindow;
    private View view;
    private ListView mListView;
    List<Classify> data;
    private int width;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product;
    }


    @Override
    protected void initView(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.statusColor);
        }
        mTitle.setText(R.string.cpzc);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SpaceDecoration itemDecoration = new SpaceDecoration((int) Util.dip2px(getContext(), 2));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapterWithProgress(adapter = new ProductAdapter(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ProductViewHolder(parent);
            }
        });
        //加载数据
        adapter.setMore(R.layout.view_more, this);

        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
                adapter.resumeMore();
            }

            @Override
            public void onNoMoreClick() {
                adapter.resumeMore();
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        recyclerView.setRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra("id", mProductList.get(position).getId());
                startActivity(intent);
            }
        });

        view = LayoutInflater.from(getContext()).inflate(R.layout.popu_goods_layout, null, false);
        mListView = (ListView) view.findViewById(R.id.listview);
        mPopupWindow = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.setAnimationStyle(R.style.PopupAnimationRight);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        httpClassify();
        setlistener();
    }

    private void httpClassify() {
        data = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Classify c = new Classify("来来" + i, i + "");
            data.add(c);
        }
        ClassifyAdapter mAdapter = new ClassifyAdapter(getContext(), data);
        mListView.setAdapter(mAdapter);
    }

    private void setlistener() {
        mTvProductAll.setOnClickListener(this);
        mTvProductMarch.setOnClickListener(this);
        mTvProductFinish.setOnClickListener(this);
        mRlPreparationGoods.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_product_all:
                T.showShort(getContext(),"1");
                break;
            case R.id.tv_product_march:
                T.showShort(getContext(),"2");
                break;
            case R.id.tv_product_finish:
                T.showShort(getContext(),"3");
                break;
            case R.id.rl_preparation_goods:
                width = ScreenUtils.getScreenWidth(getContext()) - mListView.getWidth();
                mPopupWindow.showAsDropDown(mRlPreparationGoods, width, 0);
                break;
        }
    }

    @Override
    protected void loadData() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        page = 1;
        if (!Util.isNetworkAvailable(getContext())) {
            T.showShort(getContext(), "请检查网络连接是否正常");
            adapter.pauseMore();
            return;
        }
        mSubscriber = new Subscriber<List<Product>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
//                T.showShort(getContext(), e.getMessage());
            }

            @Override
            public void onNext(List<Product> products) {
                adapter.clear();
                mProductList.clear();
                adapter.addAll(products);
                mProductList.addAll(products);
                page = 2;
            }
        };
        HttpMethods.getInstance().getAllProduct(mSubscriber, Constant.APP_ID, Constant.APP_SECRET, page + "", 0);
    }

    @Override
    public void onLoadMore() {
        if (!Util.isNetworkAvailable(getContext())) {
            adapter.pauseMore();
            return;
        }
        mSubscriber = new Subscriber<List<Product>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Product> products) {
                adapter.addAll(products);
                mProductList.addAll(products);
                page++;
            }
        };
        HttpMethods.getInstance().getAllProduct(mSubscriber, Constant.APP_ID, Constant.APP_SECRET, page + "", 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
