package com.jdyy.cfunding.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.activity.ProductDetailActivity;
import com.jdyy.cfunding.activity.WebActivity;
import com.jdyy.cfunding.adapter.HotAdapter;
import com.jdyy.cfunding.adapter.viewholder.HotViewHolder;
import com.jdyy.cfunding.app;
import com.jdyy.cfunding.bean.Banner;
import com.jdyy.cfunding.bean.Constant;
import com.jdyy.cfunding.bean.Item;
import com.jdyy.cfunding.http.HttpMethods;
import com.jdyy.cfunding.recyclerView.EasyRecyclerView;
import com.jdyy.cfunding.recyclerView.adapter.BaseViewHolder;
import com.jdyy.cfunding.recyclerView.adapter.RecyclerArrayAdapter;
import com.jdyy.cfunding.recyclerView.decoration.SpaceDecoration;
import com.jdyy.cfunding.utils.T;
import com.jdyy.cfunding.utils.Util;
import com.jdyy.cfunding.view.ImageCycleView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;


/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.ad_view)
    ImageCycleView mAdView;
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.rl_data)
    RelativeLayout mRlData;
    @Bind(R.id.rl_safe)
    RelativeLayout mRlSafe;
    @Bind(R.id.rl_talent)
    RelativeLayout mRlTalent;
    @Bind(R.id.rl_gift)
    RelativeLayout mRlGift;
    @Bind(R.id.easyRecyclerView)
    EasyRecyclerView recyclerView;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.iv_error)
    ImageView mIvError;

    private HotAdapter adapter;//热门推荐的适配器
    private ArrayList<String> mImageUrl = new ArrayList<>();//banner图片集合

    Subscriber<List<Banner>> mSubscriber;//Banner订阅者
    Subscriber<List<Item>> mSubscriber2;//热门推荐产品
    List<Item> mItems = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.statusColor);
        }
        mRlHead.setBackgroundResource(R.color.lucency);
        //将图片存入数组中
//        mImageUrl = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SpaceDecoration itemDecoration = new SpaceDecoration(Util.dip2px(getContext(), 2));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
        //加载过渡动画
        recyclerView.setAdapterWithProgress(adapter = new HotAdapter(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new HotViewHolder(parent);
            }
        });
        recyclerView.setRefreshListener(this);

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra("id", mItems.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {
        if (!Util.isNetworkAvailable(app.getContext())) {
            T.showShort(app.getContext(), "请检查网络连接是否正常");
            return;
        }
        mSubscriber = new Subscriber<List<Banner>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(Constant.MAIN_LOG, e.getMessage());
            }

            @Override
            public void onNext(final List<Banner> banners) {
                mAdView.setVisibility(View.VISIBLE);
                mIvError.setVisibility(View.GONE);
                mImageUrl.clear();
                for (int i = 0; i < banners.size(); i++) {
                    mImageUrl.add(Constant.Main_URL + banners.get(i).getImg());
                }
                //初始化图片，将图片显示出来
                mAdView.setImageResources(mImageUrl, new ImageCycleView.ImageCycleViewListener() {
                    @Override
                    public void displayImage(String imageURL, ImageView imageView) {
                        ImageLoader.getInstance().displayImage(imageURL, imageView);// 此处本人使用了ImageLoader对图片进行加装！
                    }
                    @Override
                    public void onImageClick(int position, View imageView) {
                        Intent intent = new Intent(getContext(), WebActivity.class);
                        intent.putExtra("Banner", banners.get(position));
                        getContext().startActivity(intent);
                    }
                });
            }
        };
        HttpMethods.getInstance().getAdBanner(mSubscriber);
        onRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        mSubscriber2 = new Subscriber<List<Item>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Item> items) {
                mItems.addAll(items);
                adapter.clear();
                adapter.addAll(items);
            }
        };
        HttpMethods.getInstance().getHotItem(mSubscriber2, Constant.APP_ID, Constant.APP_SECRET);
    }
}


