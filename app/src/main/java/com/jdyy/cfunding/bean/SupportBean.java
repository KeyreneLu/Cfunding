package com.jdyy.cfunding.bean;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class SupportBean extends RealmObject{
    private String order_out_orderid;
    private String order_create_time;
    private String order_amount;
    private String mobile;

    public String getOrder_out_orderid() {
        return order_out_orderid;
    }

    public void setOrder_out_orderid(String order_out_orderid) {
        this.order_out_orderid = order_out_orderid;
    }

    public String getOrder_create_time() {
        return order_create_time;
    }

    public void setOrder_create_time(String order_create_time) {
        this.order_create_time = order_create_time;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "SupportBean{" +
                "order_out_orderid='" + order_out_orderid + '\'' +
                ", order_create_time='" + order_create_time + '\'' +
                ", order_amount='" + order_amount + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
