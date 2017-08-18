package com.jdyy.cfunding.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.bean.Constant;
import com.jdyy.cfunding.bean.Information;
import com.jdyy.cfunding.bean.db.RealmHelper;
import com.jdyy.cfunding.fragment.BaseFragment;
import com.jdyy.cfunding.fragment.detail.DailyAskFragment;
import com.jdyy.cfunding.fragment.detail.DeliveryFragment;
import com.jdyy.cfunding.fragment.detail.ProjectDetailFragment;
import com.jdyy.cfunding.fragment.detail.ProjectDynamicsFragment;
import com.jdyy.cfunding.fragment.detail.SalesFragment;
import com.jdyy.cfunding.fragment.detail.SupportListFragment;
import com.jdyy.cfunding.http.GlobalToken;
import com.jdyy.cfunding.http.HttpMethods;
import com.jdyy.cfunding.utils.DateUtils;
import com.jdyy.cfunding.utils.ScreenUtils;
import com.jdyy.cfunding.utils.T;
import com.jdyy.cfunding.utils.TextUtils;
import com.jdyy.cfunding.view.NoScrollViewPager;
import com.jdyy.cfunding.view.ProgressView;
import com.jdyy.cfunding.view.scrollView.CustomViewPager;
import com.jdyy.cfunding.view.scrollView.ScrollLevitateTabView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * 项目详情
 * Created by Administrator on 2016/11/30 0030.
 */

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.iv_edit)
    ImageView mIvEdit;
    @Bind(R.id.rl_notice)
    RelativeLayout mRlNotice;
    @Bind(R.id.iv_home_left)
    ImageView mIvHomeLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    //    @Bind(R.id.progress)
//    ProgressBar mProgress;
    @Bind(R.id.iv_detail_picture)
    ImageView mIvDetailPicture;
    @Bind(R.id.tv_detail_name)
    TextView mTvDetailName;
    @Bind(R.id.tv_raise_money)
    TextView mTvRaiseMoney;
    @Bind(R.id.tv_target_amount)
    TextView mTvTargetAmount;
    @Bind(R.id.tv_remaining_time)
    TextView mTvRemainingTime;
    @Bind(R.id.numberbar1)
    ProgressView mNumberbar1;
    @Bind(R.id.tv_detail_tip)
    TextView mTvDetailTip;
    @Bind(R.id.rb_take_delivery)
    RadioButton mRbTakeDelivery;
    @Bind(R.id.rb_sale_consignment)
    RadioButton mRbSaleConsignment;
    @Bind(R.id.rg_menu)
    RadioGroup mRgMenu;
    @Bind(R.id.id_tab_line)
    ImageView mIdTabLine;
    @Bind(R.id.noScrollView)
    NoScrollViewPager mNoScrollView;
    @Bind(R.id.tv_non_delivery)
    TextView mTvNonDelivery;
    @Bind(R.id.iv_non_delivery)
    ImageView mIvNonDelivery;
    @Bind(R.id.rb_project_details)
    RadioButton mRbProjectDetails;
    @Bind(R.id.rb_support_list)
    RadioButton mRbSupportList;
    @Bind(R.id.rb_project_dynamics)
    RadioButton mRbProjectDynamics;
    @Bind(R.id.rb_frequently_questions)
    RadioButton mRbFrequentlyQuestions;
    @Bind(R.id.rg_detail_menu)
    RadioGroup mRgDetailMenu;
    @Bind(R.id.id_tab_line2)
    ImageView mIdTabLine2;
    @Bind(R.id.project_detail_noScroll)
    CustomViewPager mProjectDetailNoScroll;
    @Bind(R.id.rl_top_content)
    RelativeLayout mRlTopContent;
    @Bind(R.id.sl_main_content)
    ScrollLevitateTabView mSlMainContent;
    @Bind(R.id.flow_tab2)
    LinearLayout mFlowTab2;
    @Bind(R.id.ll_bottom_view)
    LinearLayout mLlBottomView;
    @Bind(R.id.xian5)
    View mXian5;
    @Bind(R.id.progress)
    ProgressBar mProgress;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.iv_above_top)
    ImageView mIvAboveTop;
    @Bind(R.id.tv_invite_investment)
    TextView mTvInviteInvestment;
    @Bind(R.id.tv_investment_together)
    TextView mTvInvestmentTogether;

    private String id;
    private Subscriber<Information> mSubscriber;
    private int ScreenWidth;
    List<BaseFragment> list = new ArrayList<>();
    List<BaseFragment> list1 = new ArrayList<>();
    Information mInformation = new Information();
    private int touHeight;
    private int headHeight;
    private int height;
    private String content;
    private int state;
    private boolean isCollected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        initView();
        initListeners();
        httpServer();
        initFragment();
        initEvent();
    }


    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.lucency);
        }
        mRlLeft.setVisibility(View.VISIBLE);
        mIvHomeLeft.setVisibility(View.VISIBLE);
        mRlNotice.setVisibility(View.VISIBLE);
        ScreenWidth = ScreenUtils.getScreenWidth(ProductDetailActivity.this);
        mIdTabLine.getLayoutParams().width = ScreenWidth / 2;
        mSlMainContent.smoothScrollTo(0, 0);
