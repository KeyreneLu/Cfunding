package com.jdyy.cfunding.bean;

/**
 * Created by Administrator on 2016/11/26 0026.
 */

public class Product {
    /**
     * id : 78
     * name : 国馆·中国道——文化白酒
     * desc : 国馆，是四川沱牌舍得酒业出品的高端文化白酒品牌。重新发现中国文化，颠覆传统白酒，让白酒成为文化最温暖的表达方式。
     * cover_img : /uploads//20160518/zmlcms_1463574402751.jpg
     * region : 0
     * finish_money : 37121.00
     * raising_money : 30000.00
     * has_investment : 0.00
     * lowest_money : 0.00
     * start_time : 1448208000
     * end_time : 1465560610
     * sort : 0
     * view : 0
     * attribute : 0
     * update_time : 1463575835
     * time : 1463575835
     * topfund : 0
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
    private String catName;

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

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return "Product{" +
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
                '}';
    }
}
