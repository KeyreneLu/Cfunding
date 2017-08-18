package com.jdyy.cfunding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.bean.DymicBean;
import com.jdyy.cfunding.utils.TextUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class DynamiscAdapter extends BaseAdapter {

    private Context mContext;
    List<DymicBean> data;
    public DynamiscAdapter(Context context, List<DymicBean> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_dynamics,null,false);
            holder.mTvDynamicsYear = (TextView) convertView.findViewById(R.id.tv_dynamics_year);
            holder.mTvDynamicsDay = (TextView) convertView.findViewById(R.id.tv_dynamics_day);
            holder.mTvDynamicsName = (TextView) convertView.findViewById(R.id.tv_dynamics_name);
            holder.mTvDynamicsContent = (TextView) convertView.findViewById(R.id.tv_dynamics_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        DymicBean s = data.get(position);
        holder.mTvDynamicsYear.setText(TextUtils.timesyear(s.getTime()));
        holder.mTvDynamicsDay.setText(TextUtils.timesday(s.getTime()));
        holder.mTvDynamicsName.setText(s.getId());
        if (s.getContent().contains("img")){
            holder.mWebView.getSettings().setJavaScriptEnabled(true);
            holder.mWebView.getSettings().setUseWideViewPort(true);
            holder.mWebView.getSettings().setLoadWithOverviewMode(true);
            holder.mWebView.loadDataWithBaseURL(null, "<p><img src=\"/uploads/6725/20160524/zmlcms_1464078220768.jpg\"/></p><p>" +
                            "<img src=\"/uploads/6725/20160524/zmlcms_1464078222639.jpg\"/>" +
                            "</p><p><img src=\"/uploads/6725/20160524/zmlcms_1464078222743.jpg\"/></p><p><img style=\"white-space: normal;\" src=\"http://www.zamamall.cn/uploads/6725/20160524/zmlcms_1464078227771.jpg\"/></p><p><img src=\"/uploads/6725/20160524/zmlcms_1464078224887.jpg\"/></p><p><img src=\"/uploads/6725/20160524/zmlcms_1464078224806.jpg\"/></p><p><img src=\"/uploads/6725/20160524/zmlcms_1464078225824.jpg\"/></p><p><br/></p><p><img src=\"/uploads/6725/20160524/zmlcms_1464078226818.jpg\"/></p><p><img src=\"/uploads/6725/20160524/zmlcms_1464078226971.jpg\"/></p><p><img src=\"/uploads/6725/20160524/zmlcms_1464078227770.jpg\"/></p><p><br/></p><p>关于烽火通信<br/>烽火通信科技股份有限公司（股票代码：600498）是国际知名的信息通信网络产品与解决方案提供商。自1999年成立至今，烽火通信始终专注于民族光通信事业的进步与发展，积累了对人类信息通信生活的深刻理解和创造力。公司的主营业务立足于光通信，并深入拓展至信息技术与通信技术融合而生的广泛领域，客户遍布国内、国际和信息化三大市场，已跻身全球光传输与网络接入设备、光纤光缆最具竞争力企业10强。<br/><br/>关于烽火终端<br/>业务与终端产出线是烽火通信最年轻的一条产出线，2015年出货量达1000万台，产品涵盖家庭网关、LTE终端、网络机顶盒及融合视讯平台等，面向家庭和企业用户，提供数据，语音，视频等多种互联网业务，并可完美体验智慧家庭及物联网应用。烽火终端产品灵活的业务提供能力、完善的Qos功能、强大的安全保障和便捷的管理方式，可满足用户差异化需求。<br/></p>"
                    , "text/html", "utf-8", null);
        }else {
            holder.mTvDynamicsContent.setText(s.getContent());
        }
        return convertView;
    }

    class ViewHolder{
        TextView mTvDynamicsYear,mTvDynamicsDay,mTvDynamicsName,mTvDynamicsContent;
        WebView mWebView;
    }
}