//        判断是否收藏
        if (RealmHelper.getInstance().queryInformationId(id)) {
            isCollected = true;
            mIvEdit.setImageResource(R.mipmap.icon_love);
        } else {
            mIvEdit.setImageResource(R.mipmap.icon_loveg);
            isCollected = false;
        }

        ViewTreeObserver vto = mRgDetailMenu.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                int width = mRgDetailMenu.getMeasuredWidth();
                height = mIvDetailPicture.getMeasuredHeight();
                headHeight = mRlHead.getMeasuredHeight();
                touHeight = mRlTou.getMeasuredHeight();
                mIdTabLine2.getLayoutParams().width = width / 4;//设置第二下标的颜色
                return true;
            }
        });
//        设置ViewPager不能左右滑动
        mNoScrollView.setNoScroll(true);
        mProjectDetailNoScroll.setNoScroll(true);
    }

    private void initListeners() {
        mSlMainContent.setOnScrollLintener(new ScrollLevitateTabView.OnScrollLintener() {
            @Override
            public void onScroll(int scrollY) {
                int top = Math.max(scrollY, mFlowTab2.getTop());
                mLlBottomView.layout(0, top + touHeight + headHeight, mLlBottomView.getWidth(), top + mLlBottomView.getHeight() + touHeight + headHeight);
                //设置标题的背景颜色
                if (scrollY <= 0) {
                    state = 1;
                    mXian5.setVisibility(View.INVISIBLE);
                    if (RealmHelper.getInstance().queryInformationId(id)) {
                        mIvEdit.setImageResource(R.mipmap.icon_love);
                    } else {
                        mIvEdit.setImageResource(R.mipmap.icon_loveg);
                    }
                    mIvHomeLeft.setImageResource(R.mipmap.icon_back);
                    mTitle.setTextColor(Color.argb((int) 0, 97, 97, 131));
                    mRlTou.setBackgroundColor(Color.argb((int) 0, 182, 182, 189));
                    mRlHead.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
                } else if (scrollY > 0 && scrollY <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    state = 2;
                    float scale = (float) scrollY / height;
                    float alpha = (255 * scale);
                    mTitle.setTextColor(Color.argb((int) alpha, 97, 97, 131));
                    mXian5.setVisibility(View.INVISIBLE);
                    if (RealmHelper.getInstance().queryInformationId(id)) {
                        mIvEdit.setImageResource(R.mipmap.icon_love_red);
                    } else {
                        mIvEdit.setImageResource(R.mipmap.icon_love_gray);
                    }
                    mIvHomeLeft.setImageResource(R.mipmap.icon_backgray);
                    mRlTou.setBackgroundColor(Color.argb((int) alpha, 182, 182, 189));
                    mRlHead.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {    //滑动到banner下面设置普通颜色
                    state = 2;
                    if (RealmHelper.getInstance().queryInformationId(id)) {
                        mIvEdit.setImageResource(R.mipmap.icon_love_red);
                    } else {
                        mIvEdit.setImageResource(R.mipmap.icon_love_gray);
                    }
                    mIvHomeLeft.setImageResource(R.mipmap.icon_backgray);
                    mTitle.setTextColor(Color.argb((int) 255, 97, 97, 131));
                    mRlTou.setBackgroundColor(Color.argb((int) 255, 182, 182, 189));
                    mRlHead.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                    mXian5.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initEvent() {
        mRgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ScaleAnimation sAnim = new ScaleAnimation(1, 1.1f, 1, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                sAnim.setDuration(500);
                sAnim.setFillAfter(true);
                //遍历所有的RadioButton
                for (int i = 0; i < group.getChildCount(); ) {
                    RadioButton radioBtn = (RadioButton) group.getChildAt(i);
                    i = i + 2;
                    if (radioBtn.isChecked()) {
                        radioBtn.startAnimation(sAnim);
                    } else {
                        radioBtn.clearAnimation();
                    }
                }

                switch (checkedId) {
                    case R.id.rb_take_delivery:
                        mNoScrollView.setCurrentItem(0, false);
                        break;
                    case R.id.rb_sale_consignment:
                        mNoScrollView.setCurrentItem(1, false);
                        break;
                }
            }
        });
        mNoScrollView.setAdapter(new TabAdapter(getSupportFragmentManager()));
        mNoScrollView.setOnPageChangeListener(new TabOnPageChangeListener());

        mRgDetailMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ScaleAnimation sAnim = new ScaleAnimation(1, 1.1f, 1, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                sAnim.setDuration(500);
                sAnim.setFillAfter(true);
                //遍历所有的RadioButton
                for (int i = 0; i < group.getChildCount(); ) {
                    RadioButton radioBtn = (RadioButton) group.getChildAt(i);
                    i = i + 1;
                    if (radioBtn.isChecked()) {
                        radioBtn.startAnimation(sAnim);
                    } else {
                        radioBtn.clearAnimation();
                    }
                }
                switch (checkedId) {
                    case R.id.rb_project_details:
                        mProjectDetailNoScroll.setCurrentItem(0, false);
                        break;
                    case R.id.rb_support_list:
                        mProjectDetailNoScroll.setCurrentItem(1, false);
                        break;
                    case R.id.rb_project_dynamics:
                        mProjectDetailNoScroll.setCurrentItem(2, false);
                        break;
                    case R.id.rb_frequently_questions:
                        mProjectDetailNoScroll.setCurrentItem(3, false);
                        break;
                }
            }
        });
        mProjectDetailNoScroll.setAdapter(new TabAdapter2(getSupportFragmentManager()));
        mProjectDetailNoScroll.setOnPageChangeListener(new TabOnPageChangeListener2());

        mIvHomeLeft.setOnClickListener(this);
        mRlNotice.setOnClickListener(this);
        mTvInvestmentTogether.setOnClickListener(this);
        mTvInviteInvestment.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.rl_notice:
                if (state == 1) {
                    if (isCollected) {
                        RealmHelper.getInstance().deleteInformation(id);
                        mIvEdit.setImageResource(R.mipmap.icon_loveg);
                        isCollected = false;
                    } else {
                        RealmHelper.getInstance().insertInformation(mInformation);
                        mIvEdit.setImageResource(R.mipmap.icon_love);
                        isCollected = true;
                    }
                } else {
                    if (isCollected) {
                        RealmHelper.getInstance().deleteInformation(id);
                        mIvEdit.setImageResource(R.mipmap.icon_love_gray);
                        isCollected = false;
                    } else {
                        RealmHelper.getInstance().insertInformation(mInformation);
                        mIvEdit.setImageResource(R.mipmap.icon_love_red);
                        isCollected = true;
                    }
                }
                break;
            case R.id.tv_investment_together:
                goToActivity(SupportDetailActivity.class);
                break;

        }
    }


    private void httpServer() {
        mSubscriber = new Subscriber<Information>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                T.showShort(ProductDetailActivity.this, e.getMessage());
            }

            @Override
            public void onNext(Information information) {
                mInformation = information;
                mProgress.setVisibility(View.GONE);
                mSlMainContent.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(Constant.Main_URL + information.getCover_img(), mIvDetailPicture);
                mTvDetailName.setText(information.getName());
                mTitle.setText(information.getName());
                mTvRemainingTime.setText(DateUtils.getIntervalDays(information.getEnd_time()));
                mTvTargetAmount.setText(TextUtils.MoneyDeal(information.getRaising_money(), false));
                mTvRaiseMoney.setText(TextUtils.MoneyDeal(information.getFinish_money(), false));
                mTvDetailTip.setText("超募金额" + information.getFinish_money() + "元为止");
                content = information.getContent();
                Log.e(Constant.MAIN_LOG, content);
            }
        };
        HttpMethods.getInstance().getPrejectInformationNoToken(mSubscriber, GlobalToken.getToken(), id);
    }

    private void initFragment() {
        DeliveryFragment delivery = new DeliveryFragment();
        list.add(delivery);
        SalesFragment sales = new SalesFragment();
        list.add(sales);

        ProjectDetailFragment detail = new ProjectDetailFragment(mProjectDetailNoScroll);
        Bundle bundle1 = new Bundle();
        bundle1.putString("id", id);
        detail.setArguments(bundle1);
        list1.add(detail);

        SupportListFragment support = new SupportListFragment(mProjectDetailNoScroll);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        support.setArguments(bundle);
        list1.add(support);

        ProjectDynamicsFragment dynamics = new ProjectDynamicsFragment(mProjectDetailNoScroll);
        Bundle bundle2 = new Bundle();
        bundle2.putString("id", id);
        dynamics.setArguments(bundle2);
        list1.add(dynamics);

        DailyAskFragment ask = new DailyAskFragment(mProjectDetailNoScroll);
        Bundle bundle3 = new Bundle();
        bundle3.putString("id", id);
        ask.setArguments(bundle3);
        list1.add(ask);

        mProjectDetailNoScroll.setCurrentItem(0);
        mNoScrollView.setCurrentItem(0);
    }

    private class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mIdTabLine.getLayoutParams();
            //获取组件距离左侧组件的距离
            lp.leftMargin = (int) ((positionOffset + position) * mRgMenu.getWidth() / 2);
            mIdTabLine.setLayoutParams(lp);
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mRbTakeDelivery.setChecked(true);
                    break;
                case 1:
                    mRbSaleConsignment.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class TabOnPageChangeListener2 implements ViewPager.OnPageChangeListener {
        /**
         * 当滑动状态改变时调用
         * state=0的时候表示什么都没做，就是停在那
         * state=1的时候表示正在滑动
         * state==2的时候表示滑动完毕了
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mIdTabLine2.getLayoutParams();
            //获取组件距离左侧组件的距离
            lp.leftMargin = (int) ((positionOffset + position) * mRgDetailMenu.getWidth() / 4);
            mIdTabLine2.setLayoutParams(lp);
        }

        @Override
        public void onPageSelected(int position) {
            mProjectDetailNoScroll.resetHeight(position);
            switch (position) {
                case 0:
                    mRbProjectDetails.setChecked(true);
                    break;
                case 1:
                    mRbSupportList.setChecked(true);
                    break;
                case 2:
                    mRbProjectDynamics.setChecked(true);
                    break;
                case 3:
                    mRbFrequentlyQuestions.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }

    private class TabAdapter2 extends FragmentPagerAdapter {

        public TabAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list1.get(position);
        }

        @Override
        public int getCount() {
            return list1.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }

}
