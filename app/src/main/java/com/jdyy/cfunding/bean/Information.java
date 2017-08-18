package com.jdyy.cfunding.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class Information  extends RealmObject{

    /**
     * id : 125
     * name : 悠趣智路由 - 孩子上网的安全卫士
     * desc : 悠趣智路由是首款针对未成年人绿色上网安全管理的智能路由器，它在拥有传统智能路由器的一键配置、上网加速、手机控制等功能外，更具备未成年人绿色上网功能，可实现未成年人上网设备的内容管理、时间管理与远程控制，并针对不同年龄段的未成年人提供优质互联网推荐内容，合理安排上网时间，营造健康积极的上网环境。
     * cover_img : /uploads/6725/20160524/zmlcms_1464081697116.jpg
     * region : 0
     * finish_money : 4619
     * raising_money : 149.00
     * has_investment : 0.00
     * lowest_money : 0.00
     * start_time : 0
     * end_time : 1467043200
     * sort : 0
     * view : 0
     * attribute : 0
     * update_time : 1464856069
     * time : 1464856069
     * topfund : 0
     * content :
     * support : [{"order_out_orderid":"201605281206068576","order_create_time":"1464408366","order_amount":"149","mobile":"15802714687"},{"order_out_orderid":"201606021428008810","order_create_time":"1464848880","order_amount":"447","mobile":"15107160575"},{"order_out_orderid":"201606021428343480","order_create_time":"1464848914","order_amount":"894","mobile":"15107160575"},{"order_out_orderid":"201606021429209017","order_create_time":"1464848960","order_amount":"1490","mobile":"15107160575"},{"order_out_orderid":"201606021431011057","order_create_time":"1464849061","order_amount":"1490","mobile":"18627989987"},{"order_out_orderid":"201606021431515607","order_create_time":"1464849111","order_amount":"298","mobile":"18602701722"},{"order_out_orderid":"201606021634147554","order_create_time":"1464856454","order_amount":"596","mobile":"18679260733"}]
     * dymic : [{"id":"26","time":"1464231378","content":"项目已被咱们众筹审核通过"},{"id":"41","time":"1464397320","content":"项目进入上线展示"},{"id":"52","time":"1467166316","content":"项目进入众筹成功"}]
     * qa : null
     */

    private String id;
    private String name;
    private String desc;
    private String cover_img;
    private String region;
    private String finish_money;
    private String raising_money;
    private String has_investment;
    private String lowest_money;
    private String start_time;
    private String end_time;
    private String sort;
    private String view;
    private String attribute;
    private String update_time;
    private String time;
    private String topfund;
    private String content;

    public RealmList<QaBean> getQa() {
        return qa;
    }

    public void setQa(RealmList<QaBean> qa) {
        this.qa = qa;
    }
    /**
     * order_out_orderid : 201605281206068576
     * order_create_time : 1464408366
     * order_amount : 149
     * mobile : 15802714687
     */
    private RealmList<SupportBean> support;
    /**
     * id : 26
     * time : 1464231378
     * content : 项目已被咱们众筹审核通过
     */

    private RealmList<DymicBean> dymic;


    private RealmList<QaBean> qa;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFinish_money() {
        return finish_money;
    }

    public void setFinish_money(String finish_money) {
        this.finish_money = finish_money;
    }

    public String getRaising_money() {
        return raising_money;
    }

    public void setRaising_money(String raising_money) {
        this.raising_money = raising_money;
    }

    public String getHas_investment() {
        return has_investment;
    }

    public void setHas_investment(String has_investment) {
        this.has_investment = has_investment;
    }

    public String getLowest_money() {
        return lowest_money;
    }

    public void setLowest_money(String lowest_money) {
        this.lowest_money = lowest_money;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTopfund() {
        return topfund;
    }

    public void setTopfund(String topfund) {
        this.topfund = topfund;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public List<SupportBean> getSupport() {
        return support;
    }

    public void setSupport(RealmList<SupportBean> support) {
        this.support = support;
    }

    public List<DymicBean> getDymic() {
        return dymic;
    }

    public void setDymic(RealmList<DymicBean> dymic) {
        this.dymic = dymic;
    }

//    public static class SupportBean implements RealmModel{
//        private String order_out_orderid;
//        private String order_create_time;
//        private String order_amount;
//        private String mobile;
//
//        public String getOrder_out_orderid() {
//            return order_out_orderid;
//        }
//
//        public void setOrder_out_orderid(String order_out_orderid) {
//            this.order_out_orderid = order_out_orderid;
//        }
//
//        public String getOrder_create_time() {
//            return order_create_time;
//        }
//
//        public void setOrder_create_time(String order_create_time) {
//            this.order_create_time = order_create_time;
//        }
//
//        public String getOrder_amount() {
//            return order_amount;
//        }
//
//        public void setOrder_amount(String order_amount) {
//            this.order_amount = order_amount;
//        }
//
//        public String getMobile() {
//            return mobile;
//        }
//
//        public void setMobile(String mobile) {
//            this.mobile = mobile;
//        }
//    }

//    public static class DymicBean implements RealmModel{
//        private String id;
//        private String time;
//        private String content;
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getTime() {
//            return time;
//        }
//
//        public void setTime(String time) {
//            this.time = time;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//    }

//    public static class QaBean implements RealmModel{
//        private String title;
//        private String content;
//        private String time;
//        private String answer;
//        private String acontent;
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//
//        public String getTime() {
//            return time;
//        }
//
//        public void setTime(String time) {
//            this.time = time;
//        }
//
//        public String getAnswer() {
//            return answer;
//        }
//
//        public void setAnswer(String answer) {
//            this.answer = answer;
//        }
//
//        public String getAcontent() {
//            return acontent;
//        }
//
//        public void setAcontent(String acontent) {
//            this.acontent = acontent;
//        }
//    }


    @Override
    public String toString() {
        return "Information{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", cover_img='" + cover_img + '\'' +
                ", region='" + region + '\'' +
                ", finish_money='" + finish_money + '\'' +
                ", raising_money='" + raising_money + '\'' +
                ", has_investment='" + has_investment + '\'' +
                ", lowest_money='" + lowest_money + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", sort='" + sort + '\'' +
                ", view='" + view + '\'' +
                ", attribute='" + attribute + '\'' +
                ", update_time='" + update_time + '\'' +
                ", time='" + time + '\'' +
                ", topfund='" + topfund + '\'' +
                ", content='" + content + '\'' +
                ", support=" + support.get(0).toString() +
                ", dymic=" + dymic.get(0).toString() +
                ", qa=" + qa +
                '}';
    }
}
