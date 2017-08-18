package com.jdyy.cfunding.fragment.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.bean.Information;
import com.jdyy.cfunding.fragment.BaseFragment;
import com.jdyy.cfunding.http.GlobalToken;
import com.jdyy.cfunding.http.HttpMethods;
import com.jdyy.cfunding.utils.HTMLUtil;
import com.jdyy.cfunding.view.scrollView.CustomViewPager;
import com.zzhoujay.richtext.RichText;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

import static com.jdyy.cfunding.R.id.webView;

/**
 * 项目详情界面
 * Created by Administrator on 2016/11/30 0030.
 */

public class ProjectDetailFragment extends BaseFragment {
    @Bind(webView)
    TextView mWebView;
    @Bind(R.id.scrollview)
    ScrollView mScrollview;

    private CustomViewPager vp;
    private String id;
    private String content;
    Subscriber<Information> mSubscriber;

    public ProjectDetailFragment(CustomViewPager vp) {
        this.vp = vp;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_projectdetail;
    }

    @Override
    protected void initView(View view) {
        vp.setObjectForPosition(view, 0);
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
//                T.showShort(getContext(),e.getMessage());
            }

            @Override
            public void onNext(Information information) {
                Log.e("SupportListFragment", information.getContent() + "");
                content = information.getContent().toString();
                RichText.from(HTMLUtil.toHtmlTag(content)).autoFix(true).into(mWebView);
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
