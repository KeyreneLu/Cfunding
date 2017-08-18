package com.jdyy.cfunding.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.fragment.BaseFragment;
import com.jdyy.cfunding.fragment.FindFragment;
import com.jdyy.cfunding.fragment.HomeFragment;
import com.jdyy.cfunding.fragment.MeFragment;
import com.jdyy.cfunding.fragment.MovieFragment;
import com.jdyy.cfunding.fragment.ProductFragment;
import com.jdyy.cfunding.utils.T;
import com.jdyy.cfunding.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ----------Dragon be here!----------/
 *   ┏┓      ┏┓
 * ┏┛┻━━━┛┻┓
 * ┃              ┃
 * ┃      ━      ┃
 * ┃  ┳┛  ┗┳  ┃
 * ┃              ┃
 * ┃      ┻      ┃
 * ┃              ┃
 * ┗━┓      ┏━┛
 *     ┃      ┃神兽保佑
 *     ┃      ┃代码无BUG！
 *     ┃      ┗━━━┓
 *     ┃              ┣┓
 *     ┃              ┏┛
 *      ┗┓┓┏━┳┓┏┛
 *        ┃┫┫  ┃┫┫
 *        ┗┻┛  ┗┻┛
 * ━━━━━━神兽出没━━━━━━
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.noScrollView)
    NoScrollViewPager mNoScrollView;
    @Bind(R.id.rb_home)
    RadioButton mRbHome;
    @Bind(R.id.rb_product)
    RadioButton mRbProduct;
    @Bind(R.id.rb_movie)
    RadioButton mRbMovie;
    @Bind(R.id.rb_find)
    RadioButton mRbFind;
    @Bind(R.id.rb_me)
    RadioButton mRbMe;
    @Bind(R.id.rg_menu)
    RadioGroup mRgMenu;
    @Bind(R.id.btn_login_now)
    Button mBtnLoginNow;
    @Bind(R.id.rl_login)
    RelativeLayout mRlLogin;

    List<BaseFragment> mFragments = new ArrayList<>();//fragment集合
    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        HomeFragment homeFragment = new HomeFragment();
        mFragments.add(homeFragment);

        ProductFragment productFragment = new ProductFragment();
        mFragments.add(productFragment);

        MovieFragment movieFragment = new MovieFragment();
        mFragments.add(movieFragment);

        FindFragment findFragment = new FindFragment();
        mFragments.add(findFragment);

        MeFragment meFragment = new MeFragment();
        mFragments.add(meFragment);
    }

    private void initView() {
        TabAdapter mAdapter = new TabAdapter(getSupportFragmentManager());
        mNoScrollView.setAdapter(mAdapter);
        mNoScrollView.setCurrentItem(0);
//        设置ViewPager不能左右滑动
        mNoScrollView.setNoScroll(true);
        mBtnLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(LoginActivity.class);
            }
        });
    }

    private void initEvent() {
        mRgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        mRlLogin.setVisibility(View.GONE);
                        mNoScrollView.setCurrentItem(0, false);
                        break;
                    case R.id.rb_product:
                        mRlLogin.setVisibility(View.GONE);
                        mNoScrollView.setCurrentItem(1, false);
                        break;
                    case R.id.rb_movie:
                        mRlLogin.setVisibility(View.GONE);
                        mNoScrollView.setCurrentItem(2, false);
                        break;
                    case R.id.rb_find:
                        mRlLogin.setVisibility(View.GONE);
                        mNoScrollView.setCurrentItem(3, false);
                        break;
                    case R.id.rb_me:
                        if (isLogin) {
                            mRlLogin.setVisibility(View.VISIBLE);
                            mRlLogin.setOnClickListener(null);
                        } else {
                            mRlLogin.setVisibility(View.GONE);
                        }
                        mNoScrollView.setCurrentItem(4, false);
                        break;
                }
            }
        });
    }

    private class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }

    /**
     * 关闭程序提醒
     */
    private long keyBackClickCount;

    public boolean dispatchKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                ) {
            switch ((int) keyBackClickCount++) {
                case 0:
                    T.showShort(MainActivity.this, "再按一次退出");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            keyBackClickCount = 0;
                        }
                    }, 3000);
                    break;
                case 1:
                    exitApp();
                    break;
                default:
                    break;
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
